package com.schottenTotten.model;

public class Carte implements Comparable<Carte> {
    // Utilisation de constantes (Slide 14/46) au lieu d'Enums (non vus explicitement)
    public static final String[] COULEURS = {"Rouge", "Bleu", "Vert", "Jaune", "Violet", "Marron"};
    
    private int valeur; // 1 à 9
    private String couleur;

    // Constructeur (Slide 21)
    public Carte(int valeur, String couleur) {
        this.valeur = valeur;
        this.couleur = couleur;
    }

    public int getValeur() { return valeur; }
    public String getCouleur() { return couleur; }

    // Polymorphisme : Redéfinition de toString (Slide 59)
    @Override
    public String toString() {
        return valeur + " " + couleur;
    }

    // Implémentation de l'interface Comparable pour trier les cartes (Slide 83)
    // Utile pour vérifier les "Suites"
    @Override
    public int compareTo(Carte autre) {
        return Integer.compare(this.valeur, autre.valeur);
    }
}