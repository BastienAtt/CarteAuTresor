package com.bastien.model;

public class Aventurier {
    private String name;

    private int positionOE;

    private int positionNS; 

    private char orientation;

    private String deplacement;

    public Aventurier(String name, int positionOE, int positionNS, char orientation, String deplacement) {
        this.name = name;
        this.positionOE = positionOE;
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

    public int getPositionOE() {
        return positionOE;
    }

    public void setPositionOE(int positionOE) {
        this.positionOE = positionOE;
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
                int[] retourN = {this.positionOE,this.positionNS-1}; 
                return retourN;
            case 'S':
                int[] retourS = {this.positionOE,this.positionNS+1}; 
                return retourS;
            case 'E':
               int[] retourE = {this.positionOE+1,this.positionNS}; 
                return retourE;
            case 'O':
                int[] retourO = {this.positionOE-1,this.positionNS}; 
                return retourO;
            default:
                throw new Exception("Orientation inconnue");
        }
    }

}
