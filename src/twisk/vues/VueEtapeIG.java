package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public abstract class VueEtapeIG extends VBox implements Vue {
    private final MondeIG monde;
    private final EtapeIG etape;
    private final Label label;

    public VueEtapeIG(MondeIG monde, EtapeIG etape) {
        this.monde = monde;
        this.etape = etape;
        label = new Label("Etape n° " + this.etape.getIdentifiant());
        this.getChildren().add(label);
        monde.ajouterVue(this);
    }
}
