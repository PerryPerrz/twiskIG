package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;
import twisk.vues.Vue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MondeIG implements Iterable<EtapeIG> {
    private final HashMap<String, EtapeIG> etapes;
    private final ArrayList<Vue> vues;

    public MondeIG() {
        FabriqueIdentifiant fabID = FabriqueIdentifiant.getInstance();
        etapes = new HashMap<>(10);
        vues = new ArrayList<>(10);
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
        return new Iterator<EtapeIG>() {
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
        for (int i = 0; i < this.etapes.size(); ++i) {
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
        return this.etapes.get(id).getIdentifiant();
    }
}
