package com.hogwai.p3.joueur;

import com.hogwai.p3.mode.Mode.ModeName;

import java.util.List;
import java.util.LinkedList;
import java.util.ResourceBundle;

public abstract class Joueur {
    private static final String CONFIG = "config";

    protected LinkedList<Integer> combinaison;
    protected ModeName mode;
    protected String nbCombinaison = ResourceBundle.getBundle(CONFIG).getString("nbCombinaison");
    protected String nbEssais = ResourceBundle.getBundle(CONFIG).getString("nbEssais");
    protected boolean modeDev = ResourceBundle.getBundle(CONFIG).getString("modeDev").equals("true");

    public ModeName getMode(){
        return this.mode;
    }

    public void setCombinaison(List<Integer> combinaison) {
        this.combinaison = (LinkedList<Integer>) combinaison;
    }

    public List<Integer> getCombinaison() {
        return combinaison;
    }

    protected List<Integer> getListFromInt(int nbToList) {
        LinkedList<Integer> listFromNb = new LinkedList<>();
        while (nbToList > 0) {
            listFromNb.push(nbToList % 10);
            nbToList /= 10;
        }
        return listFromNb;
    }

    public List<String> getListFromString(String strToList){
        LinkedList<String> listFromStr = new LinkedList<>();
        for (int i = 0; i < strToList.length(); i++){
            listFromStr.push(Character.toString(strToList.charAt(i)));
        }
        return listFromStr;
    }

    public Integer getCombiInt(){
        return this.getIntFromList(this.combinaison);
    }

    public Integer getIntFromList(LinkedList<Integer> listToBeParsed){
        String nbStr = "";
        for (Integer nbList: listToBeParsed) {
            nbStr = nbStr.concat(nbList.toString());
        }
        return Integer.parseInt(nbStr);
    }
}
