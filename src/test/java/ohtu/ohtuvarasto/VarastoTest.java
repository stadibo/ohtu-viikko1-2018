package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void uselessStorageUnitCanBeCreated() {
        varasto = new Varasto(0.0);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void uselessStorageUnitCanBeCreatedWithZeroSaldo() {
        varasto = new Varasto(0.0, -10.0);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void initialSaldoNotBiggerThanVolume() {
        varasto = new Varasto(4.0, 5.0);
        assertEquals(4.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void storageUnitWithLegalSaldoLeavesCorrectAmountOfSpaceLeft() {
        varasto = new Varasto(4.0, 2.0);
        assertEquals(2.0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void addingNegativeAmountToStorageUnitChangesNothing() {
        varasto.lisaaVarastoon(-1);
        assertEquals(10.0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void addingMoreThanCapacityFillsStorageAndDiscardsTheRest() {
        varasto.lisaaVarastoon(12.0);
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void removingNegativeAmountReturnsZero() {
        assertEquals(0.0, varasto.otaVarastosta(-1.0), vertailuTarkkuus);
    }
    
    @Test
    public void removingMoreThanSaldoEmptiesStorageUnit() {
        varasto.lisaaVarastoon(4.0);
        assertEquals(4.0, varasto.otaVarastosta(10.0), vertailuTarkkuus);
    }
    
    @Test
    public void toStringPrintsCorrectInformation() {
        varasto.lisaaVarastoon(3.0);
        assertEquals("saldo = 4.0, vielä tilaa 6.0", varasto.toString());
    }

}