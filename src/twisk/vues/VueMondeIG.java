package twisk.vues;

import javafx.scene.layout.Pane;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

public class VueMondeIG extends Pane implements Vue {
    private final MondeIG monde;

    public VueMondeIG(MondeIG monde) {
        this.monde = monde;
        monde.ajouterVue(this);
        TailleComposants tC = TailleComposants.getInstance();
        for (EtapeIG etape : this.monde) {
            VueActiviteIG viewA = new VueActiviteIG(monde, etape);
            viewA.setMinSize(tC.getLarg(), tC.getHaut());
            this.getChildren().add(viewA);
            viewA.relocate(etape.getPosX(), etape.getPosY());
        }
    }

    @Override
    public void mettreAJour() {
        this.getChildren().clear();
        for (EtapeIG etape : this.monde) {
            VueActiviteIG viewA = new VueActiviteIG(monde, etape);
            this.getChildren().add(viewA);
            etape.randomPositions();
            viewA.relocate(etape.getPosX(), etape.getPosY());
        }
    }
}
