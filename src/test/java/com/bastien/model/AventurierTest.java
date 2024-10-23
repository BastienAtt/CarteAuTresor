package com.bastien.model;

import static org.junit.Assert.assertArrayEquals;
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

    /**
     * Test de la fonction qui renvoi l'emplacement théorique apres mouvement de 1 vers le sud depuis la positionNS 1
     * +
     * Test de la fonction qui deplacement réellement le personnage dans le meme cas
     * @throws Exception 
     */
    @Test
    public void aventurierEmplacementArriver() throws Exception{
        Aventurier aventurier = new Aventurier("Test",0,1,'S',"GDAGGA");
        int[] result = aventurier.deplacementTheorique();
        int[] expected = { 0,2 };
        assertArrayEquals(expected, result);
        aventurier = new Aventurier("Test",0,1,'O',"GDAGGA");
        result = aventurier.deplacementTheorique();
        int[] expected2 = { -1,1 };
        assertArrayEquals(expected2, result);
        aventurier = new Aventurier("Test",0,1,'E',"GDAGGA");
        result = aventurier.deplacementTheorique();
        int[] expected3 = { 1,1 };
        assertArrayEquals(expected3, result);
        aventurier = new Aventurier("Test",0,1,'N',"GDAGGA");
        result = aventurier.deplacementTheorique();
        int[] expected4 = { 0,0 };
        assertArrayEquals(expected4, result);
    }

}
