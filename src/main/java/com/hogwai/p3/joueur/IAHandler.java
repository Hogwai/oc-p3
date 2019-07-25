package com.hogwai.p3.joueur;

import java.util.*;

public class IAHandler extends Joueur {

    public IAHandler() {
        //Constructeur par défaut
    }


    public void genererCombinaison() {
        int range = Integer.parseInt(ResourceBundle.getBundle("config").getString("nbCombinaison"));
        List<Integer> listDigitsCombi = new LinkedList<>();
        for (int i = 0; i < range; i++) {
            listDigitsCombi.add(i, new Random().nextInt(10 - 1) + 1);
        }
        super.setCombinaison(listDigitsCombi);
    }

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
}
