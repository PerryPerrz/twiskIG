package twisk.mondeIG;

public class ArcIG {
    private final PointDeControleIG PdcDepart;
    private final PointDeControleIG PdcArrive;

    public ArcIG(PointDeControleIG PdcA, PointDeControleIG PdcD) {
        this.PdcDepart = PdcD;
        this.PdcArrive = PdcA;
    }
}