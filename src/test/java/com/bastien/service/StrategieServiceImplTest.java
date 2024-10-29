package com.bastien.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.bastien.model.Aventurier;
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
        //le "M" seul ne valide pas l'égalité, ( Expected [M] but was [M] ) la reduction en chart de 1 charactere corrige le probleme.
        //Il s'agit du charactere 8203 en plus le zero-width space. Retrait de celui ci dans le code Impl
        assertEquals("M", carte.getMap()[1][0].getTitre());
        assertEquals("M", carte.getMap()[2][1].getTitre());
        assertEquals(0, carte.getMap()[1][0].getNombresTresor());
        assertEquals(0, carte.getMap()[2][1].getNombresTresor());
        assertEquals("T", carte.getMap()[0][3].getTitre());  
        assertEquals("T", carte.getMap()[1][3].getTitre());
        assertEquals(2, carte.getMap()[0][3].getNombresTresor());
        assertEquals(3, carte.getMap()[1][3].getNombresTresor());

    }

    //A​ - Lara - 1 - 1 - S - AADADAGGA 
    @Test
    public void fichierEntreeCompletAventurierTest() throws Exception {
        StrategieService strategieService = new StrategieServiceImpl();
        String cheminFichier = "src\\test\\java\\com\\bastien\\testFichier\\testCreationCarteMontagneEtTresor.txt";
        strategieService.recupDonneesFichiers(cheminFichier);
        List<Aventurier> listResult = strategieService.getListDesAventurier();
        assertEquals( 1, listResult.size());
        assertEquals( "AADADAGGA", listResult.get(0).getDeplacement());
        assertEquals( "Lara", listResult.get(0).getName());
        assertEquals( 'S', listResult.get(0).getOrientation());
    }

    /**
     * C - 3 - 4
     * M​ - 1 - 0 
     * M​ - 2 - 1 
     * T​ - 0 - 3 - 2 
     * T​ - 1 - 3 - 3 
     * A​ - Lara - 1 - 1 - S - AADADAGGA 
     * @throws Exception
     *  -> Aucune montagnes sur le trajet. 
     * Lara fait A 1-2, A 1-3, D 'O', A 0-3, D 'N', A 0-2, G 'O', G 'S', A 0-3
     * Position finale 0-3 orientation S
     */
    @Test
    public void simulationTestEmplacementFinal() throws Exception {
        StrategieService strategieService = new StrategieServiceImpl();
        String cheminFichier = "src\\test\\java\\com\\bastien\\testFichier\\testCreationCarteMontagneEtTresor.txt";
        strategieService.recupDonneesFichiers(cheminFichier);
        strategieService.simulation();
        Aventurier lara = strategieService.getListDesAventurier().get(0);
        assertEquals(0, lara.getPositionOE());
        assertEquals(3, lara.getPositionNS());
        assertEquals('S', lara.getOrientation());
    }

    /**
     * -> Lara ne bouge pas depuis 2-3 'S' HIU en deplacement
     * Dorian va : -> A 1-1 -> A 1-1(Montagne en 1-0) -> D 'E'-> A 1-1(Montagne en 2-1) -> D 'S' -> D 'O' -> A 0-1 -> A 0-1(bord de map) -> G 'S' -> A 0-2 -> A 0-3 -> G 'E' -> A 1-3 -> A 1-3(Lara en 2-3)
     * -> Dorian 1-3 'E'
     * @throws Exception
     */
    @Test
    public void simulationTestEmplacementFinalMultiAventurierEtMontage() throws Exception {
        StrategieService strategieService = new StrategieServiceImpl();
        String cheminFichier = "src\\test\\java\\com\\bastien\\testFichier\\testCreationCarteMontagneEtTresorMultiple.txt";
        strategieService.recupDonneesFichiers(cheminFichier);
        strategieService.simulation();
        Aventurier lara = strategieService.getListDesAventurier().get(0);
        assertEquals(2, lara.getPositionOE());
        assertEquals(3, lara.getPositionNS());
        assertEquals('S', lara.getOrientation());
        Aventurier dorian = strategieService.getListDesAventurier().get(1);
        assertEquals(1, dorian.getPositionOE());
        assertEquals(3, dorian.getPositionNS());
        assertEquals('E', dorian.getOrientation());
    }

    /**
     * C - 3 - 4
     * M​ - 1 - 0 
     * M​ - 2 - 1 
     * T​ - 0 - 3 - 2 
     * T​ - 1 - 3 - 3 
     * A​ - Lara - 1 - 1 - S - AADADAGGA 
     * @throws Exception
     *  -> Aucune montagnes sur le trajet. 
     * Lara fait A 1-2, A 1-3 -> T, D 'O', A 0-3 -> T, D 'N', A 0-2, G 'O', G 'S', A 0-3 -> T ===> 3 Trésor
     * Position finale 0-3 orientation S
     */
    @Test
    public void simulationTestTresorCount() throws Exception {
        StrategieService strategieService = new StrategieServiceImpl();
        String cheminFichier = "src\\test\\java\\com\\bastien\\testFichier\\testCreationCarteMontagneEtTresor.txt";
        strategieService.recupDonneesFichiers(cheminFichier);
        strategieService.simulation();
        Aventurier lara = strategieService.getListDesAventurier().get(0);
        assertEquals(3, lara.getNombresTresor());
    }



}
