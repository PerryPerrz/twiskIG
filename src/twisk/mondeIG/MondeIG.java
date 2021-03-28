package twisk.mondeIG;

import javafx.scene.control.TextInputDialog;
import javafx.scene.shape.Arc;
import twisk.designPattern.SujetObserve;
import twisk.exceptions.ArcAlreadyCreateException;
import twisk.exceptions.CreateArcWithEndPdcException;
import twisk.exceptions.SameActivityException;
import twisk.exceptions.TwiskException;
import twisk.outils.FabriqueIdentifiant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MondeIG extends SujetObserve implements Iterable<EtapeIG> {
    private final HashMap<String, EtapeIG> etapes;
    private final ArrayList<EtapeIG> etapesSelectionnees;
    private final ArrayList<ArcIG> arcs;

    public MondeIG() {
        super();
        FabriqueIdentifiant fabID = FabriqueIdentifiant.getInstance();
        etapes = new HashMap<>(10);
        etapesSelectionnees = new ArrayList<>(10);
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
                this.notifierObservateurs();
        }
    }

    @Override
    public Iterator<EtapeIG> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private int nbEtapesDejaParcourues = 0;

            @Override
            public boolean hasNext() {
                //Tant qu'on à pas parcouru toutes les étapes
                while (nbEtapesDejaParcourues != etapes.size()) {
                    if (etapes.get("" + index) == null) { //On regarde si l'étape existe ou pas
                        index++; //On passe à la prochaine étape
                    } else {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public EtapeIG next() {
                index++;
                nbEtapesDejaParcourues++;
                return etapes.get("" + (index - 1));
            }
        };
    }

    //Fonctions nécessaires aux tests de MondeIG (fonction "ajouter", "iterator")
    public int nbEtapes() {
        int cpt = 0;
        for (int i = 0; i < this.etapes.size(); ++i) {
            cpt += 1;
        }
        return cpt;
    }

    public void ajouter(PointDeControleIG pdc1, PointDeControleIG pdc2) throws TwiskException {
        for (Iterator<ArcIG> it = this.iteratorArcs(); it.hasNext(); ) {
            ArcIG arc = it.next();
            //Exactement le même arc où exactement l'opposé de cet arc
            if ((arc.getPdcDepart().getId().equals(pdc1.getId()) && arc.getPdcArrive().getId().equals(pdc2.getId()))) {
                throw new ArcAlreadyCreateException("On ne peut pas créer un arc déjà créer!");
            }
            if (arc.getPdcArrive().getId().equals(pdc1.getId()) || arc.getPdcDepart().getId().equals(pdc2.getId())) {
                throw new CreateArcWithEndPdcException("Un arc ne peut pas partir du point d'arrivé d'un autre arc!");
            }
        }
        if (pdc1.getEtapeRattache() == pdc2.getEtapeRattache()) {
            throw new SameActivityException("Vous ne pouvez pas, créer d'arcs entre 2 points de controle identiques! où créer un arc entre 2 points d'une même étape!");
        }
        ArcIG ark = new ArcIG(pdc1, pdc2);
        this.arcs.add(ark);
    }

    public Iterator<ArcIG> iteratorArcs() {
        return arcs.iterator();
    }

    public void creationArc(PointDeControleIG pdc) throws TwiskException {
        boolean isCreated = false;
        for (EtapeIG etape : this) {
            for (PointDeControleIG pdcIG : etape) {
                //Je cherche dans tous les pdc si il y en a un qui est true, le pdc en paramètre est le 2éme pdc qu'on à cliqué
                //Si j'en trouve un, cela signifie que celui en paramètre est le second
                if (pdcIG.isClicked()) {
                    pdcIG.setClicked();
                    isCreated = true;
                    ajouter(pdcIG, pdc);
                    this.notifierObservateurs();
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

    public void ajouterEtapeSelectionnee(EtapeIG etape) {
        if (isSelectionned(etape)) {
            etapesSelectionnees.remove(etape);
        } else {
            etapesSelectionnees.add(etape);
        }
        this.notifierObservateurs();
    }

    public boolean isSelectionned(EtapeIG etape) {
        return etapesSelectionnees.contains(etape);
    }

    public void supprimerLaSelection() {
        for (EtapeIG e : this) {
            supprimer(e);
        }
        this.notifierObservateurs();
    }

    public void supprimer(EtapeIG e) {
        ArcIG arc;
        if (isSelectionned(e)) {
            for(Iterator<ArcIG> iter = this.iteratorArcs(); iter.hasNext();){
                arc = iter.next();
                if(arc.isLinkedToStage(e)){
                    iter.remove();
                    arcs.remove(arc);
                }
            }
            etapesSelectionnees.remove(e);
            etapes.remove(e.getIdentifiant());
        }
    }

    public void renommerLaSelection(String newName){
        for(EtapeIG e : this){
            if(this.isSelectionned(e)){
                e.setNom(newName);
            }
        }
        notifierObservateurs();
    }
}
