package twisk.vues;

import javafx.scene.layout.Pane;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import java.util.Random;

public class VueMondeIG extends Pane implements Vue {
    private final MondeIG monde;

    public VueMondeIG(MondeIG monde) {
        this.monde = monde;
        monde.ajouterVue(this);
        Random random = new Random();
        for (EtapeIG etape : this.monde) {
            VueActiviteIG viewA = new VueActiviteIG(monde, etape);
            this.getChildren().add(viewA);
            viewA.relocate(random.nextInt(650), random.nextInt(550));
        }
    }

    @Override
    public void mettreAJour() {
        this.getChildren().clear();
        Random random = new Random();
        for (EtapeIG etape : this.monde) {
            VueActiviteIG viewA = new VueActiviteIG(monde, etape);
            this.getChildren().add(viewA);
            viewA.relocate(random.nextInt(650), random.nextInt(550));
        }
    }
}
