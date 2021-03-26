package twisk.outils;

public class TailleComposants {
    private static final TailleComposants instance = new TailleComposants();
    private final int larg;
    private final int haut;
    private final int tailleBouton;
    private final int windowX;
    private final int windowY;
    private final int rad;
    private final int largLigne;
    private final int longTri;
    private final int largTri;
    private final int tailleIcons;
    private final int tailleIcons2;

    private TailleComposants() {
        this.larg = 80;
        this.haut = 40;
        this.tailleBouton = 30;
        this.windowX = 700;
        this.windowY = 600;
        this.rad = 6;
        this.largLigne = 2;
        this.longTri = 18;
        this.largTri = 6;
        this.tailleIcons = 50;
        this.tailleIcons2 = 25;
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

    public int getWindowX() {
        return windowX;
    }

    public int getWindowY() {
        return windowY;
    }

    public int getRad() {
        return rad;
    }

    public int getLargLigne() {
        return this.largLigne;
    }

    public int getLongTri() {
        return longTri;
    }

    public int getLargTri() {
        return largTri;
    }

    public int getTailleIcons() {
        return tailleIcons;
    }

    public int getTailleIcons2() {
        return tailleIcons2;
    }
}
