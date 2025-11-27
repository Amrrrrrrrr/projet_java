package com.schottenTotten.model;

import java.util.ArrayList;

public class Borne {
    private int id;
    // Composition avec ArrayList (Slide 51)
    private ArrayList<Carte> cartesJ1;
    private ArrayList<Carte> cartesJ2;
    private boolean estRevendiquee;
    private Joueur proprietaire;

    public Borne(int id) {
        this.id = id;
        this.cartesJ1 = new ArrayList<>(); // Slide 39 : Création d'objet
        this.cartesJ2 = new ArrayList<>();
        this.estRevendiquee = false;
    }

    // Méthode lançant une exception (Slide 105)
    public void ajouterCarte(Carte c, int numJoueur) throws CoupInvalideException {
        if (estRevendiquee) {
            throw new CoupInvalideException("Borne déjà revendiquée !");
        }
        
        ArrayList<Carte> cible = (numJoueur == 1) ? cartesJ1 : cartesJ2;
        
        if (cible.size() >= 3) { // Règle de base : max 3 cartes
            throw new CoupInvalideException("Borne pleine !");
        }
        cible.add(c);
    }

    public ArrayList<Carte> getCartesJ1() { return cartesJ1; }
    public ArrayList<Carte> getCartesJ2() { return cartesJ2; }
    
    // TODO: Ajouter ici la logique de calcul de force (Somme, Suite, Brelan...)
    // C'est ici que vous utiliserez compareTo() et des boucles.
    
    @Override
    public String toString() {
        return "Borne " + id + " : J1" + cartesJ1 + " vs J2" + cartesJ2;
    }
}