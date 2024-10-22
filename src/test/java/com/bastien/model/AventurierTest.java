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

}
