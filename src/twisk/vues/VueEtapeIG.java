package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import twisk.designPattern.Observateur;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public abstract class VueEtapeIG extends VBox implements Observateur {
    protected final MondeIG monde;
    protected final EtapeIG etape;
    protected final Label label;

    public VueEtapeIG(MondeIG monde, EtapeIG etape) {
        this.monde = monde;
        monde.ajouterObservateur(this);
        this.etape = etape;
        label = new Label(this.etape.getNom());
        this.getChildren().add(label);
    }
}
