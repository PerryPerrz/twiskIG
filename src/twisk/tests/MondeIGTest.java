package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        monde.ajouter("Kebab");
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
}