package twisk.vues;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.TailleComposants;

public class VueArcIG extends Pane implements Vue {
    private final MondeIG monde;
    private final ArcIG arc;
    private final Line ligne;
    private final Polyline triangle;

    public VueArcIG(MondeIG monde, ArcIG arc) {
        this.monde = monde;
        this.arc = arc;
        this.ligne = new Line();
        this.triangle = new Polyline();
        TailleComposants tc = TailleComposants.getInstance();
        ligne.setStroke(Color.AQUAMARINE);
        ligne.setStrokeWidth(tc.getStrokeWidth());
        triangle.setStyle("-fx-border-color: #8F00FF; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-width: 3px; -fx-background-color: #00D1FF ;fx-font-size: 12px;-fx-font-family: system-ui;-fx-text-fill: #FF008B;");
        this.getChildren().addAll(ligne, triangle);
    }

    public void mettreAJour() {
        this.getChildren().clear();
        this.apparitionDeLaLigne(this.arc.getPdcArrive(), this.arc.getPdcDepart());
        this.getChildren().add(ligne);
    }

    public void apparitionDeLaLigne(PointDeControleIG pdc1, PointDeControleIG pdc2) {
        this.ligne.setStartX(pdc1.getCentreX());
        this.ligne.setStartY(pdc1.getCentreY());
        this.ligne.setEndX(pdc2.getCentreX());
        this.ligne.setEndY(pdc2.getCentreY());
    }
}
