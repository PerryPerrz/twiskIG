package twisk.vues;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import twisk.designPattern.Observateur;
import twisk.exceptions.UncorrectSettingsException;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

import java.util.Optional;

public class VueMenu extends MenuBar implements Observateur {
    private final MondeIG monde;
    private final Menu fichier;
    private final Menu edition;
    private final Menu mondeMenu;
    private final Menu parametres;
    private final Menu style;

    public VueMenu(MondeIG monde) {
        this.monde = monde;
        monde.ajouterObservateur(this);

        fichier = new Menu("Fichier");
        edition = new Menu("Edition");
        mondeMenu = new Menu("Monde");
        parametres = new Menu("Paramètres");
        style = new Menu(("Style"));
        MenuItem quitter = new MenuItem();
        MenuItem supprimer = new MenuItem();
        MenuItem renommer = new MenuItem();
        MenuItem effacer = new MenuItem();
        MenuItem entree = new MenuItem("Entrée");
        MenuItem sortie = new MenuItem("Sortie");
        MenuItem delai = new MenuItem("Délai");
        MenuItem ecart = new MenuItem("Écart");
        MenuItem jour = new MenuItem("Jour");
        MenuItem nuit = new MenuItem("Nuit");

        this.fichier.getItems().add(quitter);
        this.edition.getItems().add(supprimer);
        this.edition.getItems().add(renommer);
        this.edition.getItems().add(effacer);
        this.mondeMenu.getItems().add(entree);
        this.mondeMenu.getItems().add(sortie);
        this.parametres.getItems().add(delai);
        this.parametres.getItems().add(ecart);
        this.style.getItems().add(jour);
        this.style.getItems().add(nuit);

        quitter.setOnAction(actionEvent -> Platform.exit());
        supprimer.setOnAction(actionEvent -> monde.supprimerLaSelection());
        renommer.setOnAction(actionEvent -> this.rename());
        effacer.setOnAction(actionEvent -> monde.effacerLaSelection());
        entree.setOnAction(actionEvent -> monde.setEntree());
        sortie.setOnAction(actionEvent -> monde.setSortie());
        delai.setOnAction(actionEvent -> this.delai());
        ecart.setOnAction(actionEvent -> this.ecart());
        jour.setOnAction(actionEvent -> monde.setStyle(0));
        nuit.setOnAction(actionEvent -> monde.setStyle(1));

        TailleComposants tc = TailleComposants.getInstance();
        Image image3 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/file.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon3 = new ImageView(image3);
        fichier.setGraphic(icon3);

        Image image4 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/edit.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon4 = new ImageView(image4);
        edition.setGraphic(icon4);

        Image image9 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/world.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon9 = new ImageView(image9);
        mondeMenu.setGraphic(icon9);

        Image image10 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/settings.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon10 = new ImageView(image10);
        parametres.setGraphic(icon10);

        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/exit.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon = new ImageView(image);
        quitter.setGraphic(icon);

        Image image2 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/delete.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon2 = new ImageView(image2);
        supprimer.setGraphic(icon2);

        Image image5 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/rename.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon5 = new ImageView(image5);
        renommer.setGraphic(icon5);

        renommer.setDisable(true);

        Image image6 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/select.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon6 = new ImageView(image6);
        effacer.setGraphic(icon6);

        Image image7 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/entree.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon7 = new ImageView(image7);
        entree.setGraphic(icon7);

        Image image8 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/sortie.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon8 = new ImageView(image8);
        sortie.setGraphic(icon8);

        Image image11 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/hourglass.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon11 = new ImageView(image11);
        delai.setGraphic(icon11);

        delai.setDisable(true);

        Image image12 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/hourglass.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon12 = new ImageView(image12);
        ecart.setGraphic(icon12);

        ecart.setDisable(true);

        this.getMenus().addAll(fichier, edition, mondeMenu, parametres, style);
    }

    @Override
    public void reagir() {
        edition.getItems().get(1).setDisable(monde.nbEtapesSelectionnees() != 1); //On set le disable à false lorsque le nombre d'étapes est égale à 1
        parametres.getItems().get(0).setDisable(monde.nbEtapesSelectionnees() != 1);//On disable délai
        parametres.getItems().get(1).setDisable(monde.nbEtapesSelectionnees() != 1);//On disable écart
    }

    public void rename() {
        TextInputDialog dialog = new TextInputDialog("Balançoire");
        dialog.setTitle("Renommer la sélection");
        dialog.setHeaderText("Entrez votre nouveau nom :");
        dialog.setContentText("Nom :");

        TailleComposants tc = TailleComposants.getInstance();
        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/tools.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dialog.setGraphic(icon);

        Optional<String> result = dialog.showAndWait();
        //Ce qu'on à donné à result est stocké dans name et on fait un appel un la fonction avec la variable name
        result.ifPresent(name -> this.monde.renommerLaSelection(name));
    }

    public void delai() {
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Délai d'une activité");
        dialog.setHeaderText("Entrez votre délai :");
        dialog.setContentText("Délai :");

        TailleComposants tc = TailleComposants.getInstance();
        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/tools.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dialog.setGraphic(icon);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(d -> {
            try {
                this.monde.setDelai(d);
            } catch (UncorrectSettingsException uSE) {
                Alert dia = new Alert(Alert.AlertType.ERROR);
                dia.setTitle("UncorrectSettingsException");
                dia.setHeaderText("Impossible de saisir ce délai");
                dia.setContentText("Erreur : La saisie du délai est incorrecte\n" +
                        "Veuillez ré-essayer");
                Image image2 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/warning.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon2 = new ImageView(image2);
                dia.setGraphic(icon2);
                dia.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dia.close());
                pt.play();
            }
        });
    }

    public void ecart() {
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Écart d'une activité");
        dialog.setHeaderText("Entrez votre écart :");
        dialog.setContentText("Écart :");

        TailleComposants tc = TailleComposants.getInstance();
        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/tools.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dialog.setGraphic(icon);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(e -> {
            try {
                this.monde.setEcart(e);
            } catch (UncorrectSettingsException uSE) {
                Alert dia = new Alert(Alert.AlertType.ERROR);
                dia.setTitle("UncorrectSettingsException");
                dia.setHeaderText("Impossible de saisir cette écart");
                dia.setContentText("Erreur : La saisie de l'écart est incorrecte\n" +
                        "Veuillez ré-essayer");
                Image image2 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/warning.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon2 = new ImageView(image2);
                dia.setGraphic(icon2);
                dia.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dia.close());
                pt.play();
            }
        });
    }
}
