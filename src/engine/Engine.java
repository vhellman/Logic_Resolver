package engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Engine {

   public static void main(String args[]) throws Exception
   {
       boolean temp;
       ArrayList<Clause> moreClauses = Clause.generateMoreClauses();

       System.out.println("Original: ");
       printClauses(moreClauses);
       System.out.println("\n \n");

       temp = doResolution(moreClauses);

       System.out.print("Proof by contradiction says that the statement is: " + temp + ". \n\n\n");
   }

    public static boolean doResolution( ArrayList<Clause> clauses ){ //add query , clauses should be KB + notQuery
        return runResolveProgram(clauses);
    }

    /******************************************************************************************************
     *
     *                              PRIVATE AUXILLARY FUNCTIONS
     *
     *****************************************************************************************************/

    /**
     * Uses proof by contradiction to test wether a certain statement is true or not.
     *
     * @param clauses - the union of the knowledge base and the negation of the statement that is tested. KB U -a
     * @return a boolean, true if there is a contradiction and false if there are no new clauses to derive.
     */

   private static boolean runResolveProgram(ArrayList<Clause> clauses){

        Clause clasI, clasJ;
        ArrayList<Clause> newClauses = new ArrayList<>(); //newClauses -> {}

        for( int i = 0 ; i < (clauses.size() - 1)  ; ++i ) {

            clasI = clauses.get(i); //clause Ci
            for( int j = i+1 ; j < clauses.size()  ; ++j ){

                clasJ = clauses.get(j); //clause Cj

                Clause resolvent = clasI.resolve2(clasJ); //resolve for each pair of clauses

                if ( resolvent.toString().equals("") ) { return true; }//if empty clause is found: return true

                if ( !newClauses.contains(resolvent) ) { newClauses.add(resolvent); } //new = new U resolvents
            }
        }

        /*
        System.out.println("new Clauses:");
        printClauses(newClauses);
        System.out.println(" \n ");
        */

        if( clauses.containsAll( newClauses ) ){ return false; } //if newClauses is a sub-set of clauses: return false

        else { //else clauses = clauses U newClauses
        clauses = addNewClauses(clauses, newClauses);
            /*
            System.out.println("KB:");
            printClauses( clauses );
            System.out.println("\n \n \n ");
            */
        return runResolveProgram( clauses );
        }
    }

    /**
     * Loops through the newClauses and adds all clauses that is not already known.
     *
     * @param KB the knowledge base
     * @param newClauses - an set of new clauses to insert into the KB
     * @return a union of KB and newClauses
     */
    private static ArrayList<Clause> addNewClauses( ArrayList<Clause> KB, ArrayList<Clause> newClauses ){

        Clause tempClause;

        for( int i = 0 ; i < newClauses.size() ; i++) {
            tempClause = newClauses.get(i);

            if( !KB.contains(tempClause) ){
                KB.add(tempClause);
            }
        }
        return KB;
    }

    public static void printClauses(ArrayList<Clause> clauses){
        System.out.println("Clauses: ");
        for(int i = 0; i<clauses.size();++i){
            System.out.println(clauses.get(i).toString());
        }
    }
}
/******************************************************************************************************
 *
 *                              NOT USED ANYMORE?
 *
 *****************************************************************************************************/



/*    static int[] pickIndices(ArrayList<Clause> clauses){

       Random rng = new Random();

       int i = rng.nextInt(clauses.size());
       int j = rng.nextInt(clauses.size());

       while(j == i){
           j = rng.nextInt(clauses.size());
       }
       return new int[]{i,j};
    }*/
