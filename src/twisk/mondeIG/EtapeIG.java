package twisk.mondeIG;

import java.util.Random;

public abstract class EtapeIG {
    protected String nom;
    protected String identifiant;
    protected int posX;
    protected int posY;

    public EtapeIG(String nom, String idf) {
        this.nom = nom;
        this.identifiant = idf;
        randomPositions();

    }

    public void randomPositions() {
        Random random = new Random();
        this.posX = random.nextInt(650);
        this.posY = random.nextInt(550);
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
