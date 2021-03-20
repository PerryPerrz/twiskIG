package twisk.designPattern;

import java.util.ArrayList;

public class SujetObserve {
    private final ArrayList<Observateur> observateurs;

    public SujetObserve() {
        observateurs = new ArrayList<>(10);
    }

    public void ajouterObservateur(Observateur obs) {
        observateurs.add(obs);
    }

    public void notifierObservateurs() {
        for (int i = 0; i < this.observateurs.size(); ++i) {
            observateurs.get(i).reagir();
        }
    }
}
