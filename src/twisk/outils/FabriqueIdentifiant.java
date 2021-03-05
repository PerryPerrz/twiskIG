package twisk.outils;

public class FabriqueIdentifiant {
    private int noEtape;

    private FabriqueIdentifiant(){
        this.noEtape = 0;
    }
    private static final FabriqueIdentifiant instance = new FabriqueIdentifiant();

    public static FabriqueIdentifiant getInstance(){
        return instance;
    }

    public String getIdentifiantEtape(){
        return "" + this.noEtape++;
    }
}
