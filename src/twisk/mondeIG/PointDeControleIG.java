package twisk.mondeIG;

public class PointDeControleIG {
    private int centreX;
    private int centreY;
    private final String id;
    private final EtapeIG etapeRattache;

    public PointDeControleIG(String id, EtapeIG etape) {
        this.id = id;
        this.etapeRattache = etape;
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
}
