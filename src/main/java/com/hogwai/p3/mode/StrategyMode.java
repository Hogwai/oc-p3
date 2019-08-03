package com.hogwai.p3.mode;

import com.hogwai.p3.joueur.IAHandler;
import com.hogwai.p3.joueur.UtilisateurHandler;

import java.util.List;

public interface StrategyMode {

    /**
     * Affiche le menu du mode choisi
     */
    void afficherMenuMode();

    /**
     * Lance le jeu selon le mode choisi
     * @param utilisateur Utilisateur
     * @param ia Intelligence artificielle
     */
    void jouer(UtilisateurHandler utilisateur, IAHandler ia);

    /**
     * Détermine si la liste d'indices passée en paramètre constitue une victoire.
     * @param clues liste d'indices
     * @return true si victoire, faux sinon
     */
    default boolean gagnerPartie(List<String> clues) {
        return clues.get(0).equals("=") && clues.stream().allMatch(e -> e.equals(clues.get(0)));
    }
}
