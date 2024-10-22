package com.bastien.model;

public class Aventurier {
    private String name;

    private int positionEO;

    private int positionNS; 

    private char orientation;

    private String deplacement;

    public Aventurier(String name, int positionEO, int positionNS, char orientation, String deplacement) {
        this.name = name;
        this.positionEO = positionEO;
        this.positionNS = positionNS;
        this.orientation = orientation;
        this.deplacement = deplacement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositionEO() {
        return positionEO;
    }

    public void setPositionEO(int positionEO) {
        this.positionEO = positionEO;
    }

    public int getPositionNS() {
        return positionNS;
    }

    public void setPositionNS(int positionNS) {
        this.positionNS = positionNS;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public String getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(String deplacement) {
        this.deplacement = deplacement;
    }

    public void deplacementGauche() {
        if(this.orientation == 'N'){
            this.orientation = 'O';
        } else if(this.orientation == 'O'){
            this.orientation = 'S';
        } else if(this.orientation == 'S'){
            this.orientation = 'E';
        }else if(this.orientation == 'E'){
            this.orientation = 'N';
        }
    }

    public void deplacementDroite() {
        if(this.orientation == 'N'){
            this.orientation = 'E';
        } else if(this.orientation == 'E'){
            this.orientation = 'S';
        } else if(this.orientation == 'S'){
            this.orientation = 'O';
        }else if(this.orientation == 'O'){
            this.orientation = 'N';
        }
    }

    public int[] deplacementTheorique(){
        return null;
    }

}
