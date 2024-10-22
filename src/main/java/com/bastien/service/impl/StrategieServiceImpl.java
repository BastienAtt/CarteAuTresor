package com.bastien.service.impl;

import java.io.*;
import com.bastien.model.Carte;
import com.bastien.service.StrategieService;

public class StrategieServiceImpl implements StrategieService {

    private Carte carte;

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public void recupDonneesFichiers(String cheminFichier) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            // Premiere ligne :
            String line = controleReadFirstLine(reader);
            String[] splitLine= line.split(" - ");
            int sizeOE = Integer.valueOf(splitLine[1]);
            int sizeNS = Integer.valueOf(splitLine[2]);
            carte = new Carte(sizeOE, sizeNS);

            //ligne apres la premiere 
            
        } catch (FileNotFoundException e) {
            throw new Exception("Fichier Non trouvé, chemin du fichier reçu : " + cheminFichier);
        } catch (IOException e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    private String controleReadFirstLine(BufferedReader reader) throws IOException {
        boolean outWhile = true;
        while (outWhile) {
            try {
                String firstLine = reader.readLine();
                if (firstLine.startsWith("C")) {
                    return firstLine;
                }
            } catch (Exception e) {
                throw new IOException("Le programme n'a pas trouvé de ligne commençant par C, verifier sa présence");
            }
        }
        return "";
    }

}
