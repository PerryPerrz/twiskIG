package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.exceptions.ArcAlreadyCreateException;
import twisk.exceptions.CreateArcWithEndPdcException;
import twisk.exceptions.SameActivityException;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.FabriqueIdentifiant;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MondeIGTest {
    MondeIG monde;
    FabriqueIdentifiant fab = FabriqueIdentifiant.getInstance();

    @BeforeEach
    void setUp() {
        fab.reset();
        monde = new MondeIG();
    }

    @Test
    void ajouter() {
        assertEquals(monde.nbEtapes(), 1);
        monde.ajouter("Activite");
        assertEquals(monde.nbEtapes(), 2);
        monde.ajouter("Tourcoing");
        assertEquals(monde.nbEtapes(), 2);
    }

    @Test
    void iterator() {
        int cpt = 0;
        monde.ajouter("Activite");
        for (Iterator<EtapeIG> iter = monde.iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            assertEquals(e.getIdentifiant(), "" + cpt); //Bonnes étapes
            cpt++;
        }
        assertEquals(cpt, 2); //Bons nombres de boucles
        monde.ajouter("Activite");
        monde.ajouterEtapeSelectionnee(monde.getEtapeIndice("1"));
        monde.supprimerLaSelection();
        cpt = 0;
        for (Iterator<EtapeIG> iter = monde.iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            assertEquals(e.getIdentifiant(), "" + cpt);
            cpt += 2;
        }
        assertEquals(cpt, 4);
        monde.ajouter("Activite");
        monde.ajouterEtapeSelectionnee(monde.getEtapeIndice("3"));
        monde.supprimerLaSelection();
        cpt = 0;
        for (Iterator<EtapeIG> iter = monde.iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            assertEquals(e.getIdentifiant(), "" + cpt);
            cpt += 2;
        }
        assertEquals(cpt, 4);
        monde.ajouter("Activite");
        monde.ajouterEtapeSelectionnee(monde.getEtapeIndice("2"));
        monde.supprimerLaSelection();
        cpt = 0;
        for (Iterator<EtapeIG> iter = monde.iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            assertEquals(e.getIdentifiant(), "" + cpt);
            cpt += 4;
        }
        assertEquals(cpt, 8);
    }

    @Test
    void ajouterVue() {
    }

    @Test
    void prevenirVues() {
    }

    @Test
    void testAjouter() {
        monde.ajouter("Activite");
        //Même étape | assertThrows(Nom de la classe qui correspond à l'exception retournée, ()-> cette ligne de code provoque une exception)
        assertThrows(SameActivityException.class, () -> monde.ajouter(monde.getEtapeIndice("0").getPdcIndex(0), monde.getEtapeIndice("0").getPdcIndex(1)));
        assertEquals(monde.getNbArcs(), 0);
        //Même étape même pdc
        assertThrows(SameActivityException.class, () -> monde.ajouter(monde.getEtapeIndice("0").getPdcIndex(0), monde.getEtapeIndice("0").getPdcIndex(0)));
        assertEquals(monde.getNbArcs(), 0);
        //Même étape même pdc
        assertThrows(SameActivityException.class, () -> monde.ajouter(monde.getEtapeIndice("1").getPdcIndex(0), monde.getEtapeIndice("1").getPdcIndex(0)));
        assertEquals(monde.getNbArcs(), 0);
        //Même étape même pdc
        assertThrows(SameActivityException.class, () -> monde.ajouter(monde.getEtapeIndice("1").getPdcIndex(1), monde.getEtapeIndice("1").getPdcIndex(1)));
        assertEquals(monde.getNbArcs(), 0);
        //Etapes différentes, pdc différents
        assertDoesNotThrow(() -> monde.ajouter(monde.getEtapeIndice("0").getPdcIndex(0), monde.getEtapeIndice("1").getPdcIndex(1)));
        assertEquals(monde.getNbArcs(), 1);
        //Etapes différentes, pdc différents
        assertDoesNotThrow(() -> monde.ajouter(monde.getEtapeIndice("0").getPdcIndex(2), monde.getEtapeIndice("1").getPdcIndex(1)));
        assertEquals(monde.getNbArcs(), 2);
        //C'est exactement l'inverse de l'arc précedent
        assertThrows(CreateArcWithEndPdcException.class, () -> monde.ajouter(monde.getEtapeIndice("1").getPdcIndex(1), monde.getEtapeIndice("0").getPdcIndex(2)));
        assertEquals(monde.getNbArcs(), 2);
        //Arc crée avec le point d'arrivée d'un arc déjà crée
        assertThrows(CreateArcWithEndPdcException.class, () -> monde.ajouter(monde.getEtapeIndice("1").getPdcIndex(1), monde.getEtapeIndice("0").getPdcIndex(3)));
        assertEquals(monde.getNbArcs(), 2);
        //Test avec exactement le même arc
        assertThrows(ArcAlreadyCreateException.class, () -> monde.ajouter(monde.getEtapeIndice("0").getPdcIndex(2), monde.getEtapeIndice("1").getPdcIndex(1)));
        assertEquals(monde.getNbArcs(), 2);
    }

    @Test
    void supprimer() {
        monde.ajouter("Activite");
        monde.ajouter("Activite");

        assertEquals(monde.nbEtapes(), 3);
        //On sélectionne l'étape 1 pour pouvoir la selectionner
        monde.ajouterEtapeSelectionnee(monde.getEtapeIndice("1"));
        monde.supprimerLaSelection();
        assertEquals(monde.nbEtapes(), 2);
        assertThrows(NullPointerException.class, () -> monde.getEtapeIndice("1").getIdentifiant());
        assertEquals(monde.getEtapeIndice("0").getIdentifiant(), "0");  //On vérifie que l'étape 0 existe toujours
        assertEquals(monde.getEtapeIndice("2").getIdentifiant(), "2");  //On vérifie que l'étape 2 existe toujours

        //On sélectionne l'étape 0 pour pouvoir la selectionner
        monde.ajouterEtapeSelectionnee(monde.getEtapeIndice("0"));
        monde.supprimerLaSelection();
        assertEquals(monde.nbEtapes(), 1);
        assertThrows(NullPointerException.class, () -> monde.getEtapeIndice("0").getIdentifiant());
        assertEquals(monde.getEtapeIndice("2").getIdentifiant(), "2");  //On vérifie que l'étape 2 existe toujours

        //On sélectionne l'étape 2 pour pouvoir la selectionner
        monde.ajouterEtapeSelectionnee(monde.getEtapeIndice("2"));
        monde.supprimerLaSelection();
        assertEquals(monde.nbEtapes(), 0);
        assertThrows(NullPointerException.class, () -> monde.getEtapeIndice("2").getIdentifiant());
    }
}