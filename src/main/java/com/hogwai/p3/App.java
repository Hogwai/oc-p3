package com.hogwai.p3;

import com.hogwai.p3.mode.ChallengerStrategy;
import com.hogwai.p3.mode.DefenseurStrategy;
import com.hogwai.p3.mode.DuelStrategy;
import com.hogwai.p3.mode.Mode.ModeName;
import com.hogwai.p3.runtime.JeuContext;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
        JeuContext jeu = new JeuContext(args.length > 0 && args[0].equals("true"));
        ModeName mode = null;
        System.out.printf("#=== Lancement: Mécanisme de recherche d'une combinaison à %s chiffres ===#%n", jeu.getNbCombinaison());
        System.out.println();
        do {
            if(jeu.getPlayAgainChoice().equals("1")){
                jeu.afficherMenu();
                jeu.lancerPartie(mode);
            } else {
                switch ( jeu.demanderChoixMode() ) {
                    case 1:
                        jeu.setStrategyMode(new ChallengerStrategy());
                        mode = ModeName.CHALLENGER;
                        LOGGER.info("Mode choisi: Challenger");
                    break;
                    case 2:
                        jeu.setStrategyMode(new DefenseurStrategy());
                        LOGGER.info("Mode choisi: Défenseur");
                        mode = ModeName.DEFENSEUR;
                        break;
                    case 3:
                        jeu.setStrategyMode(new DuelStrategy());
                        LOGGER.info("Mode choisi: Duel");
                        mode = ModeName.DUEL;
                        break;
                    default:
                        LOGGER.error("Valeur inattendue lors du choix du mode.");
                }
                jeu.afficherMenu();
                jeu.lancerPartie(mode);
            }
            jeu.rejouer();
            if (jeu.getPlayAgainChoice().equals("1")
                    || jeu.getPlayAgainChoice().equals("2")) {
                jeu.setPlayAgain(true);
            } else {
                jeu.setPlayAgain(false);
                System.out.println("A bientôt.");
            }
        } while (jeu.isPlayAgain());

    }
}
