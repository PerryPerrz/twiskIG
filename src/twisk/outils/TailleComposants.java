package twisk.outils;

public class TailleComposants {
    private static final TailleComposants instance = new TailleComposants();

    private final int larg;
    private final int haut;
    private final int tailleBouton;

    private TailleComposants() {
        this.larg = 50;
        this.haut = 50;
        this.tailleBouton = 30;
    }

    public static TailleComposants getInstance() {
        return instance;
    }

    public int getLarg() {
        return larg;
    }

    public int getHaut() {
        return haut;
    }

    public int getTailleBouton() {
        return tailleBouton;
    }
}
