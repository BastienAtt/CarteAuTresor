package com.bastien.model;

public class Carte {
    private Case[][] map;

    /**
     * Constructor
     * @param size
     */
    public Carte(int sizeI,int sizeJ) {
        map = new Case[sizeI][sizeJ];
    }

    /**
     * modifie l'emplacement [i][j]
     * @param type
     * @param i
     * @param j
     */
    public void addInMap(Case casee, int i, int j){
        map[i][j] = casee;
    }

    /**
     * Récupère le type d'objet à l'emplacement [i][j]
     * @param i
     * @param j
     * @return
     */
    public Case getTypeAt(int i, int j){
        return map[i][j];
    }

    // Getters and setters...

    public Case[][] getMap() {
        return map;
    }

    public void setMap(Case[][] map) {
        this.map = map;
    }
    
}
