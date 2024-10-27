package com.bastien.service;


import java.util.List;

import com.bastien.model.Aventurier;
import com.bastien.model.Carte;

public interface StrategieService {
    public void recupDonneesFichiers(String cheminFichier) throws Exception;

    public Carte getCarte();

    public List<Aventurier> getListDesAventurier();
}
