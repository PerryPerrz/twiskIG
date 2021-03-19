package twisk.vues;

import javafx.scene.layout.Pane;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.TailleComposants;

import java.util.Iterator;

public class VueMondeIG extends Pane implements Vue {
    private final MondeIG monde;

    public VueMondeIG(MondeIG monde) {
        this.monde = monde;
        monde.ajouterVue(this);
        TailleComposants tC = TailleComposants.getInstance();
        for (EtapeIG etape : this.monde) {
            //On met à jour le modèle avant de mettre à jour la vue.
            etape.randomPositions();
            VueActiviteIG viewA = new VueActiviteIG(this.monde, etape);
            viewA.setMinSize(tC.getLarg(), tC.getHaut());
            this.getChildren().add(viewA);
            viewA.mettreAJour();
            for (PointDeControleIG pdc : etape) {
                VuePointDeControleIG viewPdc = new VuePointDeControleIG(this.monde, pdc);
                this.getChildren().add(viewPdc);
                viewPdc.mettreAJour();
            }
        }
    }

    @Override
    public void mettreAJour() {
        this.getChildren().clear();
        TailleComposants tC = TailleComposants.getInstance();
        //On met à jour le modèle avant de mettre à jour la vue.
        monde.randomPos();
        //il demande un iterator sur les arcs au monde puis parours les arcs avec un for
        for (Iterator<ArcIG> it = monde.iteratorArcs(); it.hasNext(); ) {
            ArcIG a = it.next();
            VueArcIG viewArk = new VueArcIG(monde, a);
            this.getChildren().add(viewArk);
            viewArk.mettreAJour();
        }
        for (EtapeIG etape : this.monde) {
            VueActiviteIG viewA = new VueActiviteIG(this.monde, etape);
            viewA.setMinSize(tC.getLarg(), tC.getHaut());
            this.getChildren().add(viewA);
            viewA.mettreAJour();
            for (PointDeControleIG pdc : etape) {
                VuePointDeControleIG viewPdc = new VuePointDeControleIG(this.monde, pdc);
                this.getChildren().add(viewPdc);
                viewPdc.mettreAJour();
            }
        }
    }
}
