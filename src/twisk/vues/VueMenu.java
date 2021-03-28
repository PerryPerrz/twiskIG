package twisk.vues;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import twisk.designPattern.Observateur;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

import java.util.Optional;

public class VueMenu extends MenuBar implements Observateur {
    private final MondeIG monde;
    private final Menu fichier;
    private final Menu edition;

    public VueMenu(MondeIG monde) {
        this.monde = monde;
        monde.ajouterObservateur(this);

        fichier = new Menu("Fichier");
        edition = new Menu("Edition");
        MenuItem quitter = new MenuItem();
        MenuItem supprimer = new MenuItem();
        MenuItem renommer = new MenuItem();
        this.fichier.getItems().add(quitter);
        this.edition.getItems().add(supprimer);
        this.edition.getItems().add(renommer);
        quitter.setOnAction(actionEvent -> Platform.exit());
        supprimer.setOnAction(actionEvent -> monde.supprimerLaSelection());
        renommer.setOnAction(actionEvent -> this.rename());

        TailleComposants tc = TailleComposants.getInstance();
        Image image3 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/file.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon3 = new ImageView(image3);
        fichier.setGraphic(icon3);

        Image image4 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/edit.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon4 = new ImageView(image4);
        edition.setGraphic(icon4);

        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/exit.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon = new ImageView(image);
        quitter.setGraphic(icon);

        Image image2 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/delete.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon2 = new ImageView(image2);
        supprimer.setGraphic(icon2);

        Image image5 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/rename.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon5 = new ImageView(image5);
        renommer.setGraphic(icon5);

        this.getMenus().addAll(fichier, edition);
    }

    @Override
    public void reagir() {
    }

    public void rename(){
        TextInputDialog dialog = new TextInputDialog("Balançoire");
        dialog.setTitle("Renommer la sélection");
        dialog.setHeaderText("Entrez votre nouveau nom :");
        dialog.setContentText("Nom :");

        TailleComposants tc = TailleComposants.getInstance();
        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/tools.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dialog.setGraphic(icon);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> this.monde.renommerLaSelection(name));
    }
}
