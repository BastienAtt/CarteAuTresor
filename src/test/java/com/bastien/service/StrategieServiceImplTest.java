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
}
