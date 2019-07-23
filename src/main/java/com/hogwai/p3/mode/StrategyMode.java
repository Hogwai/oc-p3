package com.hogwai.p3.mode;

import com.hogwai.p3.joueur.IAHandler;
import com.hogwai.p3.joueur.UtilisateurHandler;

public interface StrategyMode {
    void afficherMenuMode();

    void lancerMode();

    void lancerBoucleMode(UtilisateurHandler utilisateur, IAHandler ia);
}
