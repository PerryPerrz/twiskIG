package twisk.outils;

public class FabriqueIdentifiant {
    private static final FabriqueIdentifiant instance = new FabriqueIdentifiant();
    private int noEtape;

    private FabriqueIdentifiant() {
        this.noEtape = 0;
    }

    public static FabriqueIdentifiant getInstance() {
        return instance;
    }

    public String getIdentifiantEtape() {
        return "" + this.noEtape++;
    }
}
