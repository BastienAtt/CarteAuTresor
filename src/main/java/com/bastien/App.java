package com.bastien;


import com.bastien.service.StrategieService;
import com.bastien.service.impl.StrategieServiceImpl;


public class App 
{
    public static void main( String[] args )
    {
        StrategieService strategieService = new StrategieServiceImpl();
        String cheminFichier = "";
        try {
            strategieService.recupDonneesFichiers(cheminFichier);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
