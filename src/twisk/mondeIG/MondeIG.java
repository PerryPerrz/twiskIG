package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;
import twisk.vues.Vue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MondeIG implements Iterable<EtapeIG> {
    private final HashMap<String, EtapeIG> etapes;
    private final ArrayList<Vue> vues;
    private final ArrayList<ArcIG> arcs;

    public MondeIG() {
        FabriqueIdentifiant fabID = FabriqueIdentifiant.getInstance();
        etapes = new HashMap<>(10);
        vues = new ArrayList<>(10);
        arcs = new ArrayList<>(10);
        String id = fabID.getIdentifiantEtape();
        ActiviteIG activite = new ActiviteIG("Activité n°" + id, id);
        this.etapes.put(id, activite);
    }

    public void ajouter(String type) {
        switch (type) {
            case "Activite":
                FabriqueIdentifiant fabID = FabriqueIdentifiant.getInstance();
                String id = fabID.getIdentifiantEtape();
                ActiviteIG activite = new ActiviteIG("Activité n°" + id, id);
                this.etapes.put(id, activite);
                this.prevenirVues();
        }
    }

    @Override
    public Iterator<EtapeIG> iterator() {
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < etapes.size() && etapes.get("" + index) != null; //On regarde si on est arrivé à la fin et si ce n'est pas le cas, si l'étape actuelle n'est pas null
            }

            @Override
            public EtapeIG next() {
                index++;
                return etapes.get("" + (index - 1));
            }
        };
    }

    public void ajouterVue(Vue v) {
        vues.add(v);
    }

    public void prevenirVues() {
        for (int i = 0; i < this.vues.size(); ++i) {
            vues.get(i).mettreAJour();
        }
    }

    //Fonctions nécessaires aux tests de MondeIG (fonction "ajouter", "iterator")
    public int nbEtapes() {
        int cpt = 0;
        for (int i = 0; i < this.etapes.size(); ++i) {
            cpt += 1;
        }
        return cpt;
    }

    public String getIdentifiantEtape(String id) {
        return this.getEtapeIndice(id).getIdentifiant();
    }

    public void ajouter(PointDeControleIG pdc1, PointDeControleIG pdc2) {
        boolean alreadyAdd = false;
        boolean pointDeDepartSurUnPointDArret = false;
        for (Iterator<ArcIG> it = this.iteratorArcs(); it.hasNext(); ) {
            ArcIG arc = it.next();
            //Exactement le même arc où exactement l'opposé de cet arc
            if ((arc.getPdcDepart().getId().equals(pdc1.getId()) && arc.getPdcArrive().getId().equals(pdc2.getId())) || (arc.getPdcDepart().getId().equals(pdc2.getId()) && arc.getPdcArrive().getId().equals(pdc1.getId()))) {
                alreadyAdd = true;
                System.out.println("On ne peut pas créer un arc déjà créer! où exactement l'inverse!");
            }
            if (arc.getPdcArrive().getId().equals(pdc1.getId())) {
                pointDeDepartSurUnPointDArret = true;
                System.out.println("Un arc ne peut pas partir du point d'arrivé d'un autre arc!");
            }
        }
        System.out.println((pdc1.getEtapeRattache() == pdc2.getEtapeRattache()) + "" + alreadyAdd + "" + pointDeDepartSurUnPointDArret);
        if (pdc1.getEtapeRattache() == pdc2.getEtapeRattache()) {
            System.out.println("Vous ne pouvez pas, créer d'arcs entre 2 points de controle identiques! où créer un arc entre 2 points d'une même étape!");
        } else if (!alreadyAdd && !pointDeDepartSurUnPointDArret) {
            ArcIG ark = new ArcIG(pdc1, pdc2);
            this.arcs.add(ark);
        }
    }

    public Iterator<ArcIG> iteratorArcs() {
        return arcs.iterator();
    }

    public void creationArc(PointDeControleIG pdc) {
        boolean isCreated = false;
        for (EtapeIG etape : this) {
            for (PointDeControleIG pdcIG : etape) {
                //Je cherche dans tous les pdc si il y en a un qui est true, le pdc en paramètre est le 2éme pdc qu'on à cliqué
                //Si j'en trouve un, cela signifie que celui en paramètre est le second
                if (pdcIG.isClicked()) {
                    pdcIG.setClicked();
                    ajouter(pdcIG, pdc);
                    isCreated = true;
                    this.prevenirVues();
                }
                //Si j'en trouve pas, le pdc en param est le premier.
            }
        }
        if (!isCreated) {
            pdc.setClicked();
        }
    }

    //Fonctions nécessaires aux tests
    public EtapeIG getEtapeIndice(String indice) {
        return this.etapes.get(indice);
    }

    public int getNbArcs() {
        return this.arcs.size();
    }
}
