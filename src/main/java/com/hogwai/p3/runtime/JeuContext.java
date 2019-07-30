package com.hogwai.p3.runtime;

import com.hogwai.p3.mode.StrategyMode;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JeuContext {
    private static final Logger LOGGER = LogManager.getLogger(JeuContext.class.getName());

    private StrategyMode strategyMode;
    private String nbCombinaison;
    private String nbEssais;
    private boolean modeDev;
    private boolean playAgain;
    private String playAgainChoice;

    public JeuContext() {
        ResourceBundle properties = this.getProperties();
        this.playAgain = false;
        this.playAgainChoice = "2";
        this.nbCombinaison = properties.getString("nbCombinaison");
        this.nbEssais = properties.getString("nbEssais");
        this.modeDev = properties.getString("modeDev").equals("true");
        LOGGER.info("Lancement d'une instance de jeu");
        LOGGER.info(String.format("Nombre de chiffres dans une combinaison: %s", this.nbCombinaison));
        LOGGER.info(String.format("Nombre d'essais: %s", this.nbEssais));
        LOGGER.info(String.format("Mode développeur: %s", this.modeDev ? "activé" : "désactivé"));
    }

    public void setStrategyMode(StrategyMode strategyMode) {
        this.strategyMode = strategyMode;
    }

    public StrategyMode getStrategyMode() {
        return strategyMode;
    }

    public ResourceBundle getProperties() {
        try {
            ResourceBundle.getBundle("config");
        } catch (MissingResourceException ex) {
            LOGGER.error(String.format("Une erreur a été levée lors de la récupération des properties:%s", ex));
            return null;
        }
        return ResourceBundle.getBundle("config");
    }

    public void setPlayAgain(boolean playAgain) {
        this.playAgain = playAgain;
    }

    public boolean isPlayAgain() {
        return playAgain;
    }

    public void setPlayAgainChoice(String playAgainChoice) {
        this.playAgainChoice = playAgainChoice;
    }

    public String getPlayAgainChoice() {
        return playAgainChoice;
    }

    public void afficherMenu() {
        LOGGER.info("Affichage du menu");

        System.out.printf("Lancement: Mécanisme de recherche d'une combinaison à %s chiffres%n", this.nbCombinaison);
        System.out.println();
        this.strategyMode.afficherMenuMode();
    }

    public int demanderChoixMode() {
        LOGGER.info("Choix du mode");
        Scanner sc;
        boolean checkSaisie;
        int choix = 0;
        do {
            System.out.println("Veuillez choisir le mode de jeu:");
            System.out.println("Tapez 1 pour Challenger");
            System.out.println("Tapez 2 pour Défenseur");
            System.out.println("Tapez 3 pour Duel");
            sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                choix = sc.nextInt();
                if (!(choix < 1 || choix > 3)) {
                    checkSaisie = true;
                } else {
                    checkSaisie = false;
                    System.out.println("Veuillez saisir un entier compris entre 1 et 3 selon le mode choisi.");
                    LOGGER.trace("Mauvaise saisie de l'utilisateur lors du choix du mode");
                }
            } else {
                checkSaisie = false;
                System.out.println("Veuillez saisir un entier compris entre 1 et 3 selon le mode choisi.");
                LOGGER.trace("Mauvaise saisie de l'utilisateur lors du choix du mode");
            }
        } while (!checkSaisie);
        return choix;
    }

    public void lancerPartie() {
        this.strategyMode.lancerMode();
    }

    public void rejouer(){
        Scanner sc;
        boolean checkSaisie;
        do {
            System.out.println("#============================");
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Rejouer au même mode");
            System.out.println("2. Lancer un autre mode");
            System.out.println("3. Quitter");
            System.out.print("Votre choix: ");
            sc = new Scanner(System.in);
            if (sc.hasNext()) {
                this.playAgainChoice = sc.next();
                if (this.playAgainChoice.length() == 1
                        && this.playAgainChoice.matches("^[1-3]+$")) {
                    checkSaisie = true;
                } else {
                    checkSaisie = false;
                    System.out.println("Votre choix doit être compris entre 1 et 3.");
                    LOGGER.warn("Mauvaise saisie lors du choix de fin de jeu.");
                }
            } else {
                checkSaisie = false;
                System.out.println("Votre choix doit être compris entre 1 et 3.");
                LOGGER.warn("Mauvaise saisie lors du choix de fin de jeu.");
            }
        } while (!checkSaisie);
    }
}
