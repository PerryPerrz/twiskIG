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

    //Fonction qui retourne vrai si des arcs relies cette étape
    public boolean isLinkedToStage(EtapeIG e) {
        for (PointDeControleIG pdc : e) {
            if (pdc.getId().equals(this.getPdcArrive().getId()) || pdc.getId().equals(this.getPdcDepart().getId())) {
                return true;
            }
        }
        return false;
    }
}
