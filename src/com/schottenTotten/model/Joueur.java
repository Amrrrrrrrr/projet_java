package com.schottenTotten.model;

import java.util.ArrayList;

public class Joueur {
    protected String nom; // protected pour l'héritage (Slide 56)
    protected ArrayList<Carte> main;

    public Joueur(String nom) {
        this.nom = nom;
        this.main = new ArrayList<>();
    }

    public void piocher(Carte c) {
        main.add(c);
    }

    public Carte jouerCarte(int index) throws CoupInvalideException {
        if (index < 0 || index >= main.size()) {
            throw new CoupInvalideException("Carte inexistante");
        }
        return main.remove(index);
    }
    
    public ArrayList<Carte> getMain() { return main; }
    public String getNom() { return nom; }
}