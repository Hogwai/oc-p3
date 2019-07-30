package com.hogwai.p3.mode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public abstract class Mode {
    private static final Logger LOGGER = LogManager.getLogger(ChallengerHandler.class.getName());
    private static final String CONFIG = "config";

    protected String nbCombinaison = ResourceBundle.getBundle(CONFIG).getString("nbCombinaison");
    protected String nbEssais = ResourceBundle.getBundle(CONFIG).getString("nbEssais");
    protected boolean modeDev = ResourceBundle.getBundle(CONFIG).getString("modeDev").equals("true");

    public String getNbCombinaison() {
        return nbCombinaison;
    }

    public String getNbEssais() {
        return nbEssais;
    }

    public boolean isModeDev() {
        return modeDev;
    }

    public void timer(Integer time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            LOGGER.warn(String.format("Une erreur est survenue lors du décompte avant le lancement de la boucle de jeu: %s", ex));
            Thread.currentThread().interrupt();
        }
    }

    public enum ModeName {
        CHALLENGER("Challenger"),
        DEFENSEUR("Défenseur"),
        DUEL("Duel");

        private String strName;

        ModeName(String name) {
            this.strName = name;
        }

        @Override
        public String toString() {
            return strName;
        }

    }
}
