package twisk.mondeIG;

public class ArcIG {
    private final PointDeControleIG PdcDepart;
    private final PointDeControleIG PdcArrive;

    public ArcIG(PointDeControleIG PdcD, PointDeControleIG PdcA) {
        this.PdcDepart = PdcD;
        this.PdcArrive = PdcA;
    }

    public PointDeControleIG getPdcDepart() {
        return PdcDepart;
    }

    public PointDeControleIG getPdcArrive() {
        return PdcArrive;
    }
}
