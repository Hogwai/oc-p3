package com.hogwai.p3;

import com.hogwai.p3.runtime.JeuContext;

/**
 * App: classe d'exécution appelant JeuContext
 */
public class App {
    public static void main(String[] args) {
        JeuContext jeu = new JeuContext(args.length > 0 && args[0].equals("true"));
        do {
            if(!jeu.getPlayAgainChoice().equals("1")){
                jeu.demanderChoixMode();
            }
            jeu.afficherMenu();
            jeu.lancerPartie();
            jeu.rejouer();
            jeu.setPlayAgain(jeu.getPlayAgainChoice().equals("1") || jeu.getPlayAgainChoice().equals("2"));
        } while (jeu.isPlayAgain());
    }
}
