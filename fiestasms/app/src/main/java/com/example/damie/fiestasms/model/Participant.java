package com.example.damie.fiestasms.model;

/**
 * Created by damien on 30.03.17.
 */
public class Participant {
    private String nom;
    private String prenom;
    private String boisson;

    public Participant(String nom, String prenom, String boisson) {
        this.nom = nom;
        this.prenom = prenom;
        this.boisson = boisson;
    }

    public Participant() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getBoisson() {
        return boisson;
    }

    public void setBoisson(String boisson) {
        this.boisson = boisson;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", boisson='" + boisson + '\'' +
                '}';
    }
}
