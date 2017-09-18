package engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Engine {

   public static void main(String args[]) throws Exception{

      ArrayList<Clause> clauses = Clause.generateClauses();
      ArrayList<Clause> moreClauses = Clause.generateMoreClauses();

      System.out.println("Clauses before resolve: " + clauses.get(0).toString() + " and " + clauses.get(1).toString());

      Clause theClause = clauses.get(0).resolve2(clauses.get(1));

      System.out.println("Resolvent: " + theClause.toString());

      ArrayList<Clause> resolvents = new ArrayList<Clause>();
      resolvents = runResolveProgram(moreClauses);
      printClauses(resolvents);
   }

   public static ArrayList<Clause> runResolveProgram(ArrayList<Clause> clauses){

       int attempts = 5;
       int current = 0;

       int[] indices;
       ArrayList<Clause> resolvents = new ArrayList<Clause>();
       // try making new resolvents until no more can be created. Stop after too many unsuccessful attempts

       while(current < attempts){
           indices = pickIndices(clauses);
           Clause resolvent = clauses.get(indices[0]).resolve2(clauses.get(indices[1]));
           if(resolvents.contains(resolvent)){
               current++;
           }else{
               resolvents.add(resolvent);
               System.out.println("Added resolvent to knowledgebase: " + resolvent.toString());
           }
       }
       return resolvents;
   }

    static int[] pickIndices(ArrayList<Clause> clauses){

       Random rng = new Random();

       int i = rng.nextInt(clauses.size());
       int j = rng.nextInt(clauses.size());

       while(j == i){
           j = rng.nextInt(clauses.size());
       }
       return new int[]{i,j};
    }

    public static void printClauses(ArrayList<Clause> clauses){
        System.out.println("Clauses: ");
        for(int i = 0; i<clauses.size();++i){
            System.out.println(clauses.get(i).toString());
        }
    }
}
