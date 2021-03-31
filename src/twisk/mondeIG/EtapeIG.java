package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;
import twisk.outils.TailleComposants;

import java.util.Iterator;
import java.util.Random;

public abstract class EtapeIG implements Iterable<PointDeControleIG> {
    protected String nom;
    protected String identifiant;
    protected int posX;
    protected int posY;
    protected PointDeControleIG[] pdc;
    protected boolean entree;
    protected boolean sortie;
    protected int delai;
    protected int ecart;

    public EtapeIG(String nom, String idf) {
        this.nom = nom;
        this.identifiant = idf;
        pdc = new PointDeControleIG[4];
        this.entree = false;
        this.sortie = false;
        this.delai = 8;
        this.ecart = 4;
        FabriqueIdentifiant fabrik = FabriqueIdentifiant.getInstance();

        for (int i = 0; i < this.pdc.length; ++i) {
            pdc[i] = new PointDeControleIG(fabrik.getIdentifiantPdc(), this);
        }
        randomPositions();
    }

    public void randomPositions() {
        Random random = new Random();
        TailleComposants tc = TailleComposants.getInstance();
        //On fait en sorte que les activités ne se créent pas sur le menu ni sur le boutton "ajouter"
        this.posX = random.nextInt(tc.getWindowX() - tc.getLargAct() - tc.getRad() * 2) + tc.getRad() * 2;
        this.posY = random.nextInt(tc.getWindowY() - tc.getHautAct() - tc.getTailleBouton() - tc.getTailleIcons2() * 2 - tc.getRad()) + tc.getTailleIcons2() * 2 + tc.getRad();
        this.raffraichissementPdc();
    }

    public void raffraichissementPdc() {
        TailleComposants tc = TailleComposants.getInstance();
        pdc[0].setCentre(this.posX + tc.getLargAct() / 2, this.posY);
        pdc[1].setCentre(this.posX + tc.getLargAct() / 2, this.posY + tc.getHautAct());
        pdc[2].setCentre(this.posX, this.posY + tc.getHautAct() / 2);
        pdc[3].setCentre(this.posX + tc.getLargAct(), this.posY + tc.getHautAct() / 2);
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

    @Override
    public Iterator<PointDeControleIG> iterator() {
        return new Iterator<>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < pdc.length && pdc[i] != null;
            }

            @Override
            public PointDeControleIG next() {
                return pdc[i++];
            }
        };
    }

    public PointDeControleIG getPdcIndex(int indice) {
        return this.pdc[indice];
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPosXPosY(int posX, int posY) {
        TailleComposants tc = TailleComposants.getInstance();
        this.posX = posX;
        this.posY = posY;

        //On raffraichit les points de contrôles lors du drag and drop
        pdc[0].setCentre(this.posX + tc.getLargAct() / 2, this.posY);
        pdc[1].setCentre(this.posX + tc.getLargAct() / 2, this.posY + tc.getHautAct());
        pdc[2].setCentre(this.posX, this.posY + tc.getHautAct() / 2);
        pdc[3].setCentre(this.posX + tc.getLargAct(), this.posY + tc.getHautAct() / 2);
    }

    public void invEntree() {
        this.entree = !this.entree;
    }

    public void invSortie() {
        this.sortie = !this.sortie;
    }

    public int getDelai() {
        return delai;
    }

    public int getEcart() {
        return ecart;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public void setEcart(int ecart) {
        this.ecart = ecart;
    }

    public boolean estUneEntree() {
        return entree;
    }

    public boolean estUneSortie() {
        return sortie;
    }
}
