package com.hogwai.p3.mode;

import com.hogwai.p3.joueur.IAHandler;
import com.hogwai.p3.joueur.UtilisateurHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;

/**
 * Classe gestionnaire du mode Challenger
 */
public class ChallengerStrategy extends Mode implements StrategyMode {
    private static final Logger LOGGER = LogManager.getLogger(ChallengerStrategy.class.getName());

    public ChallengerStrategy(Boolean modeDev) {
        if (Boolean.TRUE.equals(modeDev)) {
            this.modeDev = true;
        }
    }

    @Override
    public void afficherMenuMode() {
        LOGGER.info(String.format("Affichage du sous-menu du mode %s", ModeName.CHALLENGER));
        System.out.printf("Bienvenue dans le mode %s!%n", ModeName.CHALLENGER);
        System.out.printf("Votre objectif: Trouver la bonne combinaison en %s essais%n", super.getNbCombinaison());
        System.out.println();
        //Mode développeur: ON
        if(this.modeDev){
            System.out.println("Le mode développeur est activé");
            System.out.println();
        }
        System.out.println("A chaque tentative incorrecte, le système vous donnera une réponse sous forme d'un indice:");
        System.out.println("Proposition : XXXX -> Réponse : -=+-");
        System.out.println();
        System.out.println("Signification:");
        System.out.println("+: chiffre supérieur à celui proposé");
        System.out.println("-: chiffre inférieur à celui proposé");
        System.out.println("=: chiffre correct");
    }

    @Override
    public void jouer(UtilisateurHandler utilisateur, IAHandler ia) {
        List<String> clues;
        int propoUser;
        boolean win = false;
        ia.generateRandCombi();
        //Mode développeur: ON
        if(this.modeDev){
            LOGGER.debug(String.format("Solution: %d", ia.getCombiInt()));
            System.out.printf("Solution: %d%n", ia.getCombiInt());
        }

        for (int i = 0; i < Integer.parseInt(super.getNbEssais()); i++) {
            propoUser = utilisateur.getUserInputInt();
            //Mode développeur: ON
            if(this.modeDev){
                LOGGER.debug(String.format("Proposition de l'utilisateur: %d", propoUser));
            }
            clues = ia.compareCombiTo(propoUser);
            if (this.gagnerPartie(clues)){
                if(this.modeDev){
                    LOGGER.debug(String.format("Victoire de l'utilisateur. Nombre de tentatives: %d", i + 1));
                }
                win = true;
                break;
            } else {
                //Mode développeur: ON
                if(this.modeDev){
                    LOGGER.debug(String.format("Indices donnés par l'intelligence artificielle: %s", ia.getStringFromList(clues)));
                }
                LOGGER.info("Affichage d'un indice");
                System.out.print("Proposition : ");
                utilisateur.getCombinaison().forEach(System.out::print);
                System.out.print(" -> Réponse : ");
                clues.forEach(System.out::print);

                System.out.println();
                System.out.println();
            }
        }
        if (win) {
            System.out.print("Bravo ! Vous avez trouvé la bonne combinaison: ");
            ia.getCombinaison().forEach(System.out::print);

            System.out.println();
            System.out.println();
        } else {
            LOGGER.info("Défaite de l'utilisateur");
            System.out.print("Dommage ! Vous n'avez pas réussi à trouver la bonne combinaison: ");
            ia.getCombinaison().forEach(System.out::print);

            System.out.println();
            System.out.println();
        }
    }
}
