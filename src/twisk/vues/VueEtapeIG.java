package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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
        this.setId(etape.getIdentifiant()); //L'id de VueEtape = l'id de Etape
        label = new Label(this.etape.getNom());
        this.getChildren().add(label);

        this.setOnDragDetected(this::setMouse);
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
