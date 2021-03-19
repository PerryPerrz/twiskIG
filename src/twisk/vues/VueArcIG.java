package twisk.vues;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.TailleComposants;

import java.awt.*;

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
        ligne.setStroke(Color.PINK);
        ligne.setStrokeWidth(tc.getStrokeWidth());
        triangle.setStroke(Color.PINK);
        triangle.setFill(Color.PINK);
        triangle.setStrokeWidth(tc.getStrokeWidth());
        this.getChildren().addAll(ligne, triangle);
    }

    public void mettreAJour() {
        this.getChildren().clear();
        this.apparitionDeLaLigne(this.arc.getPdcArrive(), this.arc.getPdcDepart());
        this.apparitionDuTriangle();
        this.getChildren().addAll(ligne,triangle);
    }

    public void apparitionDeLaLigne(PointDeControleIG pdc1, PointDeControleIG pdc2) {
        this.ligne.setStartX(pdc1.getCentreX());
        this.ligne.setStartY(pdc1.getCentreY());
        this.ligne.setEndX(pdc2.getCentreX());
        this.ligne.setEndY(pdc2.getCentreY());
    }

    //Fonction qui calcule l'orientation de la flèche
    public void apparitionDuTriangle(){
        TailleComposants tc = TailleComposants.getInstance();
        int pointDepX = arc.getPdcDepart().getCentreX();
        int pointDepY = arc.getPdcDepart().getCentreY();
        int pointArrX = arc.getPdcArrive().getCentreX();
        int pointArrY = arc.getPdcArrive().getCentreY();

        Point ptA = new Point(pointArrX - pointDepX, pointArrY - pointDepY);
        double rapport = 15.00 / Math.sqrt(Math.pow((pointArrX - pointDepX), 2) + Math.pow((pointArrY - pointDepY), 2));
        Point ptB = new Point((int)(- ptA.getX() * rapport), (int)(- ptA.getY() * rapport));
        Point ptC = new Point((int)(pointArrX + ptB.getX()), (int)(pointArrY + ptB.getY()));
        Point ptD = new Point((int)(ptB.getX()/2), (int)(ptB.getY()/2));
        Point ptE = new Point((int)(ptC.getX() - ptD.getY()), (int)(ptC.getY() + ptB.getX()));
        Point ptF = new Point((int)(ptC.getX() + ptD.getY()), (int)(ptC.getY() - ptD.getX()));

        this.triangle.getPoints().addAll((double)pointArrX, (double)pointArrY, ptE.getX(), ptE.getY(), ptF.getX(), ptF.getY(),(double)pointArrX, (double)pointArrY);
    }
}
