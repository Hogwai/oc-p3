package com.hogwai.p3.joueur;

import java.util.*;

public class IAHandler extends Joueur {

    /**
     * Constructeur par défaut
     */
    public IAHandler() {
        //Constructeur par défaut
    }

    /**
     * Génère une combinaison aléatoire
     */
    public void generateRandCombi() {
        LinkedList<Integer> listDigitsCombi = new LinkedList<>();
        for (int i = 0; i < Integer.parseInt(super.nbCombinaison); i++) {
            listDigitsCombi.add(i, new Random().nextInt(10 - 1) + 1);
        }
        super.setCombinaison(listDigitsCombi);
    }

    /**
     *
     * @param combinaison Combinaison à comparer avec celle de l'IA
     *
     * @return Chaîne de caractère contenant les indices générés par l'IA
     */
    public List<String> compareCombiTo(int combinaison) {
        List<String> result = new ArrayList<>();
        List<Integer> listDigitsCombi = super.getListFromInt(combinaison);
        for (int i = 0; i < listDigitsCombi.size(); i++) {
            if (super.combinaison.get(i).compareTo(listDigitsCombi.get(i)) > 0) {
                result.add("+");
            } else if (super.combinaison.get(i).compareTo(listDigitsCombi.get(i)) < 0) {
                result.add("-");
            } else {
                result.add("=");
            }
        }
        return result;
    }

    /**
     * Ajuste la proposition de l'IA par rapport
     * aux indices donnés par l'utilisateur
     * @param clue Indice
     */
    public void compareGuessesTo(String clue) {
        for (int i = 0; i < clue.length(); i++) {
            if (String.valueOf(clue.charAt(i)).equals("+")) {
                this.combinaison.set(i, this.combinaison.get(i) + 1);
            } else if (String.valueOf(clue.charAt(i)).equals("-")) {
                this.combinaison.set(i, this.combinaison.get(i) - 1);
            }
        }
    }
}
