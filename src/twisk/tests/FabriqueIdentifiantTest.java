package twisk.tests;

import twisk.outils.FabriqueIdentifiant;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * La classe FabriqueIdentifiantTest.
 */
class FabriqueIdentifiantTest {
    /**
     * La fabrique.
     */
    FabriqueIdentifiant fabID;

    /**
     * Sets up.
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        fabID = FabriqueIdentifiant.getInstance();
    }

    /**
     * Gets identifiant etape.
     */
    @org.junit.jupiter.api.Test
    void getIdentifiantEtape() {
        String id = fabID.getIdentifiantEtape();
        assertEquals(id, "0");

        id = fabID.getIdentifiantEtape();
        assertEquals(id, "1");

        id = fabID.getIdentifiantEtape();
        assertEquals(id, "2");

        id = fabID.getIdentifiantEtape();
        assertEquals(id, "3");

        id = fabID.getIdentifiantEtape();
        id = fabID.getIdentifiantEtape();
        id = fabID.getIdentifiantEtape();
        assertEquals(id, "6");
    }
}