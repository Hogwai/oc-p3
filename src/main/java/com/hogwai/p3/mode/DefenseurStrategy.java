package com.hogwai.p3.mode;

import com.hogwai.p3.joueur.IAHandler;
import com.hogwai.p3.joueur.UtilisateurHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe gestionnaire du mode Défenseur
 */
public class DefenseurStrategy extends Mode implements StrategyMode {
    private static final Logger LOGGER = LogManager.getLogger(DefenseurStrategy.class.getName());

    public DefenseurStrategy(Boolean modeDev) {
        if (Boolean.TRUE.equals(modeDev)) {
            this.modeDev = true;
        }
    }

    @Override
    public void afficherMenuMode() {
        LOGGER.info(String.format("Affichage du sous-menu du mode %s", ModeName.DEFENSEUR));
        System.out.printf("Bienvenue dans le mode %s!%n", ModeName.DEFENSEUR);
        System.out.printf("Votre objectif: Définir une combinaison de %s chiffres que le système tentera de deviner en %s essais%n",
                super.getNbCombinaison(), super.getNbEssais());
        System.out.println();
        //Mode développeur: ON
        if (this.modeDev) {
            System.out.println("Le mode développeur est activé");
            System.out.println();
        }
        System.out.println("A chaque tentative incorrecte de l'intelligence artificielle, vous lui donnerez un indice sous cette forme:");
        System.out.println("Réponse: -=+-");
        System.out.println();
        System.out.println("Signification:");
        System.out.println("+: chiffre supérieur à celui proposé par le système");
        System.out.println("-: chiffre inférieur à celui proposé par le système");
        System.out.println("=: chiffre correct");
    }

    @Override
    public void jouer(UtilisateurHandler utilisateur, IAHandler ia) {
        String clues;
        boolean win = false;
        Integer solution = utilisateur.getUserInputInt();
        ia.generateRandCombi();
        //Mode développeur: ON
        if (this.modeDev) {
            LOGGER.debug(String.format("Solution: %d", solution));
            System.out.printf("Solution: %d%n", solution);
        }

        for (int i = 0; i < Integer.parseInt(super.getNbEssais()); i++) {
            clues = utilisateur.getUserInputString(ia.getCombinaison());
            //Mode développeur: ON
            if (this.modeDev) {
                LOGGER.debug(String.format("Indices donnés par l'utilisateur: %s", clues));
            }
            ia.compareGuessesTo(clues);
            //Mode développeur: ON
            if (this.modeDev) {
                LOGGER.debug(String.format("Proposition de l'intelligence artificielle: %d", ia.getCombiInt()));
            }
            if (ia.getCombiInt().equals(solution)) {
                //Mode développeur: ON
                if (this.modeDev) {
                    LOGGER.debug(String.format("Victoire de l'intelligence artificielle. Nombre de tentatives: %d", i));
                }
                win = true;
                break;
            }
        }
        if (win) {
            System.out.printf("L'intelligence artificielle a deviné votre combinaison: %d", solution);
            System.out.println();
            System.out.println();
        } else {
            LOGGER.info("Défaite de l'intelligence artificielle.");
            System.out.printf("L'intelligence artificielle n'est pas parvenue à deviner votre combinaison: %d", solution);
            System.out.println();
            System.out.println();
        }
    }
}
