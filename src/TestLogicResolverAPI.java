import engine.*;

import java.util.ArrayList;

public class TestLogicResolverAPI {
    public static void main(String args[]) throws Exception
    {
        /*boolean temp;
        ArrayList<Clause> moreClauses = Clause.generateMoreClauses();

        Engine logicEngine;
        logicEngine = new Engine(moreClauses);

        System.out.println("Original: ");
        Engine.printClauses(moreClauses);
        System.out.println("\n \n");

        logicEngine.doResolution(moreClauses);
        temp = logicEngine.getTheTruth();

        System.out.print("Proof by contradiction says that the statement is: " + temp + ". \n\n\n");
        */

        ArrayList<Clause> robbers = Clause.robberProblem();
        Engine engine = new Engine(robbers);
        System.out.println("Robber problem. KB: ");
        Engine.printClauses(robbers);
        System.out.println("\n\n");
        System.out.println("Is A innocent? Let's apply resolution and find out!");
        engine.doResolution(robbers);
        boolean answer = engine.getTheTruth();

        System.out.println("The verdict is: " + answer);
    }
}
