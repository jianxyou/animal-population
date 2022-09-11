// Fichier :     Population.java
// Création:     2022.03.11
// Auteurs :     jianxin.you@umontreal.ca  yehao.yan@umontreal.ca
import javax.swing.*;
import javax.swing.undo.CannotUndoException;
import java.awt.image.AreaAveragingScaleFilter;
import java.security.KeyStore;
import java.util.Iterator;
import java.lang.Iterable;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

// Defines a population of herb and animals (preys and predators), iterable
public class Population implements EcoSysteme, Iterable<Animal> {

    private Herbe herbe;
    private ArrayList<Animal> individus;


    public Population( Herbe herbe,ArrayList<Animal> proies, ArrayList<Animal> predateurs){
        this.herbe = herbe;
        individus = new ArrayList<>();
        individus.addAll(proies);
        individus.addAll(predateurs);
    }


    // get number of current preys
    public int getNombreProies() {
        int nombre = 0;
        for (Animal animal : individus) {
            if (animal.estProie() && animal.estVivant()) {
                nombre += 1;
            }
        }
        return nombre;
    }

    //get number of current predator
    public int getNombrePredateurs() {
        int nombre = 0;
        for (Animal animal : individus) {
            if (animal.estPredateur() && animal.estVivant()) {
                nombre += 1;
            }
        }
        return nombre;
    }

    // get number of current mature preys
    public int getNombreProiesMatures() {
        int nombre = 0;
        for (Animal animal: individus){
            if (animal.estProie() && animal.estMature() && animal.estVivant()){
                nombre += 1;
            }
        }
        return nombre;
    };

    // get number of current mature predators
    public int getNombrePredateursMatures() {
        int nombre = 0;
        for (Animal animal: individus){
            if (animal.estPredateur() && animal.estMature() && animal.estVivant()){
                nombre += 1;
            }
        }
        return nombre;
    }

    // get number of current chaseable preys
    public int getNombreProiesChassables(){
        int nombreProies= getNombreProies();
    //At each hunting season, only 20% of the antelopes alive
    //at the start of the hunt are huntable
        return (int)(nombreProies * 0.2) ;
    }

    // animals in the population
    public ArrayList<Animal> getIndividus(){return this.individus;}



    // current total mass of preys
    public double masseProies(){
        double masseTotal = 0;

        for (Animal animal : individus){
            if (animal.estProie() && animal.estVivant()){
                masseTotal += animal.getMasse();
            }
        }
        return masseTotal;
    }

    // current toral mass of predators
    public double massePredateurs(){
        double masseTotal = 0;
        for (Animal animal : individus){
            if (animal.estPredateur() && animal.estVivant()){
                masseTotal += animal.getMasse();
            }
        }

        return masseTotal;
    }

    //remove dead animals from the population
    public void removeAnimalMort() {

        ArrayList<Animal> animalMort = new ArrayList<>();
        for (Animal animal : individus) {
            if (!animal.estVivant()) {
                animalMort.add(animal);
            }
        }

        for (Animal animal : animalMort) {
            individus.remove(animal);
        }
    }

    // make the grass and the animals get older
    public void vieillir(){
        herbe.vieillir();
        for (Animal animal : individus){
            if (animal.estVivant()) {
                animal.vieillir();
            }
        }
       removeAnimalMort();
    }

    // mix the list of the animals
    public void melanger() {Collections.shuffle(this.individus, new Random(4));}


   // set foods for animals
    public void setNourriture(){
        for (Animal animal: individus){
            if (animal.estPredateur()){
                Lion lion = (Lion) animal;
                lion.setIndividus(individus);  // lion need the information of the whole popluation.
            }

            else if (animal.estProie()){
                Antilope antilope = (Antilope) animal;
                antilope.setHerbe(herbe);
            }
        }
    }

    // make animals hunt for food
    public void chasser() {
        melanger();
        setNourriture();
        int nombreProiesChassables = getNombreProiesChassables();
        for (Animal animal : individus) {
            //antelopes eat grasses
            if (animal.estVivant() && animal.estProie()) {
                animal.manger();
            //lions eat antelopes
            } else if (animal.estVivant() && animal.estPredateur()) {

                if (nombreProiesChassables > 0) {
                    Lion lion = (Lion) animal;
                    // lions also have to know there are how many antilopes are chasable
                    lion.setNombreProiesChassable(nombreProiesChassables);
                    lion.manger();
                    // we have to renew the variable nombreProiesChassables for the next lion.
                    nombreProiesChassables = lion.getNombreProiesChassable();
                } else {
                    animal.mourir();
                }
            }
        }
    }





    //make animals deliver
    public void reproduire(){
        //the number of female animals corresponds to half of the mature population
        int nouveauNombreProies = getNombreProiesMatures() / 2;
        int nouveauNombrePredateurs = getNombrePredateursMatures() / 2;
        ArrayList<Animal> bebeAnimal = new ArrayList<>();
        //antelopes deliver
        for(Animal animal : individus){
            if (animal.estProie() && animal.estVivant() && animal.estMature()){
                if (nouveauNombreProies > 0){
                    bebeAnimal.add(animal.accoucher());
                    nouveauNombreProies -= 1;
            }
        }
           //lions deliver
            else if (animal.estPredateur() && animal.estVivant() && animal.estMature()){
                if (nouveauNombrePredateurs > 0){
                bebeAnimal.add(animal.accoucher());
                nouveauNombrePredateurs -= 1;
            }
        }
    }
        individus.addAll(bebeAnimal);
        removeAnimalMort();
    }



    public Iterator<Animal> iterator() {return individus.iterator();}

}
