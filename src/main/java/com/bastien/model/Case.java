package com.bastien.model;

public class Case {
    
    private String titre;

    private int nombresTresor;

    private Boolean aventurierPresent;

    //Constructor avec valeur en entrée
    public Case(Boolean aventurierPresent, String titre) {
        this.aventurierPresent = aventurierPresent;
        this.titre = titre;
        this.nombresTresor = 0;  //initialisation à 0 pour chaque case créée si non précisé
    }

    //constructor avec valeur en entrée et initialisation du nombre de trésors
    public Case(Boolean aventurierPresent, String titre, int nombresTresor) {
        this.aventurierPresent = aventurierPresent;
        this.titre = titre;
        this.nombresTresor = nombresTresor;
    }

    //constructor vide
    public Case() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Boolean isAventurierPresent() {
        return aventurierPresent;
    }
    
    public void setAventurierPresent(Boolean aventurierPresent) {
        this.aventurierPresent = aventurierPresent;
    }

    public int getNombresTresor() {
        return nombresTresor;
    }

    public void setNombresTresor(int nombresTresor) {
        this.nombresTresor = nombresTresor;
    }
    
}
