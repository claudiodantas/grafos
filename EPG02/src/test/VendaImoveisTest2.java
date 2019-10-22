package test;

import main.java.VendaImoveis;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VendaImoveisTest2 {

    VendaImoveis vi;
    HashSet<String> imoveis;

    @Before
    public void setup(){
        this.vi = new VendaImoveis("./src/main/resources/Vizinhanca2.csv");
        this.imoveis = new HashSet<>();

        this.imoveis.add("I1"); this.imoveis.add("I2"); this.imoveis.add("I3"); this.imoveis.add("I4");
        this.imoveis.add("I5"); this.imoveis.add("I6"); this.imoveis.add("I7"); this.imoveis.add("I8");
    }

    //Imovel mais proximo de CAMPO
    @Test
    public void test1() {
        assertEquals("I1", this.vi.localizaImovel("CAMPO", this.imoveis));

    }

    //Imovel mais proximo de RODOVIARIA
    @Test
    public void test2(){
        assertEquals("I1", this.vi.localizaImovel("RODOVIARIA", this.imoveis));

    }

    //Imovel mais proximo de CLUBE
    @Test
    public void test3(){
        String imovel = this.vi.localizaImovel("CLUBE", this.imoveis);
        assertTrue(imovel.equals("I4")||imovel.equals("I6"));

    }

    //Imovel mais proximo de IGREJA
    @Test
    public void test4(){
        assertEquals("I6", this.vi.localizaImovel("IGREJA", this.imoveis));

    }

    //Imovel para ponto de interesse inexistente
    @Test
    public void test5(){
        assertNull(this.vi.localizaImovel("Salete's House", this.imoveis));
    }


}
