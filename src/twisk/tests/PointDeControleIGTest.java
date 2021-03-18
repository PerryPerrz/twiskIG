package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.PointDeControleIG;

import static org.junit.jupiter.api.Assertions.*;

class PointDeControleIGTest {
    PointDeControleIG pdc;

    @BeforeEach
    void setUp() {
        ActiviteIG act = new ActiviteIG("Balançoire","0");
        pdc = new PointDeControleIG("0",act);
    }

    @Test
    void setCentre() {
        pdc.setCentre(0,0);
        assertEquals(pdc.getCentreX(),0);
        assertEquals(pdc.getCentreY(),0);
        pdc.setCentre(10,10);
        assertEquals(pdc.getCentreX(),10);
        assertEquals(pdc.getCentreY(),10);
        pdc.setCentre(50,50);
        assertEquals(pdc.getCentreX(),50);
        assertEquals(pdc.getCentreY(),50);
    }
}