// Fichier :     Animal.java
// Création:
// Auteurs :
//
// Ce code n'est pas protégé par un copyright.
//
// Historique :
//  Créé pour le cours IFT1025 H22
//

import java.security.KeyStore;

/**
 class Animal
 implements the Prey/Predator relationship

 **/

// TO BE COMPLETED //

public class Animal implements ProiePredateur{

    private boolean estProie;
    private boolean estPredateur;
    private boolean estVivant;
    private double masse;
    private int age;
    protected double facteurCroissance;

    public Animal(double facteurCroissance) {this.facteurCroissance = facteurCroissance;}

    public void naitre() {estVivant = true;}; // animal becomes alive

    public void vieillir() {
        age += 1;
        masse = masse * facteurCroissance;
        if (age > getAgeMax()){
            mourir();
        }

    }; // animal is getting one year older

    public void manger(){}; // animal eats

    public Animal accoucher() {return new Animal(facteurCroissance);}; // animal delivers

    public void mourir(){estVivant = false;}; // animal dies

    public boolean estVivant() {return estVivant;}; // animal is alive

    public boolean estMature() {return age >= getAgeMature();}; // animal is mature

    public void setProie( boolean proie ) {estProie = proie;}; // set animal mode to prey

    public boolean estProie() {return estProie;};// animal is a prey

    public void setPredateur(boolean predateur) {this.estPredateur = predateur;};// set animal mode to predator

    public boolean estPredateur() {return estPredateur;};// animal is a predator

    public double getMasse() {return masse;};// get animal's mass

    public void setMasse( double masse ){this.masse = masse;}; // set animal's mass

    public void setAge( int age ) {this.age = age;}; // set animal's age

    public int getAge() {return age;}; // get animal's age

    public int getAgeMax() {return -1;}; // get animal's maximum age

    public int getAgeMature() {return -1;} // get animal's mature age

}
