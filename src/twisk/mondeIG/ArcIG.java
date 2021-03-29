package twisk.mondeIG;

public class ArcIG {
    private final PointDeControleIG PdcDepart;
    private final PointDeControleIG PdcArrive;
    private boolean select; //Variable booléenne indiquant si un arc à été selectionné ou non par l'utilisateur.

    /**
     * Constructeur de la classe ArcIG
     *
     * @param PdcD
     * @param PdcA
     */
    public ArcIG(PointDeControleIG PdcD, PointDeControleIG PdcA) {
        this.PdcDepart = PdcD;
        this.PdcArrive = PdcA;
        this.select = false;
    }

    /**
     * Fonction qui retourne l'attribut PdcDepart de la classe ArcIG, soit le PointDeControle de départ d'un arc
     *
     * @return
     */
    public PointDeControleIG getPdcDepart() {
        return PdcDepart;
    }

    /**
     * Fonction qui retourne l'attribut PdcArrive de la classe ArcIG, soit le PointDeControle d'arrivé d'un arc
     *
     * @return
     */
    public PointDeControleIG getPdcArrive() {
        return PdcArrive;
    }

    /**
     * Fonction qui retourne vrai si des arcs relis cette étape
     *
     * @param e
     * @return
     */
    public boolean isLinkedToStage(EtapeIG e) {
        for (PointDeControleIG pdc : e) {
            if (pdc.getId().equals(this.getPdcArrive().getId()) || pdc.getId().equals(this.getPdcDepart().getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Fonction qui test si un arc est selectionné (Elle retourne vrai si l'arc que l'on teste est selectionné par l'utilisateur)
     *
     * @return
     */
    public boolean isSelected() {
        return this.select;
    }

    /**
     * Procédure qui change la valeur de l'attribut select, elle permet de selectionner un arc ou non
     *
     * @param select
     */
    public void setSelect(boolean select) {
        this.select = select;
    }
}
