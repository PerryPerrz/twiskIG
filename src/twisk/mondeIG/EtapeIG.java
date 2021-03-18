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

    public EtapeIG(String nom, String idf) {
        this.nom = nom;
        this.identifiant = idf;
        pdc = new PointDeControleIG[4];
        FabriqueIdentifiant fabrik = FabriqueIdentifiant.getInstance();

        for(int i = 0; i < this.pdc.length ; ++i){
            pdc[i] = new PointDeControleIG(fabrik.getIdentifiantPdc(),this);
        }
        randomPositions();
    }

    public void randomPositions() {
        TailleComposants tc = TailleComposants.getInstance();
        Random random = new Random();
        this.posX = random.nextInt(650);
        this.posY = random.nextInt(550);

        pdc[0].setCentre(this.posX + tc.getLarg()/2,this.posY);
        pdc[1].setCentre(this.posX + tc.getLarg()/2,this.posY + tc.getHaut());
        pdc[2].setCentre(this.posX,this.posY + tc.getHaut()/2);
        pdc[3].setCentre(this.posX + tc.getLarg(),this.posY + tc.getHaut()/2);
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
}
