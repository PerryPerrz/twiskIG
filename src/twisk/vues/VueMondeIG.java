package twisk.vues;

import javafx.scene.layout.Pane;
import twisk.designPattern.Observateur;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.TailleComposants;

import java.util.Iterator;

public class VueMondeIG extends Pane implements Observateur {
    private final MondeIG monde;

    public VueMondeIG(MondeIG monde) {
        this.monde = monde;
        monde.ajouterObservateur(this);
        TailleComposants tC = TailleComposants.getInstance();
        for (Iterator<EtapeIG> iter = monde.iterator(); iter.hasNext();) {
            EtapeIG etape = iter.next();
            //On met à jour le modèle avant de mettre à jour la vue.
            etape.randomPositions();
            VueActiviteIG viewA = new VueActiviteIG(this.monde, etape);
            viewA.setMinSize(tC.getLarg(), tC.getHaut());
            this.getChildren().add(viewA);
            viewA.reagir();
            for (PointDeControleIG pdc : etape) {
                VuePointDeControleIG viewPdc = new VuePointDeControleIG(this.monde, pdc);
                this.getChildren().add(viewPdc);
                viewPdc.reagir();
            }
        }
    }

    @Override
    public void reagir() {
        this.getChildren().clear();
        TailleComposants tC = TailleComposants.getInstance();
        //il demande un iterator sur les arcs au monde puis parours les arcs avec un for
        for (Iterator<ArcIG> it = monde.iteratorArcs(); it.hasNext(); ) {
            ArcIG a = it.next();
            VueArcIG viewArk = new VueArcIG(monde, a);
            this.getChildren().add(viewArk);
        }
        for (Iterator<EtapeIG> iter = monde.iterator(); iter.hasNext();) {
            EtapeIG etape = iter.next();
            VueActiviteIG viewA = new VueActiviteIG(this.monde, etape);
            viewA.setMinSize(tC.getLarg(), tC.getHaut());
            this.getChildren().add(viewA);
            for (PointDeControleIG pdc : etape) {
                VuePointDeControleIG viewPdc = new VuePointDeControleIG(this.monde, pdc);
                this.getChildren().add(viewPdc);
            }
        }
    }
}
