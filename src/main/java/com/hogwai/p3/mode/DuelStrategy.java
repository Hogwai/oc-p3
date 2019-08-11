package com.hogwai.p3.mode;

import com.hogwai.p3.joueur.IAHandler;
import com.hogwai.p3.joueur.UtilisateurHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Classe gestionnaire du mode Duel
 */
public class DuelStrategy extends Mode implements StrategyMode {
    private static final Logger LOGGER = LogManager.getLogger(DuelStrategy.class.getName());

    public DuelStrategy(Boolean modeDev){
        if (Boolean.TRUE.equals(modeDev)) {
            this.modeDev = true;
        }
    }

    @Override
    public void afficherMenuMode() {
        LOGGER.info(String.format("Affichage du sous-menu du mode %s", ModeName.DUEL));
        System.out.printf("Bienvenue dans le mode %s!%n", ModeName.DUEL);
        System.out.printf("Votre objectif: Vaincre l'IA en devinant la combinaison de %s chiffres en premier %n",
                super.getNbCombinaison());
        System.out.println();
        if(this.modeDev){
            System.out.println("Le mode développeur est activé");
            System.out.println();
        }
        System.out.println("A chaque tentative incorrecte, le système vous donnera un indice sous cette forme:");
        System.out.println("Réponse: -=+-");
        System.out.println();
        System.out.println("Signification:");
        System.out.println("+: chiffre supérieur");
        System.out.println("-: chiffre inférieur");
        System.out.println("=: chiffre correct");
    }

    @Override
    public void jouer(UtilisateurHandler utilisateur, IAHandler ia) {
        int propoUser;
        List<String> cluesUser;
        List<String> cluesIa;
        boolean win = false;
        Integer solution = ia.returnRandCombiInt();
        ia.generateRandCombi();
        //Mode développeur: ON
        if(this.modeDev){
            LOGGER.debug(String.format("Solution: %d", solution));
            System.out.printf("Solution: %d%n", solution);
        }

        for (int i = 0; i < Integer.parseInt(super.getNbEssais()); i++) {
            LOGGER.info("Affichage des propositions de l'utilisateur et de l'IA.");
            propoUser = utilisateur.getUserInputInt();
            //Mode développeur: ON
            if(this.modeDev){
                LOGGER.debug(String.format("Proposition de l'utilisateur: %d", propoUser));
                LOGGER.debug(String.format("Proposition de l'intelligence artificielle: %d", ia.getCombiInt()));
            }
            System.out.printf("Votre proposition: %d%n", propoUser);
            System.out.printf("Proposition de l'IA: %d%n", ia.getCombiInt());
            cluesUser = ia.getClues(solution, propoUser);
            cluesIa = ia.getClues(solution, ia.getCombiInt());
            ia.compareGuessesTo(ia.getStringFromList(cluesIa));
            //Mode développeur: ON
            if (this.modeDev) {
                LOGGER.debug(String.format("Indices données à l'utilisateur: %s", utilisateur.getStringFromList(cluesUser)));
                LOGGER.debug(String.format("Indices donnés à l'intelligence artificielle: %s", ia.getStringFromList(cluesIa)));
            }
            if(this.gagnerPartie(cluesUser)){
                if(this.modeDev){
                    LOGGER.debug(String.format("Victoire de l'utilisateur. Nombre de tentatives: %d", i));
                }
                System.out.printf("Victoire de l'utilisateur. Nombre de tentatives: %d%n", i);
                System.out.println();
                win = true;
                break;
            } else if (this.gagnerPartie(cluesIa)) {
                if(this.modeDev){
                    LOGGER.debug(String.format("Victoire de l'intelligence artificielle. Nombre de tentatives: %d", i));
                }
                System.out.printf("Victoire de l'intelligence artificielle. Nombre de tentatives: %d%n", i);
                System.out.println();
                win = true;
                break;
            } else {
                System.out.println();
                System.out.print("Votre indice: ");
                cluesUser.forEach(System.out::print);
                System.out.println();
                System.out.print("Indice de l'IA: ");
                cluesIa.forEach(System.out::print);
                System.out.println();
            }
        }
        if(!win){
            System.out.printf("Vous, ainsi que l'IA n'êtes pas parvenue à deviner la combinaison: %d", solution);
            //Mode développeur: ON
            if(this.modeDev){
                LOGGER.debug(String.format("Défaite de l'IA et de l'utilisateur. Solution %d",solution));
            }
            System.out.println();
        }
    }
}
