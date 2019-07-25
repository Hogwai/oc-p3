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

    public int sanitizeUserInputInt() {
        Scanner sc;
        boolean checkSaisie;
        Integer propTemp = 0;
        do {
            System.out.println("Veuillez saisir votre proposition:");
            sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                propTemp = sc.nextInt();
                if (propTemp.toString().length() == Integer.parseInt(super.nbCombinaison)
                        && propTemp.toString().matches("^([1-9]+)")) {
                    checkSaisie = true;
                } else {
                    checkSaisie = false;
                    System.out.printf("Votre proposition doit être composée de %s chiffres compris entre 1 et 9.%n", super.nbCombinaison);
                    LOGGER.warn("Proposition de l'utilisateur au mauvais format.");
                }
            } else {
                checkSaisie = false;
                System.out.printf("Votre proposition doit être composée de %s chiffres compris entre 1 et 9.%n", super.nbCombinaison);
                LOGGER.warn("Proposition de l'utilisateur au mauvais format.");
            }
        } while (!checkSaisie);

        this.setCombinaison(super.getListFromInt(propTemp));
        return propTemp;
    }
}
