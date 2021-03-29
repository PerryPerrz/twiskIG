package twisk.designPattern;

import java.util.ArrayList;

public class SujetObserve {
    private final ArrayList<Observateur> observateurs;

    public SujetObserve() {
        observateurs = new ArrayList<>(10);
    }

    /**
     * Fonction qui ajoute un observateur à l'attribut qui correspond à la collection d'observateurs
     *
     * @param obs
     */
    public void ajouterObservateur(Observateur obs) {
        observateurs.add(obs);
    }

    /**
     * Fonction qui notifie les observateurs, met à jour les vues
     */
    public void notifierObservateurs() {
        for (int i = 0; i < this.observateurs.size(); ++i) {
            observateurs.get(i).reagir();
        }
    }
}
