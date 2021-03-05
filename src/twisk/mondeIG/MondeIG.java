package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;

import java.util.HashMap;
import java.util.Iterator;

public class MondeIG implements Iterable<EtapeIG>{
    private HashMap<String,EtapeIG> etapes;

    public MondeIG(){
        FabriqueIdentifiant fabID = FabriqueIdentifiant.getInstance();
        String id = fabID.getIdentifiantEtape();
        ActiviteIG activite = new ActiviteIG("Activité n°" + id,id,45,90);
        this.etapes.put(id,activite);
    }

    public void ajouter(String type){
        switch(type){
            case "Activité" :
                FabriqueIdentifiant fabID = FabriqueIdentifiant.getInstance();
                String id = fabID.getIdentifiantEtape();
                ActiviteIG activite = new ActiviteIG("Activité n°" + id,id,45,90);
                this.etapes.put(id,activite);
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

    /*
    public void ajouterVue(Vue v){

    }

    public void prevenirVues(Vue v){

    }
    */
}
