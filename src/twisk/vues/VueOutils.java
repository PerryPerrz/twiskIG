package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import twisk.mondeIG.MondeIG;


public class VueOutils extends TilePane implements Vue {
    private MondeIG monde;
    private Button bouton;

    public VueOutils(MondeIG monde) {
        this.monde = monde;
        monde.ajouterVue(this);
        bouton = new Button("");
        bouton.setOnAction(actionEvent -> monde.ajouter("Activite"));
        Tooltip tool = new Tooltip();
        bouton.setTooltip(tool);
        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/add.png"), 36, 36, true, true); //Donne le chemin à partir de src
        ImageView icon = new ImageView(image);
        bouton.setGraphic(icon);
        this.getChildren().add(bouton);
    }

    @Override
    public void mettreAJour() {

    }
}
