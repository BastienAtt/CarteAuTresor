package com.bastien.service;

import org.junit.Test;

import com.bastien.service.impl.StrategieServiceImpl;

public class StrategieServiceImplTest {

    @Test
    public void recupDonnéesFichiersTest(){
        StrategieService strategieService = new StrategieServiceImpl();

        strategieService.recupDonnéesFichiers();

        
    }
}
