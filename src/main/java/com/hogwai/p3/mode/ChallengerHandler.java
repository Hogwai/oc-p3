package com.hogwai.p3.mode;

import com.hogwai.p3.joueur.IAHandler;
import com.hogwai.p3.joueur.UtilisateurHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;

public class ChallengerHandler extends Mode implements StrategyMode {
    private static final Logger LOGGER = LogManager.getLogger(ChallengerHandler.class.getName());

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
    public void lancerMode() {
        LOGGER.info("Lancement du jeu");
        IAHandler ia = new IAHandler();
        UtilisateurHandler utilisateur = new UtilisateurHandler();

        ia.genererCombinaison();
        System.out.println("Le jeu va se lancer dans 3 secondes...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            LOGGER.error(String.format("Une erreur est survenue lors du décompte avant le lancement de la boucle de jeu: %s", ex));
            Thread.currentThread().interrupt();
        }
        this.lancerBoucleMode(utilisateur, ia);
    }

    @Override
    public void lancerBoucleMode(UtilisateurHandler utilisateur, IAHandler ia) {
        boolean win = false;
        for (int i = 1; i < Integer.parseInt(super.getNbEssais()); i++) {
            List<String> clues = ia.compareCombiToPropo(utilisateur.verifyUserInputInt());
            if (clues.get(0).equals("=") && clues.stream().allMatch(e -> e.equals(clues.get(0)))) {
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
        } else {
            System.out.print("Dommage ! Vous n'avez pas réussi trouvé la bonne combinaison: ");
            ia.getCombinaison().forEach(System.out::print);
        }
    }
}
