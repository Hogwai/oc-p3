package com.hogwai.p3.joueur;

import java.util.*;

/**
 * Classe gestionnaire de l'intelligence artificielle
 */
public class IAHandler extends Joueur {

    /**
     * Constructeur par défaut
     */
    public IAHandler() {
        //Constructeur par défaut
    }

    /**
     * Compare la combinaison de l'IA à celle passée en paramètre et renvoie des indices
     * @param combinaison Combinaison à comparer avec celle de l'IA
     * @return Chaîne de caractère contenant les indices générés par l'IA
     * @see IAHandler#getClues(int, int)
     * @see Joueur#getIntFromList(LinkedList)
     */
    public List<String> compareCombiTo(int combinaison) {
        return this.getClues(this.getIntFromList(this.combinaison), combinaison);
    }

    /**
     * Ajuste la proposition de l'IA par rapport aux indices donnés par l'utilisateur
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

    /**
     * Génère une combinaison aléatoire et la stocke dans l'attribut combinaison
     * @see Joueur#combinaison
     */
    public void generateRandCombi() {
        this.combinaison = new LinkedList<>();
        for (int i = 0; i < Integer.parseInt(super.nbCombinaison); i++) {
            this.combinaison.add(i, new Random().nextInt(10 - 1) + 1);
        }
    }

    /**
     * Génère une combinaison aléatoire et la retourne
     * @return Combinaison générée aléatoirement
     * @see Joueur#combinaison
     * @see Joueur#getIntFromList(LinkedList)
     */
    public Integer returnRandCombiInt(){
        LinkedList<Integer> listDigitsCombi = new LinkedList<>();
        for (int i = 0; i < Integer.parseInt(super.nbCombinaison); i++) {
            listDigitsCombi.add(i, new Random().nextInt(10 - 1) + 1);
        }
        return this.getIntFromList(listDigitsCombi);
    }

    /**
     * Compare une solution à une combinaison et renvoie des indices
     * @param solution Solution à comparer avec la combinaison
     * @param combinaison Combinaison à comparer avec la solution
     * @return Chaîne de caractère contenant les indices générés par l'IA
     * @see Joueur#getListFromInt(int)
     */
    public List<String> getClues(int solution, int combinaison) {
        List<String> result = new ArrayList<>();
        List<Integer> listDigitsSolu = this.getListFromInt(solution);
        List<Integer> listDigitsCombi = this.getListFromInt(combinaison);
        for (int i = 0; i < listDigitsSolu.size(); i++) {
            if (listDigitsSolu.get(i).compareTo(listDigitsCombi.get(i)) > 0) {
                result.add("+");
            } else if (listDigitsSolu.get(i).compareTo(listDigitsCombi.get(i)) < 0) {
                result.add("-");
            } else {
                result.add("=");
            }
        }
        return result;
    }
}
