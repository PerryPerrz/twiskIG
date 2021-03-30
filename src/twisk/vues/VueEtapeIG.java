package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import twisk.designPattern.Observateur;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

public abstract class VueEtapeIG extends VBox implements Observateur {
    protected final MondeIG monde;
    protected final EtapeIG etape;
    protected final Label label;

    public VueEtapeIG(MondeIG monde, EtapeIG etape) {
        this.monde = monde;
        monde.ajouterObservateur(this);
        this.etape = etape;
        this.setId(etape.getIdentifiant()); //L'id de VueEtape = l'id de Etape
        label = new Label(this.etape.getNom());
        this.setOnDragDetected(this::setMouse);

        HBox hBox = new HBox();

        TailleComposants tc = TailleComposants.getInstance();
        if (this.etape.estUneEntree()) {
            Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/entree.png"), tc.getTailleIcons3(), tc.getTailleIcons3(), true, true);
            ImageView icon = new ImageView(image);
            hBox.getChildren().add(icon);
        }
        hBox.getChildren().add(label);
        if (this.etape.estUneSortie()) {
            Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/sortie.png"), tc.getTailleIcons3(), tc.getTailleIcons3(), true, true);
            ImageView icon = new ImageView(image);
            hBox.getChildren().add(icon);
        }
        this.getChildren().add(hBox);
    }

    public void setMouse(MouseEvent mouseEvent) {
        Dragboard dragboard = this.startDragAndDrop(TransferMode.MOVE); //Presse-papier qui contient les infos du drag'n drop
        ClipboardContent content = new ClipboardContent(); //On créer un clipboard qui contient l'id de l'étape
        content.putString(etape.getIdentifiant());
        System.out.println("Envoi : " + content.getString());
        dragboard.setContent(content);
        mouseEvent.consume(); //Cet event est finit.
    }
}
