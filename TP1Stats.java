// Fichier :     TP1Stats.java
// Création:     2022.03.03
// Auteur :      francois.major@umontreal.ca
//
// Ce code n'est pas protégé par un copyright.
//
// Historique :
//  Créé pour le cours IFT1025 H22
//

/**
 interface TP1Stats

 @author <A HREF="mailto:francois.major@umontreal.ca">François Major</A>
 @version $Revision: 1.0 $ $Date: 2022/03/03 $
 @see
 **/

public interface TP1Stats {
    // stats[0] nombreLions
    // stats[1] nombreVieuxLions <- dying next year
    // stats[2] nombreLionsMatures
    // stats[3] nombreJeunesLions <- age > 0 && !mature
    // stats[4] nombreBebesLions <- age == 0
    // stats[5] masseTotaleLions
    // stats[6] nombreAntilopes
    // stats[7] nombreVieillesAntilopes <- dying next year
    // stats[8] nombreAntilopesMatures
    // stats[9] nombreJeunesAntilopes <- age > 0 && !mature
    // stats[10] nombreBebesAntilopes <- age == 0
    // stats[11] masseTotaleAntilopes
    // the stats array stores the above values
    //   at the end of a year, in that order
    public double[] stats( int annee, boolean show );
}
