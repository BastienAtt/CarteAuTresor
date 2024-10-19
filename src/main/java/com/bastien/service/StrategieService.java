package com.bastien.service;


import com.bastien.model.Carte;

public interface StrategieService {
    public void recupDonneesFichiers(String cheminFichier) throws Exception;

    public Carte getCarte();

}
