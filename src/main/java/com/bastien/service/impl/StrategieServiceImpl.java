package com.bastien.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.bastien.model.Carte;
import com.bastien.model.Case;
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
            //On prépare une mémoire dans les cas ou le C n'est pas la premiere ligne, et que des M T ou A sont déclaré avant. 
            List<String> memoryList = new ArrayList<>();
            String line = controleReadFirstLine(reader, memoryList);
            String[] splitLine= line.split(" - ");
            int sizeOE = Integer.valueOf(splitLine[1]);
            int sizeNS = Integer.valueOf(splitLine[2]);
            carte = new Carte(sizeOE, sizeNS);
            
            //traitement des lignes non C
            boolean outWhile = true;
            while(outWhile){
                //On traite les lignes de la memoryList en priorité 
                if (!memoryList.isEmpty()) {
                    line = memoryList.remove(0);
                } else {
                    line = reader.readLine();
                }
                //if la ligne n'est ni un commentaire ni la taille de la carte
                if(line == null) {
                    outWhile = false;
                }else if(!(line.startsWith("#") || line.startsWith("C"))){
                    //Cas trésor 
                    if(line.startsWith("T")){
                        splitLine= line.split(" - ");
                        //.replace("\u200B", "") supprime le zero width space problématique voir le test unitaire
                        Case nouvelleCase = new Case(false,splitLine[0].replace("\u200B", ""),Integer.valueOf(splitLine[3].trim()));
                        carte.addInMap(nouvelleCase, Integer.valueOf(splitLine[1].trim()), Integer.valueOf(splitLine[2].trim()));
                    } else /* Cas Montagne */ if(line.startsWith("M")){
                        splitLine= line.split(" - ");
                        Case nouvelleCase = new Case(false,splitLine[0].replace("\u200B", ""),0);
                        carte.addInMap(nouvelleCase, Integer.valueOf(splitLine[1].trim()), Integer.valueOf(splitLine[2].trim()));
                    } else /* Cas Aventurier */ if(line.startsWith("A")){
                        
                    }
                }
            }
            



        } catch (FileNotFoundException e) {
            throw new Exception("Fichier Non trouvé, chemin du fichier reçu : " + cheminFichier);
        } catch (IOException e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    private String controleReadFirstLine(BufferedReader reader, List<String> memoryList) throws IOException {
        boolean outWhile = true;
        while (outWhile) {
            try {
                String firstLine = reader.readLine();
                if (firstLine.startsWith("C")) {
                    return firstLine;
                } else {
                    //On enregistre la ligne si elle n'est pas de type C 
                    memoryList.add(firstLine);
                }
            } catch (Exception e) {
                throw new IOException("Le programme n'a pas trouvé de ligne commençant par C, verifier sa présence");
            }
        }
        return "";
    }



}
