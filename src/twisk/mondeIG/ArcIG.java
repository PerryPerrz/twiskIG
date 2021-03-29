package twisk.mondeIG;

public class ArcIG {
    private final PointDeControleIG PdcDepart;
    private final PointDeControleIG PdcArrive;
    private boolean select; //Variable booléenne indiquant si un arc à été selectionné ou non par l'utilisateur.

    public ArcIG(PointDeControleIG PdcD, PointDeControleIG PdcA) {
        this.PdcDepart = PdcD;
        this.PdcArrive = PdcA;
        this.select = false;
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

    public boolean isSelected() {
        return this.select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
