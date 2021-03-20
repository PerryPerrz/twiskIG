package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.exceptions.ArcAlreadyCreateException;
import twisk.exceptions.CreateArcWithEndPdcException;
import twisk.exceptions.SameActivityException;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import static org.junit.jupiter.api.Assertions.*;

class MondeIGTest {
    MondeIG monde;

    @BeforeEach
    void setUp() {
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
        for (EtapeIG etapeIG : monde) {
            assertEquals(etapeIG.getIdentifiant(), "" + cpt);
            cpt++;
        }
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
}