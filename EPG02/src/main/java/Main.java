package main.java;

import java.util.HashSet;

public class Main {

    public static void main(String[] args){

        VendaImoveis vi = new VendaImoveis("./src/main/resources/Vizinhanca2.csv");

        HashSet<String> imoveis = new HashSet<>();


        imoveis.add("I1");
        imoveis.add("I2");
        imoveis.add("I3");
        imoveis.add("I4");

        imoveis.add("I5");
        imoveis.add("I6");
        imoveis.add("I7");
        imoveis.add("I8");

       /* System.out.println(vi.distrito.edgeSet());
        System.out.println(vi.distrito.vertexSet());*/

        String imovel =
                vi.localizaImovel("CAMPO", imoveis);

        System.out.println(imovel);

    }

}
