package com.bastien;


import com.bastien.service.StrategieService;
import com.bastien.service.impl.StrategieServiceImpl;


public class App 
{
    public static void main( String[] args )
    {
        StrategieService strategieService = new StrategieServiceImpl();
        String cheminFichierEnter = "src\\in.txt";
        String cheminFichierSortie = "src\\out.txt";

        try {
            strategieService.recupDonneesFichiers(cheminFichierEnter);
            strategieService.simulation();
            strategieService.ecriture(cheminFichierSortie);
            System.exit(0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
