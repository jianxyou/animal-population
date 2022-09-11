// Fichier :     Lion.java
// Création:     2022.03.11
// Auteurs :     jianxin.you@umontreal.ca  yehao.yan@umontreal.ca
import java.util.ArrayList;

public class Lion extends Animal{

    private ArrayList<Animal> individus;
    public static final int AGEMATURE = 5;//the mature age of a lion is 5
    public static final int AGEMAX = 50;//the maximum life span of a lion is 50 years
    private int nombreProiesChassable;

    public Lion (double facteurCroissance){
        super(facteurCroissance);
        setAge(0);//An animal is born with age 0 and mass 10
        setMasse(10);
        naitre();
        setPredateur(true);// An antelope is a predator
        setProie(false);
    }

    public void setIndividus(ArrayList<Animal> individus) {this.individus = individus;}

    public void setNombreProiesChassable(int nombre) {nombreProiesChassable = nombre;}

    public int getNombreProiesChassable() {return nombreProiesChassable;}

    @Override
    public int getAgeMature(){
        return Lion.AGEMATURE;
    }

    @Override
    public int getAgeMax(){
        return Lion.AGEMAX;
    }

    //lion dlivers
    @Override
    public Lion accoucher() {return new Lion(facteurCroissance);}


    //lion eats
    @Override
    public void manger(){
        //a lion that hunts must eat twice its mass of antelopes to survive
        double masseDoitManger = 2 * getMasse();
        for (Animal proie: individus) {
            if (nombreProiesChassable > 0) {
                if (masseDoitManger <= 0) break;

                else {
                    if (proie.estVivant() && proie.estProie()) {
                        double masseProie = proie.getMasse();
                        if (masseProie >= masseDoitManger) {
                            proie.mourir();
                            nombreProiesChassable -= 1;
                            break;
                        }
                        else {
                            proie.mourir();
                            nombreProiesChassable -= 1;
                            masseDoitManger -= masseProie;
                        }
                    }
                }
            }//there are no more antelopes to hunt
            else {
                if (masseDoitManger > 0){
                    mourir();
                }
            }

        }
    }



}
