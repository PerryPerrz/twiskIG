package twisk.outils;

public class FabriqueIdentifiant {
    private static final FabriqueIdentifiant instance = new FabriqueIdentifiant();
    private int noEtape;
    private int noPdc;

    private FabriqueIdentifiant() {
        this.noEtape = 0;
        this.noPdc = 0;
    }

    public static FabriqueIdentifiant getInstance() {
        return instance;
    }

    public String getIdentifiantEtape() {
        return "" + this.noEtape++;
    }

    public String getIdentifiantPdc() {
        return "" + this.noPdc++;
    }

    public void reset() {
        this.noEtape = 0;
    }
}
