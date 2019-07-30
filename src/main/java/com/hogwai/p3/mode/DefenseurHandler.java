package com.hogwai.p3.mode;

import com.hogwai.p3.joueur.IAHandler;
import com.hogwai.p3.joueur.UtilisateurHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefenseurHandler extends Mode implements StrategyMode {
    private static final Logger LOGGER = LogManager.getLogger(ChallengerHandler.class.getName());

    @Override
    public void afficherMenuMode() {
        LOGGER.info(String.format("Affichage du sous-menu du mode %s", ModeName.DEFENSEUR));
        System.out.printf("Bienvenue dans le mode %s!%n", ModeName.DEFENSEUR);
        System.out.printf("Votre objectif: Définir une combinaison de %s chiffres que le système tentera de deviner en %s essais%n",
                super.getNbCombinaison(), super.getNbEssais());
        System.out.println();
        System.out.println("A chaque tentative incorrecte du système, vous lui donnerez un indice sous cette forme:");
        System.out.println("Indice: -=+-");
        System.out.println();
        System.out.println("Signification:");
        System.out.println("+: chiffre supérieur à celui proposé par le système");
        System.out.println("-: chiffre inférieur à celui proposé par le système");
        System.out.println("=: chiffre correct");
    }

    @Override
    public void lancerMode() {
        LOGGER.info(String.format("Lancement du mode %s", ModeName.DEFENSEUR));
        IAHandler ia = new IAHandler();
        UtilisateurHandler utilisateur = new UtilisateurHandler(ModeName.DEFENSEUR);
        System.out.println("Le jeu va se lancer dans 3 secondes...");
        this.timer(3000);
        this.lancerBoucleMode(utilisateur, ia);
    }

    @Override
    public void lancerBoucleMode(UtilisateurHandler utilisateur, IAHandler ia) {
        boolean win = false;
        String clues;
        Integer solution = utilisateur.getUserInputInt();
        ia.generateRandCombi();
        for (int i = 0; i < Integer.parseInt(super.getNbEssais()); i++) {
            clues = utilisateur.getUserInputString(ia.getCombinaison());
            ia.compareGuessesTo(clues);

            if (ia.getCombiInt().equals(solution)){
                win = true;
                LOGGER.info(String.format("Victoire de l'intelligence artificielle. Nombre de tentatives: %d", i));
                break;
            }
        }
        if (win) {
            System.out.printf("L'intelligence artificielle a deviné votre combinaison: %d", solution);
            System.out.println();
        } else {
            System.out.printf("L'intelligence artificielle n'est pas parvenue à deviner votre combinaison: %d", solution);
            System.out.println();
            LOGGER.info("Défaite de l'intelligence artificielle.");
        }
    }
}
