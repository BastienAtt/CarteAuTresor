package com.bastien.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarteTest {

    
    @Test
    public void creationCarteTaille5by4Test() {
        int sizeI = 5;
        int sizeJ = 4;
        Carte carte = new Carte(sizeI,sizeJ);
        assertEquals(sizeI,carte.getMap().length);
        assertEquals(sizeJ,carte.getMap()[0].length);
    }

    @Test
    public void creationCarteTaille5by4EtRemplissageTest() {
        int sizeI = 5;
        int sizeJ = 4;
        Carte carte = new Carte(sizeI,sizeJ);
        carte.getMap()[3][3] = new Case(false,"M");
        assertEquals("M",carte.getMap()[3][3].getTitre());
        assertEquals(false,carte.getMap()[3][3].isAventurierPresent());
    }
}
