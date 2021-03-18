package twisk.mondeIG;

public class PointDeControleIG {
    private int centreX;
    private int centreY;
    private final String id;
    private final EtapeIG etapeRattache;
    //Booléen qui informe sur quel Pdc l'utilisateur à cliqué
    private boolean clicked;

    public PointDeControleIG(String id, EtapeIG etape) {
        this.id = id;
        this.etapeRattache = etape;
        this.clicked = false;
    }

    public void setCentre(int centreX, int centreY) {
        this.centreX = centreX;
        this.centreY = centreY;
    }

    public int getCentreX() {
        return centreX;
    }

    public int getCentreY() {
        return centreY;
    }

    //Change la valeur du booléen "clicked"
    public void setClicked() {
        this.clicked = !this.clicked;
    }

    //Regarde si l'utilisateur à cliqué sur le pdc
    public boolean isClicked() {
        return this.clicked;
    }
}
