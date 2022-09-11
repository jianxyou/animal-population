// Fichier :     Herbe.java
// Création:     2022.03.03
// Auteur :      francois.major@umontreal.ca
//
// Ce code n'est pas protégé par un copyright.
// 
// Historique :
//  Créé pour le cours IFT1025 H22
//

/**
 class Herbe

 @author <A HREF="mailto:francois.major@umontreal.ca">François Major</A>
 @version $Revision: 1.0 $ $Date: 2022/03/03 $
 @see
 **/

public class Herbe {

    // the maximum possible herb mass (upper limit)
    private double masseCritique;
    // current herb mass
    private double masse;
    // herb mass consumable in one year
    private double masseAnnuelle;
    // herb growth rate
    private double tauxCroissance;

    public Herbe( double masse, double masseCritique, double tauxCroissance ) {
        this.masse = masse;
        this.masseAnnuelle = 0.6 * this.masse; // consumable herb mass is 60% of the current mass
        this.masseCritique = masseCritique;
        this.tauxCroissance = tauxCroissance;
    }
    // getters
    public double getMasse() { return this.masse; }
    public double getMasseAnnuelle() { return this.masseAnnuelle; }
    // setters
    public void setMasse( double masse ) { this.masse = masse; }
    public void setMasseAnnuelle( double masseAnnuelle ){ this.masseAnnuelle = masseAnnuelle; }
    // behavior
    public void vieillir(){
        this.masseAnnuelle = Math.max( 0, this.masseAnnuelle );
        this.masse = this.masse * 0.4 + this.masseAnnuelle;
        this.masse = Math.min( this.masse * this.tauxCroissance, this.masseCritique );
        this.masseAnnuelle = this.masse * 0.6; // consumable herb mass is 60% of the current mass
    }
    public String toString() { return "[" + masse + " d'herbe]"; }
}