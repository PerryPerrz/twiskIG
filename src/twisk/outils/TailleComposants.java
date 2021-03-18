package twisk.outils;

public class TailleComposants {
    private static final TailleComposants instance = new TailleComposants();
    private final int larg;
    private final int haut;
    private final int tailleBouton;
    private final int windowX;
    private final int windowY;
    private final int rad;

    private TailleComposants() {
        this.larg = 80;
        this.haut = 40;
        this.tailleBouton = 30;
        this.windowX = 700;
        this.windowY = 600;
        this.rad = 4;
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
}
