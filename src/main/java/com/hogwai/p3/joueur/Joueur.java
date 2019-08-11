package com.hogwai.p3.joueur;

import com.hogwai.p3.mode.Mode.ModeName;

import java.util.List;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Joueur: classe mère (IAHandler / UtilisateurHandler)
 * @author Lilian
 */
public abstract class Joueur {
    /**
     * Constante pour la chaîne "config"
     */
    private static final String CONFIG = "config";

    /**
     * Liste contenant chaque chiffre de la combinaison du joueur
     * @see Joueur#setCombinaison(List)
     * @see Joueur#getCombinaison()
     */
    protected LinkedList<Integer> combinaison;

    /**
     * Mode
     * @see Joueur#getMode()
     */
    protected ModeName mode;

    /**
     * Nombre de chiffres autorisé dans une combinaison
     * @see ResourceBundle#getBundle(String)
     */
    protected String nbCombinaison = ResourceBundle.getBundle(CONFIG).getString("nbCombinaison");

    /**
     * Nombre d'essais maximum dans une partie
     * @see ResourceBundle#getBundle(String)
     */
    protected String nbEssais = ResourceBundle.getBundle(CONFIG).getString("nbEssais");

    /**
     * Activation du mode développeur
     * @see ResourceBundle#getBundle(String)
     */
    protected boolean modeDev = ResourceBundle.getBundle(CONFIG).getString("modeDev").equals("true");

    /**
     * Retourne le mode joué
     * @return Mode
     */
    public ModeName getMode(){
        return this.mode;
    }

    /**
     * Définit la combinaison du joueur
     * @param combinaison Combinaison du joueur
     */
    public void setCombinaison(List<Integer> combinaison) {
        this.combinaison = (LinkedList<Integer>) combinaison;
    }

    /**
     * Retourne la combinaison du joueur
     * @return Combinaison du joueur
     */
    public List<Integer> getCombinaison() {
        return combinaison;
    }

    /**
     * Prend en paramètre un nombre entier et le transforme en liste d'entiers
     * @param nbToList Nombre entier
     * @return Liste d'entiers
     */
    List<Integer> getListFromInt(int nbToList) {
        LinkedList<Integer> listFromNb = new LinkedList<>();
        while (nbToList > 0) {
            listFromNb.push(nbToList % 10);
            nbToList /= 10;
        }
        return listFromNb;
    }

    /**
     * Retourne la combinaison de l'utilisateur sous forme d'entier
     * @return Combinaison
     */
    public Integer getCombiInt(){
        return this.getIntFromList(this.combinaison);
    }

    /**
     * Prend en paramètre une liste d'entiers et la transforme en nombre entier
     * @param listToBeParsed Liste d'entiers
     * @return Nombre entier
     */
    Integer getIntFromList(LinkedList<Integer> listToBeParsed){
        String nbStr = "";
        for (Integer nbList: listToBeParsed) {
            nbStr = nbStr.concat(nbList.toString());
        }
        return Integer.parseInt(nbStr);
    }

    /**
     * Prend en paramètre une liste et la transforme en chaîne de caractères
     * @param listToConcat Liste
     * @return chaîne
     */
    public String getStringFromList(List<String> listToConcat){
        String strConcat = "";
        for (String str : listToConcat) {
            strConcat = strConcat.concat(str);
        }
        return strConcat;
    }
}
