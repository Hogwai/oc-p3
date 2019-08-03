package com.hogwai.p3.mode;

import com.hogwai.p3.joueur.IAHandler;
import com.hogwai.p3.joueur.UtilisateurHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DuelStrategy extends Mode implements StrategyMode {
    private static final Logger LOGGER = LogManager.getLogger(ChallengerStrategy.class.getName());

    @Override
    public void afficherMenuMode() {
        LOGGER.info(String.format("Affichage du sous-menu du mode %s", ModeName.DUEL));
        System.out.printf("Bienvenue dans le mode %s!%n", ModeName.DUEL);
        System.out.printf("Votre objectif: Vaincre l'IA en devinant la combinaison de %s chiffres en premier %n",
                super.getNbCombinaison());
        System.out.println();
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

        for (int i = 0; i < Integer.parseInt(super.getNbEssais()); i++) {
            LOGGER.info("Affichage des propositions de l'utilisateur et de l'IA.");

            propoUser = utilisateur.getUserInputInt();
            System.out.printf("Votre proposition: %d%n", propoUser);
            System.out.printf("Proposition de l'IA: %d%n", ia.getCombiInt());

            cluesUser = ia.getClues(solution, propoUser);
            cluesIa = ia.getClues(solution, ia.getCombiInt());
            ia.compareGuessesTo(ia.getStringFromList(cluesIa));

            if(this.gagnerPartie(cluesUser) || this.gagnerPartie(cluesIa)){
                System.out.println("Fin de la partie.");
                if(this.gagnerPartie(cluesUser)){
                    LOGGER.info(String.format("Victoire de l'utilisateur. Nombre de tentatives: %d", i));
                    System.out.printf("Victoire de l'utilisateur. Nombre de tentatives: %d%n", i);
                    System.out.println();
                } else {
                    LOGGER.info(String.format("Victoire de l'intelligence artificielle. Nombre de tentatives: %d", i));
                    System.out.printf("Victoire de l'intelligence artificielle. Nombre de tentatives: %d%n", i);
                    System.out.println();
                }
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
            LOGGER.info("Défaite de l'IA et de l'utilisateur.");
            System.out.println();
        }
    }
}
