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

    public JeuContext() {
        ResourceBundle properties = this.getProperties();
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
        //TODO
    }
}
