package engine;

import java.util.ArrayList;

public class Engine {

   private ArrayList<Clause> knowledgeBase_ = new ArrayList<Clause>();

   public static void main(String args[]) throws Exception{

      /*
      ArrayList<Clause> clauses = Clause.genClauses();

      clauses.get(0).negation(clauses.get(1));

      */

      ArrayList<Clause> clauses = Clause.generateClauses();
      //System.out.println("Clauses size: " + clauses.size());

      System.out.println("Clauses before resolve: " + clauses.get(0).toString() + " and " + clauses.get(1).toString());

      Clause theClause = clauses.get(0).resolve2(clauses.get(1));

      System.out.println("Resolvent: " + theClause.toString());
      /*
      System.out.println("Clause after negation: " + clauses.get(0).toString());
      System.out.println("Clause after negation: " + clauses.get(1).toString());
      */

   }

}
