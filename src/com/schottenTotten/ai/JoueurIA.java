package com.schottenTotten.ai;

import com.schottenTotten.model.Joueur;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.CoupInvalideException;

// Héritage (Slide 55)
public class JoueurIA extends Joueur {

    public JoueurIA(String nom) {
        super(nom); // Appel du constructeur parent (Slide 56)
    }

    // Stratégie simple : joue la première carte sur la première borne disponible
    public void jouerAutomatiquement(Borne[] bornes) {
        if (main.isEmpty()) return;

        Carte c = main.get(0); // Prend la 1ère carte
        
        for (Borne b : bornes) {
            try {
                // Essaie d'ajouter à la borne (supposons que l'IA est joueur 2)
                b.ajouterCarte(c, 2); 
                main.remove(0);
                System.out.println(nom + " a joué " + c + " sur la borne " + b);
                return;
            } catch (CoupInvalideException e) {
                // Si la borne est pleine, on passe à la suivante (catch vide ou log)
            }
        }
    }
}