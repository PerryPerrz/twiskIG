package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;


public class VueOutils extends TilePane implements Vue {
    private final MondeIG monde;
    private final Button bouton;

    public VueOutils(MondeIG monde) {
        this.monde = monde;
        monde.ajouterVue(this);
        bouton = new Button();
        bouton.setOnAction(actionEvent -> monde.ajouter("Activite"));
        Tooltip tool = new Tooltip();
        bouton.setTooltip(tool);
        TailleComposants tC = TailleComposants.getInstance();
        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/add.png"), tC.getTailleBouton(), tC.getTailleBouton(), true, true); //Donne le chemin à partir de src
        ImageView icon = new ImageView(image);
        bouton.setStyle("-fx-background-color:transparent; -fx-focus-color: transparent;");
        bouton.setGraphic(icon);
        this.getChildren().add(bouton);
    }

    @Override
    public void mettreAJour() {

    }
}
