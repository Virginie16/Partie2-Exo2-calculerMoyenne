package com.example.partie2exo2calculermoyenne;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    /**
     * Saisie des paramètres du tableau : taille, min, max, affichage du tableau généré aléatoirement, affichage du max trouvé dans ce tableau
     * @param stage je ne sais pas
     * @throws IOException je ne sais pas
     */
    @Override
    public void start(Stage stage) throws IOException {
        int taille = saisirEntier("Veuillez saisir la taille du tableau", "Taille");
        int min = saisirEntier("Veuillez saisir le nombre minimum", "min");
        int max = saisirEntier("Veuillez saisir le nombre maximum", "max");

        int [] TabEntiers = randomTab(taille,min,max);

        // Afficher les nombres du tableau
        StringBuilder nombres = new StringBuilder();
        for (int i = 0; i < taille; i++) {
            nombres.append(TabEntiers[i]).append(" | ");
        }
        afficherBoiteDeDialogue(nombres.toString(), "Tableau");

        // afficher la moyenne des nombres du tableau
        double moy = calculerMoyenne(TabEntiers);
        afficherBoiteDeDialogue("La moyenne est "+ moy,"Moyenne");

    }

    /**
     * Rempli aléatoirement un tableau d'entiers
     * @param size correspond à la taille du tableau
     * @param minInclu correspond à la valeur min dans le tableau
     * @param maxExclu correspond à la valeur max à ne pas dépasser
     * @return retourne le tableau
     */
    public static int []randomTab(int size, int minInclu, int maxExclu){
        int[] tab = new int[size];
        for (int i = 0; i< tab.length; i++){
            tab[i]=(int)(Math.random()*(maxExclu+1-minInclu))+minInclu;
        }
        return tab;
    }


    /**
     * Calculer la moyenne des entiers d'un tableau
     * @param tab argument de type tableau
     * @return retourne la moyenne calculée de type double
     */
    public static double calculerMoyenne(int[] tab){
        int l=tab.length;
        int somme = 0;
        for (int k = 0; k < l; k++) {
            somme = somme+(tab[k]);
            //System.out.println("valeur "+tab[k] +" somme = " + somme + " longueur "+l);
        }
        return (double) somme / l;

    }

    /**
     * permet d'afficher une fenêtre de saisie d'un entier
     * @param message indique à quoi correspond l'entier saisi
     * @param titre titre de la fenêtre
     * @return retourne la fonction saisirEntier
     */
    public static final int saisirEntier(String message, String titre) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(titre);
        dialog.setContentText(message);
        dialog.setHeaderText(null);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                return Integer.parseInt(result.get());

            } catch (NumberFormatException e) {
                // Afficher un message d'erreur si la saisie n'est pas un entier
                Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez entrer un nombre entier valide.", ButtonType.OK);
                alert.setTitle("Erreur de saisie");
                alert.showAndWait();
                return saisirEntier(message, titre); // Répéter la saisie
            }

        }

        return 0; // retourner 0 si l'utilisateur annule la saisie

    }

    /**
     * Permet d'afficher une fenêtre avec des informations
     * @param message concerne le message affiché dans la fenêtre
     * @param titre concerne le titre de la fenêtre
     */
    public static void afficherBoiteDeDialogue(String message, String titre) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static void main(String[] args) {
        launch();
    }
}