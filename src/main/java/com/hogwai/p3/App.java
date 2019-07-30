package com.hogwai.p3;

import com.hogwai.p3.mode.ChallengerHandler;
import com.hogwai.p3.mode.DefenseurHandler;
import com.hogwai.p3.mode.DuelHandler;
import com.hogwai.p3.runtime.JeuContext;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
        JeuContext jeu = new JeuContext();
        do {
            if(jeu.getPlayAgainChoice().equals("1")){
                jeu.afficherMenu();
                jeu.lancerPartie();
            } else {
                switch ( jeu.demanderChoixMode() ) {
                    case 1:
                        jeu.setStrategyMode(new ChallengerHandler());
                        if (LOGGER.isDebugEnabled()) LOGGER.debug("Mode choisi: Challenger");
                        break;
                    case 2:
                        jeu.setStrategyMode(new DefenseurHandler());
                        LOGGER.info("Mode choisi: Défenseur");
                        break;
                    case 3:
                        jeu.setStrategyMode(new DuelHandler());
                        LOGGER.info("Mode choisi: Duel");
                        break;
                    default:
                        LOGGER.error("Valeur inattendue lors du choix du mode.");
                }
                jeu.afficherMenu();
                jeu.lancerPartie();
            }
            jeu.rejouer();
            if (jeu.getPlayAgainChoice().equals("1")
                    || jeu.getPlayAgainChoice().equals("2")) {
                jeu.setPlayAgain(true);
            } else {
                jeu.setPlayAgain(false);
            }
        } while (jeu.isPlayAgain());

    }
}
