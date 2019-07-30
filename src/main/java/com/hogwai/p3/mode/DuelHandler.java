package com.hogwai.p3.mode;

import com.hogwai.p3.joueur.IAHandler;
import com.hogwai.p3.joueur.UtilisateurHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DuelHandler extends Mode implements StrategyMode {
    private static final Logger LOGGER = LogManager.getLogger(ChallengerHandler.class.getName());
    @Override
    public void afficherMenuMode() {
        LOGGER.info(String.format("Affichage du sous-menu du mode %s", ModeName.DUEL));
        System.out.printf("Bienvenue dans le mode %s!%n", ModeName.DUEL);
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
        //TODO
    }

    @Override
    public void lancerBoucleMode(UtilisateurHandler utilisateur, IAHandler ia) {
        //TODO
    }
}
