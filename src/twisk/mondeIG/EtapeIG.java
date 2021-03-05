package twisk.mondeIG;

public abstract class EtapeIG {
    protected String nom;
    protected String identifiant;
    protected int posX;
    protected int posY;
    protected int largeur;
    protected int hauteur;

    public EtapeIG(String nom, String idf, int larg, int haut){
        this.nom = nom;
        this.identifiant = idf;
        this.posX = 0;
        this.posY = 0;
        this.largeur = larg;
        this.hauteur = haut;
    }
}
