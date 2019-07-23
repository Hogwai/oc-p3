package com.hogwai.p3.joueur;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UtilisateurHandler extends Joueur {
    private static final Logger LOGGER = LogManager.getLogger(UtilisateurHandler.class.getName());

    public UtilisateurHandler() {
        //Constructeur par défaut
    }

    @Override
    public List<Integer> getCombinaison() {
        return super.combinaison;
    }

    public int verifyUserInputInt() {
        Scanner sc;
        boolean checkSaisie = false;
        String propTemp = "";
        do {
            System.out.println("Veuillez saisir votre proposition:");
            sc = new Scanner(System.in);
            if (sc.hasNext()) {
                propTemp = sc.next();
                if (propTemp.matches("^(?=[0-9]+)\\d{4}$")) {
                    checkSaisie = true;
                } else {
                    checkSaisie = false;
                    System.out.printf("Votre proposition doit être composée de %s chiffres.%n", super.nbCombinaison);
                    LOGGER.error("Proposition de l'utilisateur au mauvais format.");
                }
            } else {
                checkSaisie = false;
                System.out.printf("Votre proposition doit être composée de %s chiffres.%n", super.nbCombinaison);
                LOGGER.error("Proposition de l'utilisateur au mauvais format.");
            }
        } while (!checkSaisie);
        this.setCombinaison(super.getListFromInt(Integer.parseInt(propTemp)));
        return Integer.parseInt(propTemp);
    }
}
