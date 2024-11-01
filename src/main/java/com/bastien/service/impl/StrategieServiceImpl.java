package com.bastien.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bastien.model.Aventurier;
import com.bastien.model.Carte;
import com.bastien.model.Case;
import com.bastien.service.StrategieService;

public class StrategieServiceImpl implements StrategieService {

    private static final String ZERO_WIDTH_SPACE = "\u200B";

    private Carte carte;

    private List<Aventurier> listDesAventurier = new ArrayList<>();

    @Override
    public List<Aventurier> getListDesAventurier() {
        return listDesAventurier;
    }

    public void setListDesAventurier(List<Aventurier> listDesAventurier) {
        this.listDesAventurier = listDesAventurier;
    }

    @Override
    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    @Override
    public void recupDonneesFichiers(String cheminFichier) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            // Premiere ligne :
            //On prépare une mémoire dans les cas ou le C n'est pas la premiere ligne, et que des M T ou A sont déclaré avant. 
            List<String> memoryList = new ArrayList<>();
            String line = controleReadFirstLine(reader, memoryList);
            String[] splitLine = line.split(" - ");
            int sizeOE = Integer.parseInt(splitLine[1]);
            int sizeNS = Integer.parseInt(splitLine[2]);
            carte = new Carte(sizeOE, sizeNS);

            //traitement des lignes non C
            boolean outWhile = true;
            while (outWhile) {
                //On traite les lignes de la memoryList en priorité 
                if (!memoryList.isEmpty()) {
                    line = memoryList.remove(0);
                } else {
                    line = reader.readLine();
                }
                //if la ligne n'est ni un commentaire ni la taille de la carte
                if (line == null) {
                    outWhile = false;
                } else if (!(line.startsWith("#") || line.startsWith("C"))) {
                    setLine(line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Fichier Non trouvé, chemin du fichier reçu : " + cheminFichier);
        } catch (IOException e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    /**
     * place dans la carte la ligne correspondante
     * @param line
     */
    private void setLine(String line) {
        String[] splitLine;
        //Cas trésor 
        if (line.startsWith("T")) {
            splitLine = line.split(" - ");
            //.replace("\u200B", "") supprime le zero width space problématique voir le test unitaire
            Case nouvelleCase = new Case(false, splitLine[0].replace(ZERO_WIDTH_SPACE, ""), Integer.parseInt(splitLine[3].trim()));
            carte.addInMap(nouvelleCase, Integer.parseInt(splitLine[1].trim()), Integer.parseInt(splitLine[2].trim()));
        } else /* Cas Montagne */ if (line.startsWith("M")) {
            splitLine = line.split(" - ");
            Case nouvelleCase = new Case(false, splitLine[0].replace(ZERO_WIDTH_SPACE, ""), 0);
            carte.addInMap(nouvelleCase, Integer.parseInt(splitLine[1].trim()), Integer.parseInt(splitLine[2].trim()));
        } else /* Cas Aventurier */ if (line.startsWith("A")) {
            splitLine = line.split(" - ");
            Aventurier newAventurier = new Aventurier(splitLine[1], Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]), splitLine[4].charAt(0), splitLine[5].trim().replace(ZERO_WIDTH_SPACE, ""));
            listDesAventurier.add(newAventurier);
            carte.getMap()[Integer.parseInt(splitLine[2])][Integer.parseInt(splitLine[3])].setAventurierPresent(true);
        }
    }
    /**
     * Trouve la premiere ligne avec le C de la taille de la map
     * @param reader
     * @param memoryList
     * @return
     * @throws IOException
     */
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

    @Override
    public void simulation() {
        //variable de fin de boucle dans les cas ou les aventuriers ont un nombre differents de deplacement
        int deplacementFini = 0;
        while (listDesAventurier.size() > deplacementFini) {
            for (Aventurier aventurier : listDesAventurier) {
                try {
                    if (aventurier.getDeplacement().length() != 0 && !aventurier.isFinished()) {
                        gestionAventurier(aventurier);
                    } else if(!aventurier.isFinished()){
                        deplacementFini++;
                        aventurier.setIsFinished(true);
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors du déplacement de l'aventurier " + aventurier.getName() + " : " + e.getMessage());
                    aventurier.setDeplacement(removefirstChar(aventurier.getDeplacement()));
                }
            }
        }
    }
    /**
     * Gere l'aventurier et le traitement de son déplacement
     * @param aventurier
     * @throws Exception
     */
    private void gestionAventurier(Aventurier aventurier) throws Exception {
        switch (aventurier.getDeplacement().charAt(0)) {
            case 'A' -> deplacementAventurier(aventurier);
            case 'G' -> aventurier.deplacementGauche();
            case 'D' -> aventurier.deplacementDroite();
        }
        //on retire la valeur traité
        aventurier.setDeplacement(removefirstChar(aventurier.getDeplacement()));
    }

    /**
     * Gere le deplacement de l'aventurier
     * @param aventurier
     * @throws Exception
     */
    private void deplacementAventurier(Aventurier aventurier) throws Exception {
        int[] position = aventurier.deplacementTheorique();
        // Si le déplacement est valide
        if (controleDeplacement(position)) {
            // On déplace l'aventurier
            //set de l'ancien emplacement a false
            carte.getMap()[aventurier.getPositionOE()][aventurier.getPositionNS()].setAventurierPresent(false);
            aventurier.setPositionOE(position[0]);
            aventurier.setPositionNS(position[1]);
            carte.getMap()[position[0]][position[1]].setAventurierPresent(true);
            // Si le tresor est present
            if (carte.getTypeAt(position[0], position[1]).getTitre().equals("T")) {
                //on ajoute le tresor a l'aventurier
                aventurier.setNombresTresor(aventurier.getNombresTresor() + 1);
                //On le retire a la map
                carte.getTypeAt(position[0], position[1]).setNombresTresor(carte.getTypeAt(position[0], position[1]).getNombresTresor()-1);
                //Si il n'y a plus de tresor : 
                if(carte.getTypeAt(position[0], position[1]).getNombresTresor()==0){
                    //On retire le titre. 
                    carte.getTypeAt(position[0], position[1]).setTitre("");
                }
            }
        } else {
            // Si le déplacement n'est pas valide
            System.out.println("L'aventurier " + aventurier.getName() + " n'a pas un deplacement valide");
        }
    }

    /**
     * retire le premier charactere d'un String -> utiliser pour retirer le déplacement venant detre fait
     * @param str
     * @return
    */
    private String removefirstChar(String str)
    {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(1);
    }

    private boolean controleDeplacement(int[] position) {
        //controle du bon format de position
        if (position.length != 2) {
            return false;
        }
        //Controle hors map
        if (position[0] < 0) {
            return false;
        }
        if (position[1] < 0) {
            return false;
        }
        if (position[0] >= carte.getMap().length) {
            return false;
        }
        if (position[1] >= carte.getMap()[0].length) {
            return false;
        }
        //controle montagne et présence aventurier 
        Case caseEnCours = carte.getMap()[position[0]][position[1]];
        if ("M".equals(caseEnCours.getTitre())) {
            return false;
        }
        return !caseEnCours.isAventurierPresent();

    }

    @Override
    public void ecriture(String cheminFichier) throws Exception {
        //ecriture des donnees dans le fichier de sortie
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            //ecriture de la carte 
            writer.write("C - " + carte.getMap().length + " - " +carte.getMap()[0].length+"\n");
            //Montagne
            for (int i = 0; i < carte.getMap().length; i++) {
                for (int j = 0; j < carte.getMap()[i].length; j++) {
                    if(carte.getMap()[i][j].getTitre().equals("M")){
                        writer.write("M - " + i + " - " + j +"\n");
                    }
                }
                
            }
            //Tresor
            writer.write("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}\n" );

            for (int i = 0; i < carte.getMap().length; i++) {
                for (int j = 0; j < carte.getMap()[i].length; j++) {
                    if(carte.getMap()[i][j].getTitre().equals("T")){
                        writer.write("T - " + i + " - " + j + " - "+ carte.getMap()[i][j].getNombresTresor() +"\n");
                    }
                }
                
            }
            writer.write("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}\n" );

            //ecriture des aventuriers
            for (Aventurier aventurier : listDesAventurier) {
                writer.write("A - " + aventurier.getName() + " - " + aventurier.getPositionOE() + " - " + aventurier.getPositionNS() + " - " + aventurier.getOrientation() + " - " + aventurier.getNombresTresor() +"\n");
            }

        } catch (FileNotFoundException e) {
            throw new Exception("Fichier Non trouvé, chemin du fichier reçu : " + cheminFichier);
        } catch (IOException e) {
            throw new Exception(e.getMessage(), e);
        }
    }



}
