package com.bastien;

import com.bastien.service.StrategieService;
import com.bastien.service.impl.StrategieServiceImpl;


public class App 
{
    public static void main( String[] args )
    {
        StrategieService strategieService = new StrategieServiceImpl();
        strategieService.recupDonnéesFichiers();
    }
}
