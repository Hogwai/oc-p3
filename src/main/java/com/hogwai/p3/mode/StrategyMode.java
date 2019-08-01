package com.hogwai.p3.mode;

import com.hogwai.p3.joueur.IAHandler;
import com.hogwai.p3.joueur.UtilisateurHandler;

import java.util.List;

public interface StrategyMode {
    void afficherMenuMode();

    void jouer(UtilisateurHandler utilisateur, IAHandler ia);

    default boolean gagnerPartie(List<String> clues) {
        if(clues.get(0).equals("=") && clues.stream().allMatch(e -> e.equals(clues.get(0)))){
            return true;
        }
        return false;
    }
}
