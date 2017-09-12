package engine;

import java.util.ArrayList;

public class Engine {

   private ArrayList<Clause> knowledgeBase_ = new ArrayList<Clause>();

   public static void main(String args[]) throws Exception{

      ArrayList<Clause> clauses = Clause.genClauses();

      clauses.get(0).negation(clauses.get(1));
      System.out.println("Clause after negation: " + clauses.get(0).toString());
      System.out.println("Clause after negation: " + clauses.get(1).toString());


   }

}
