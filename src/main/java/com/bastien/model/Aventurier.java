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

    /**
     * 
     * @return la position EO puis la position NS théorique apres le fait d'avancer avant de vérifier que le déplacement est valide
     * @throws Exception 
     */
    public int[] deplacementTheorique() throws Exception{
        switch (this.orientation) {
            case 'N':
                int[] retourN = {this.positionEO,this.positionNS-1}; 
                return retourN;
            case 'S':
                int[] retourS = {this.positionEO,this.positionNS+1}; 
                return retourS;
            case 'E':
               int[] retourE = {this.positionEO+1,this.positionNS}; 
                return retourE;
            case 'O':
                int[] retourO = {this.positionEO-1,this.positionNS}; 
                return retourO;
            default:
                throw new Exception("Orientation inconnue");
        }
    }

}
