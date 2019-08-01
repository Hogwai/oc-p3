package com.hogwai.p3.mode;

import com.hogwai.p3.joueur.IAHandler;
import com.hogwai.p3.joueur.UtilisateurHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;

public class ChallengerStrategy extends Mode implements StrategyMode {
    private static final Logger LOGGER = LogManager.getLogger(ChallengerStrategy.class.getName());

    @Override
    public void afficherMenuMode() {
        LOGGER.info(String.format("Affichage du sous-menu du mode %s", ModeName.CHALLENGER));
        System.out.printf("Bienvenue dans le mode %s!%n", ModeName.CHALLENGER);
        System.out.printf("Votre objectif: Trouver la bonne combinaison en %s essais%n", super.getNbCombinaison());
        System.out.println();
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
        boolean win = false;
        List<String> clues;
        ia.generateRandCombi();
        for (int i = 0; i < Integer.parseInt(super.getNbEssais()); i++) {
            clues = ia.compareCombiTo(utilisateur.getUserInputInt());
            if (this.gagnerPartie(clues)){
                win = true;
                LOGGER.info(String.format("Victoire de l'utilisateur. Nombre de tentatives: %d", i));
                break;
            } else {
                LOGGER.info("Affichage d'un indice");
                System.out.print("Proposition : ");
                utilisateur.getCombinaison().forEach(System.out::print);
                System.out.print(" -> Réponse : ");
                clues.forEach(System.out::print);
                System.out.println();
            }
        }
        if (win) {
            System.out.print("Bravo ! Vous avez trouvé la bonne combinaison: ");
            ia.getCombinaison().forEach(System.out::print);
            System.out.println();
        } else {
            System.out.print("Dommage ! Vous n'avez pas réussi trouvé la bonne combinaison: ");
            ia.getCombinaison().forEach(System.out::print);
            System.out.println();
            LOGGER.info("Défaite de l'utilisateur");
        }
    }
}
