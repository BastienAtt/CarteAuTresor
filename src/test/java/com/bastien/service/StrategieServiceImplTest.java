package com.bastien.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bastien.model.Carte;
import com.bastien.service.impl.StrategieServiceImpl;

public class StrategieServiceImplTest {

    @Test
    public void recupDonneesFichiersTest() throws Exception{
        StrategieService strategieService = new StrategieServiceImpl();
        String cheminFichier = "src\\test\\java\\com\\bastien\\testFichier\\testCreationCarteSize.txt";
        strategieService.recupDonneesFichiers(cheminFichier);
        Carte carte = strategieService.getCarte();
        assertEquals(carte.getMap().length, 3);
        assertEquals(carte.getMap()[0].length, 4);
    }

    @Test(expected = Exception.class)
    public void fichierEntreeVideExceptionTest() throws Exception {
        StrategieService strategieService = new StrategieServiceImpl();
        String cheminFichier = "src\\test\\java\\com\\bastien\\testFichier\\testCreationCarteVide.txt";
        strategieService.recupDonneesFichiers(cheminFichier);
    }

    /**
     * C - 3 - 4
     * M​ - 1 - 0 
     * M​ - 2 - 1 
     * T​ - 0 - 3 - 2 
     * T​ - 1 - 3 - 3 
     * A​ - Lara - 1 - 1 - S - AADADAGGA 
     **/
    @Test
    public void fichierEntreeCompletMontagneEtTresorTest() throws Exception {
        StrategieService strategieService = new StrategieServiceImpl();
        String cheminFichier = "src\\test\\java\\com\\bastien\\testFichier\\testCreationCarteMontagneEtTresor.txt";
        strategieService.recupDonneesFichiers(cheminFichier);
        Carte carte = strategieService.getCarte();
        assertEquals("M", carte.getMap()[1][0].getTitre());
        assertEquals("M", carte.getMap()[2][1].getTitre());
        assertEquals(0, carte.getMap()[1][0].getNombresTresor());
        assertEquals(0, carte.getMap()[2][1].getNombresTresor());
        assertEquals("T", carte.getMap()[0][3].getTitre());
        assertEquals("T", carte.getMap()[1][3].getTitre());
        assertEquals(2, carte.getMap()[0][3].getNombresTresor());
        assertEquals(3, carte.getMap()[1][3].getNombresTresor());

    }

}
