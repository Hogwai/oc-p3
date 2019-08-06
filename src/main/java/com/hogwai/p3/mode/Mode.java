package com.hogwai.p3.mode;

import java.util.ResourceBundle;

/**
 * Mode: classe abstraite mère (ChallengerStrategy / DefenseurStrategy / DuelStrategy)
 */
public abstract class Mode {
    /**
     * Properties
     */
    private ResourceBundle props = ResourceBundle.getBundle("config");

    /**
     * Nombre de chiffres d'une combinaison
     * @see Mode#getNbCombinaison()
     */
    protected String nbCombinaison = props.getString("nbCombinaison");

    /**
     * Nombre d'essais d'une partie
     * @see Mode#getNbEssais()
     */
    protected String nbEssais = props.getString("nbEssais");

    /**
     *  Activation du mode développeur
     * @see Mode#isModeDev()
     */
    protected boolean modeDev = props.getString("modeDev").equals("true");


    /**
     * Retourne le nombre de chiffres autorisé dans une combinaison
     * @return Nombre de chiffres dans une combinaison
     */
    public String getNbCombinaison() {
        return nbCombinaison;
    }

    /**
     * Retourne le nombre d'essais maximum d'une partie
     * @return Nombre d'essais
     */
    public String getNbEssais() {
        return nbEssais;
    }

    /**
     * Retourne l'activation du mode développeur
     * @return true si active, false sinon
     */
    public boolean isModeDev() {
        return modeDev;
    }

    /**
     * Noms des différents modes
     */
    public enum ModeName {
        /**
         * Challenger
         */
        CHALLENGER("Challenger"),

        /**
         * Défenseur
         */
        DEFENSEUR("Défenseur"),

        /**
         * Duel
         */
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
