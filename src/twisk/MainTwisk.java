package twisk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

public class MainTwisk extends Application {
    /**
     * Procédure main qui permet de lancer l'application
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    /**
     * Procédure start qui créer la fenêtre de l'application
     */
    public void start(Stage primaryStage) throws Exception {
        MondeIG monde = new MondeIG();
        BorderPane root = new BorderPane();
        VueOutils viewO = new VueOutils(monde);
        VueMondeIG viewM = new VueMondeIG(monde);
        VueMenu viewMe = new VueMenu(monde);
        TailleComposants tc = TailleComposants.getInstance();
        root.setBottom(viewO);
        root.setCenter(viewM);
        root.setRight(viewMe);
        primaryStage.setTitle("twiskIG | Hugo Ipt");
        primaryStage.setScene(new Scene(root, tc.getWindowX(), tc.getWindowY()));
        primaryStage.show();
    }
}
