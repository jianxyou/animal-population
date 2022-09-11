// Fichier :     Savane.java
// Création:     2022.03.03
// Auteur :      francois.major@umontreal.ca
//
// Ce code n'est pas protégé par un copyright.
// 
// Historique :
//  Créé pour le cours IFT1025 H22
//

/**
 class Savane
 implements TP1Stats

 @author <A HREF="mailto:francois.major@umontreal.ca">François Major</A>
 @version $Revision: 1.0 $ $Date: 2022/03/03 $
 @see
 **/

/**
 TP1 - IFT1025
 noms et prénoms des coéquipier(s)

 This class simulates the cycles of life of a simple savane
 composed of: herb and animals (lions and Antelopes),
 which implements a prey/predator relationship.

 This class requires the following Object organization:

 ProiePredateur            TP1Stats      EcoSysteme
 .                       .             .
 .      implements     .             .
 .                   .             .
 Animal   Herbe   Savane            .
 /   \              | made of a Population composed of:
 /     \             ------------------- Herbe,
 Antilope  Lion                              herd of antelopes (preys),
 herd of lions (predators)
 */

import java.util.ArrayList;

public class Savane implements TP1Stats {

    private boolean show; // for output/debugging
    private int nombreAnnees; // number of year cycles
    private Herbe herbe; // the Herb to feed the preys
    private Population savane; // herb and animal population of the savane

    // constructor
    //   defines: numbers of lions and antelopes in the initial population
    //            number of years
    //            initial herb mass and herb maximum mass
    //            growth factors of the herb and animals
    //            output/debugging option to show more or less details
    public Savane( int nombreLions,
                   double facteurCroissanceLions,
                   int nombreAntilopes,
                   double facteurCroissanceAntilopes,
                   int nombreAnnees,
                   int masseHerbeInitiale,
                   int masseHerbeMaximum,
                   double facteurCroissanceHerbe,
                   boolean show ) {

        // assign the show and number of years attributes
        this.show = show;
        this.nombreAnnees = nombreAnnees;

        // create the herb (food for the antelopes)
        herbe = new Herbe( masseHerbeInitiale, masseHerbeMaximum, facteurCroissanceHerbe );

        // create the initial preys
        ArrayList<Animal> proies = new ArrayList<>();
        for( int i = 0; i < nombreAntilopes; i++ ) proies.add( new Antilope( facteurCroissanceAntilopes ) );

        // create the initial predators
        ArrayList<Animal> predateurs = new ArrayList<>();
        for( int i = 0; i < nombreLions; i++ ) predateurs.add( new Lion( facteurCroissanceLions ) );

        // create the savane's population
        savane = new Population( herbe, proies, predateurs );
    }

    // Savane getter
    public Population getSavane() { return savane; }

    // the cycle of life simulation for the number of years
    public double[] simule() {
        // the order of event is important

        savane.vieillir(); savane.vieillir(); // the savane grows protected for the first two years

        // cycles start
        int annee;
        for( annee = 0; annee < nombreAnnees; annee++ ) {
            savane.vieillir();   // ----- LA SAVANE VIEILLIT -----
            savane.chasser();    // ----- LA SAVANE CHASSE -----
            savane.reproduire(); // ----- LA SAVANE PROCRÉE -----
            // debugging-output
            if( show && ( annee + 1 ) < this.nombreAnnees ) stats( annee + 1, show );
        }
        return stats( annee, true ); // return the stats of the last year and print
    }

    public static void print( String text ) { System.out.println( text ); }

    public double[] stats( int annee, boolean show ) {

        // to satisfy the TP1Stats interface
        int nombreLions = 0;
        int nombreVieuxLions = 0;
        int nombreLionsMatures = 0;
        int nombreJeunesLions = 0;
        int nombreBebesLions = 0;
        double masseTotaleLions = 0;
        int nombreAntilopes = 0;
        int nombreVieillesAntilopes = 0;
        int nombreAntilopesMatures = 0;
        int nombreJeunesAntilopes = 0;
        int nombreBebesAntilopes = 0;
        double masseTotaleAntilopes = 0;

        for( Animal a : this.savane ) {
            if( a.estPredateur() ) {
                nombreLions++;
                masseTotaleLions += a.getMasse();
                if( a.getAge() == Lion.AGEMAX ) nombreVieuxLions++;
                if( a.estMature() ) nombreLionsMatures++;
                if( !a.estMature() && a.getAge() != 0 ) nombreJeunesLions++;
                if( a.getAge() == 0 ) nombreBebesLions++;
            }
            else if( a.estProie() ) {
                nombreAntilopes++;
                masseTotaleAntilopes += a.getMasse();
                if( a.getAge() == Antilope.AGEMAX ) nombreVieillesAntilopes++;
                if( a.estMature() ) nombreAntilopesMatures++;
                if( !a.estMature() && a.getAge() != 0 ) nombreJeunesAntilopes++;
                if( a.getAge() == 0 ) nombreBebesAntilopes++;
            }
        }

        String indice = "ème";
        if( annee == 1 ) indice = "ière";

        if( show ) {
            print( "///////////////////////////// À la fin de la " + annee + indice + " année, nous observons :" );
            print( nombreLions + " lions total ;" );
            print( nombreVieuxLions + " lions qui vont mourrir de vieillesse ;" );
            print( nombreLionsMatures + " lions matures ;" );
            print( nombreJeunesLions + " lions juvenils ;" );
            print( nombreBebesLions + " bébés lions ;" );
            print( masseTotaleLions + " masse en lions ;" );
            print( nombreAntilopes + " antilopes total ;" );
            print( nombreVieillesAntilopes + " antilopes qui vont mourrir de vieillesse ;" );
            print( nombreAntilopesMatures + " antilopes matures ;" );
            print( nombreJeunesAntilopes + " antilopes juveniles ;" );
            print( nombreBebesAntilopes + " bébés antilopes ; et" );
            print( masseTotaleAntilopes + " masse en antilopes." );
        }

        double[] test = {
                annee,
                nombreLions,
                nombreVieuxLions,
                nombreLionsMatures,
                nombreJeunesLions,
                nombreBebesLions,
                masseTotaleLions,
                nombreAntilopes,
                nombreVieillesAntilopes,
                nombreAntilopesMatures,
                nombreJeunesAntilopes,
                nombreBebesAntilopes,
                masseTotaleAntilopes };
        return test;
    }
}
