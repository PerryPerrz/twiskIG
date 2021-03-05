package twisk.vues;

import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

public class VueActiviteIG extends VueEtapeIG implements Vue {
    private final HBox box;

    public VueActiviteIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        box = new HBox();
        box.setStyle("-fx-border-color: #0059FF; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
        this.getChildren().add(box);
    }

    @Override
    public void mettreAJour() {

    }
}
