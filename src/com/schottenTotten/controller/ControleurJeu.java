package com.schottenTotten.controller;

import com.schottenTotten.model.*;
import com.schottenTotten.ai.JoueurIA;
import java.util.ArrayList;
import java.util.Collections; // Pour mélanger (cité implicitement slide 82 avec sort)
import java.util.Scanner;

public class ControleurJeu {
    private ArrayList<Carte> pioche;
    private Borne[] bornes; // Tableau (Slide 38)
    private Joueur j1;
    private Joueur j2;

    public ControleurJeu() {
        initialiserPartie();
    }

    private void initialiserPartie() {
        pioche = new ArrayList<>();
        bornes = new Borne[9];
        
        // Création des bornes
        for(int i=0; i<9; i++) bornes[i] = new Borne(i+1);

        // Création des cartes (Slide 13 pour déclaration)
        for(String col : Carte.COULEURS) {
            for(int val=1; val<=9; val++) {
                pioche.add(new Carte(val, col));
            }
        }
        Collections.shuffle(pioche); // Mélange

        // Polymorphisme : J1 humain, J2 IA (Projet Section 1)
        j1 = new Joueur("Humain");
        j2 = new JoueurIA("Ordinateur"); // Slide 64: Type statique Joueur, dynamique JoueurIA

        // Distribution (6 cartes)
        for(int i=0; i<6; i++) {
            j1.piocher(pioche.remove(0));
            j2.piocher(pioche.remove(0));
        }
    }

    public void demarrer() {
        Scanner sc = new Scanner(System.in);
        boolean partieEnCours = true;

        while(partieEnCours && !pioche.isEmpty()) {
            // --- Tour Joueur 1 ---
            System.out.println("\n--- A VOUS ---");
            System.out.println("Main: " + j1.getMain());
            afficherBornes();

            boolean tourValide = false;
            while(!tourValide) {
                try { // Gestion des exceptions (Slide 95)
                    System.out.println("Index carte à jouer (0-5) ?");
                    int idxCarte = Integer.parseInt(sc.nextLine());
                    
                    System.out.println("Numéro borne (1-9) ?");
                    int idxBorne = Integer.parseInt(sc.nextLine()) - 1;

                    if(idxBorne < 0 || idxBorne > 8) throw new CoupInvalideException("Borne hors limites");

                    Carte c = j1.jouerCarte(idxCarte);
                    bornes[idxBorne].ajouterCarte(c, 1);
                    tourValide = true;
                    
                    // Piocher une nouvelle carte
                    if(!pioche.isEmpty()) j1.piocher(pioche.remove(0));

                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre !");
                } catch (CoupInvalideException e) {
                    System.out.println("Erreur : " + e.getMessage()); // Slide 106 exception handling
                }
            }

            // --- Tour Joueur 2 (IA) ---
            System.out.println("\n--- Tour de l'IA ---");
            if(j2 instanceof JoueurIA) { // Opérateur instanceof (Slide 65)
                ((JoueurIA) j2).jouerAutomatiquement(bornes);
                if(!pioche.isEmpty()) j2.piocher(pioche.remove(0));
            }
        }
    }

    private void afficherBornes() {
        for(Borne b : bornes) {
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        new ControleurJeu().demarrer();
    }
}