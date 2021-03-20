package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.exceptions.TwiskException;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.TailleComposants;

public class VuePointDeControleIG extends Circle implements Vue {
    private final MondeIG monde;
    private final PointDeControleIG pdc;

    public VuePointDeControleIG(MondeIG monde, PointDeControleIG pdc) {
        TailleComposants tc = TailleComposants.getInstance();
        this.monde = monde;
        this.pdc = pdc;
        this.setFill(Color.SLATEBLUE);
        this.setRadius(tc.getRad());
        this.setOnMouseClicked(ActionEvent -> {
            try {
                monde.creationArc(pdc);
            } catch (TwiskException e) {
                System.out.println(e.getMessage());
            }
        });
        monde.ajouterVue(this);
    }

    @Override
    public void mettreAJour() {
        //On change les coordonnées du cercle à la nouvelle place du pdc
        this.setCenterX(this.pdc.getCentreX());
        this.setCenterY(this.pdc.getCentreY());
    }


}
