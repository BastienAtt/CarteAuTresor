package com.bastien.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CaseTest {

    @Test
    public void CaseValueTest(){
        Case montagneCase = new Case();
        montagneCase.setTitre("M");
        montagneCase.setAventurierPresent(false);
        assertEquals("M", montagneCase.getTitre());
        assertEquals(false, montagneCase.isAventurierPresent());

        
        Case tresorCase = new Case(true, "T", 3);
        assertEquals("T", tresorCase.getTitre());
        assertEquals(true, tresorCase.isAventurierPresent());
        assertEquals(3, tresorCase.getNombresTresor());

        
    }

}
