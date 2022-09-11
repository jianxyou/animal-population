// Fichier :     Antilope.java
// Création:     2022.03.11
// Auteurs :     jianxin.you@umontreal.ca  yehao.yan@umontreal.ca


public class Antilope extends Animal{
    private Herbe herbe;
    public static final int AGEMATURE = 2; //the mature age of an antelopes is 2
    public static final int AGEMAX = 15;// the maximum life span of an antelopes is 15 years.
    public Antilope( double facteurCroissance){
        super(facteurCroissance);
        setAge(0);//An animal is born with age 0 and mass 10
        setMasse(10);
        naitre();
        setPredateur(false);// An antelope is a prey
        setProie(true);
    }
    // antelopes must have the same herbe to eat in each year
    public void setHerbe(Herbe herbe){
        this.herbe = herbe;
    }

    //antelope dlivers
    @Override
    public Antilope accoucher() {return new Antilope(facteurCroissance);}

    @Override
    public int getAgeMature(){
        return Antilope.AGEMATURE;
    }

    @Override
    public int getAgeMax(){
        return Antilope.AGEMAX;
    }


    //antelope eats
    @Override
    public void manger() {
        double masseHerbeComsumable = herbe.getMasseAnnuelle();
        double massAntilope = getMasse();
        //If there isn't enough grass to feed an antelope, it dies
        if (masseHerbeComsumable <= 0) {
            mourir();
        }
        //To survive, an antelope must eat twice its mass of grass each year.
        else {
            if (massAntilope * 2 > masseHerbeComsumable) {
                mourir();
            }
            else {
                herbe.setMasseAnnuelle(masseHerbeComsumable - (massAntilope * 2));

            }
        }
    }



}
