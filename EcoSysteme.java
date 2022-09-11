// Fichier :     EcoSysteme.java
// Création:     2022.03.03
// Auteur :      francois.major@umontreal.ca
//
// Ce code n'est pas protégé par un copyright.
// 
// Historique :
//  Créé pour le cours IFT1025 H22
//

import java.util.ArrayList;

/**
 interface EcoSysteme

 @author <A HREF="mailto:francois.major@umontreal.ca">François Major</A>
 @version $Revision: 1.0 $ $Date: 2022/03/03 $
 @see
 **/

public interface EcoSysteme {
    public int getNombreProies(); // number of current preys
    public int getNombrePredateurs(); // number of current predators
    public int getNombreProiesMatures(); // number of current mature preys
    public int getNombrePredateursMatures(); // number of current mature predators
    public int getNombreProiesChassables(); // number of current chaseable preys
    public ArrayList<Animal> getIndividus(); // animals in the population
    public double masseProies(); // current total mass of preys
    public double massePredateurs(); // current toral mass of predators
    public void vieillir(); // getting older
    public void chasser(); // chasing
    public void reproduire(); // reproducing
    public void melanger(); // mix the list of animals
}