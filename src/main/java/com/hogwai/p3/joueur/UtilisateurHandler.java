package com.hogwai.p3.joueur;

import java.util.List;
import java.util.Scanner;

import com.hogwai.p3.mode.Mode.ModeName;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Classe gestionnaire des intéractions avec l'utilisateur
 */
public class UtilisateurHandler extends Joueur {
    /**
     * Logger
     * @see LogManager#getLogger(String)
     */
    private static final Logger LOGGER = LogManager.getLogger(UtilisateurHandler.class.getName());

    /**
     * Constructeur surchargé
     * @param mode Mode joué
     */
    public UtilisateurHandler(ModeName mode) {
        this.mode = mode;
    }


    @Override
    public List<Integer> getCombinaison() {
        return super.combinaison;
    }

    /**
     * Retourne une chaîne en fonction du mode
     * @return chaîne
     */
    public String getStrFromMode(){
        if(this.mode.equals(ModeName.CHALLENGER)
                || this.mode.equals(ModeName.DUEL)){
            return "proposition";
        } else {
            return "solution";
        }
    }

    /**
     * Demande la proposition/solution de l'utilisateur sous forme d'entier, contrôle la saisie et la renvoie
     * @return Proposition ou solution
     * @see UtilisateurHandler#getStrFromMode()
     */
    public int getUserInputInt() {
        Scanner sc;
        boolean checkSaisie;
        int propTemp = 0;
        String strMode = this.getStrFromMode();
        do {
            System.out.printf("Veuillez saisir votre %s: ", strMode);
            sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                propTemp = sc.nextInt();
                if (Integer.toString(propTemp).length() == Integer.parseInt(super.nbCombinaison)
                        && Integer.toString(propTemp).matches("^([1-9]+)")) {
                    checkSaisie = true;
                } else {
                    checkSaisie = false;
                    System.out.printf("Votre %s doit être composée de %s chiffres compris entre 1 et 9.%n", strMode, super.nbCombinaison);
                    LOGGER.warn("Proposition de l'utilisateur au mauvais format.");
                    System.out.println();
                }
            } else {
                checkSaisie = false;
                System.out.printf("Votre %s doit être composée de %s chiffres compris entre 1 et 9.%n", strMode, super.nbCombinaison);
                LOGGER.warn("Proposition de l'utilisateur au mauvais format.");
                System.out.println();
            }
        } while (!checkSaisie);
        this.setCombinaison(super.getListFromInt(propTemp));
        return propTemp;
    }

    /**
     * Demande les indices de l'utilisateur sous forme de chaîne, contrôle la saisie et la renvoie
     * @param prop Proposition à afficher
     * @return Indices
     */
    public String getUserInputString(List<Integer> prop) {
        Scanner sc;
        boolean checkSaisie;
        String clueTemp = "";

        do {
            System.out.print("Proposition : ");
            prop.forEach(System.out::print);
            System.out.print(" -> Réponse : ");
            sc = new Scanner(System.in);
            if (sc.hasNext()) {
                clueTemp = sc.next();
                if (clueTemp.length() == Integer.parseInt(super.nbCombinaison)
                        && clueTemp.matches("^[-+=]+$")) {
                    checkSaisie = true;
                } else {
                    checkSaisie = false;
                    System.out.printf("Votre indice doit être composé de %s signes (+/-/=).%n", super.nbCombinaison);
                    LOGGER.warn("Indice de l'utilisateur au mauvais format.");
                    System.out.println();
                }
            } else {
                checkSaisie = false;
                System.out.printf("Votre indice doit être composé de %s signes (+/-/=).%n", super.nbCombinaison);
                LOGGER.warn("Indice de l'utilisateur au mauvais format.");
                System.out.println();
            }
        } while (!checkSaisie);
        return clueTemp;
    }

}
