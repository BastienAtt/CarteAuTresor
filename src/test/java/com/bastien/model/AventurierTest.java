package com.bastien.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AventurierTest {

    @Test
    public void aventurierCreationTest(){
        Aventurier aventurier = new Aventurier("Test",0,1,'S',"GDAGGA");
        assertEquals("Test", aventurier.getName());
        assertEquals(0,aventurier.getPositionEO());
        assertEquals(1,aventurier.getPositionNS());
        assertEquals('S',aventurier.getOrientation());
        assertEquals("GDAGGA",aventurier.getDeplacement());
        
    }

    /*
     *      N
     *      |
     *  O---+---E
     *      |
     *      S 
     *  
     */

    @Test
    public void aventurierOrientationGaucheTest(){
        Aventurier aventurier = new Aventurier("Test",0,1,'S',"GDAGGA");
        aventurier.deplacementGauche();
        assertEquals('E', aventurier.getOrientation());
        aventurier.deplacementGauche();
        assertEquals('N', aventurier.getOrientation());
        aventurier.deplacementGauche();
        assertEquals('O', aventurier.getOrientation());
        aventurier.deplacementGauche();
        assertEquals('S', aventurier.getOrientation());
    }

    @Test
    public void aventurierOrientationDroiteTest(){
        Aventurier aventurier = new Aventurier("Test",0,1,'S',"GDAGGA");
        aventurier.deplacementDroite();
        assertEquals('O', aventurier.getOrientation());
        aventurier.deplacementDroite();
        assertEquals('N', aventurier.getOrientation());
        aventurier.deplacementDroite();
        assertEquals('E', aventurier.getOrientation());
        aventurier.deplacementDroite();
        assertEquals('S', aventurier.getOrientation());
    }

}
