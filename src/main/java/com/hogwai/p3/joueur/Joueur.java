package com.hogwai.p3.joueur;

import java.util.List;
import java.util.LinkedList;
import java.util.ResourceBundle;

public abstract class Joueur {
    private static final String CONFIG = "config";

    protected LinkedList<Integer> combinaison;
    protected String nbCombinaison = ResourceBundle.getBundle(CONFIG).getString("nbCombinaison");
    protected String nbEssais = ResourceBundle.getBundle(CONFIG).getString("nbEssais");
    protected boolean modeDev = ResourceBundle.getBundle(CONFIG).getString("modeDev").equals("true");

    public void setCombinaison(List<Integer> combinaison) {
        this.combinaison = (LinkedList<Integer>) combinaison;
    }

    public List<Integer> getCombinaison() {
        return combinaison;
    }

    public List<Integer> getListFromInt(int combinaison) {
        LinkedList<Integer> stack = new LinkedList<>();
        while (combinaison > 0) {
            stack.push(combinaison % 10);
            combinaison /= 10;
        }
        return stack;
    }
}
