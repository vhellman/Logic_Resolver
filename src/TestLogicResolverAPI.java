import engine.*;

import java.util.ArrayList;

public class TestLogicResolverAPI {
    public static void main(String args[]) throws Exception
    {
        boolean temp;
        ArrayList<Clause> moreClauses = Clause.generateMoreClauses();

        Engine logicEngine;
        logicEngine = new Engine(moreClauses);

        System.out.println("Original: ");
        Engine.printClauses(moreClauses);
        System.out.println("\n \n");

        logicEngine.doResolution(moreClauses);
        temp = logicEngine.getTheTruth();

        System.out.print("Proof by contradiction says that the statement is: " + temp + ". \n\n\n");
    }
}
