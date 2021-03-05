package twisk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

public class MainTwisk extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MondeIG monde = new MondeIG();
        BorderPane root = new BorderPane();
        VueOutils viewO = new VueOutils(monde);
        VueMondeIG viewM = new VueMondeIG(monde);

        /*
        Media media = new Media(new File("src/ressources/sounds/crabrave.wav").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();*/

        root.setBottom(viewO);
        root.setCenter(viewM);
        primaryStage.setTitle("twiskIG | Ipt");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }
}
