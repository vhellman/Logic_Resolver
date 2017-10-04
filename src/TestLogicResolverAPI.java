import engine.*;

import java.util.ArrayList;

public class TestLogicResolverAPI {
    public static void main(String args[]) throws Exception
    {
        boolean temp;
        ArrayList<Clause> moreClauses = generateMoreClauses();

        Engine logicEngine;
        logicEngine = new Engine(moreClauses);

        System.out.println("Original: ");
        Engine.printClauses(moreClauses);
        System.out.println("\n \n");

        logicEngine.doResolution(moreClauses);
        temp = logicEngine.getTheTruth();

        System.out.print("Proof by contradiction says that the statement is: " + temp + ". \n\n\n");
    }

    public static ArrayList<Clause> generateClauses(){
        ArrayList<Clause> clauses = new ArrayList<Clause>();

        Literal a = new Literal('A', true);
        Literal b = new Literal('B', false);
        Literal c = new Literal('C', true);

        Clause clause1 = new Clause(a);
        clause1.addLiteral(b);
        clause1.addLiteral(c);

        Literal a2 = new Literal('A', true);
        Literal c2 = new Literal('C', false);

        Clause clause2 = new Clause(a2);
        clause2.addLiteral(c2);

        clauses.add(clause1);
        clauses.add(clause2);
        return clauses;
    }

    public static ArrayList<Clause> generateMoreClauses(){
/*
        ArrayList<Clause> moreClauses = new ArrayList<Clause>();

        Literal a = new Literal('A', true);
        Literal b = new Literal('B', true);
        Literal c = new Literal('C', true);

        Clause clause1 = new Clause(a);
        clause1.addLiteral(b);
        clause1.addLiteral(c);

        Literal a2 = new Literal('A', false);
        Literal c2 = new Literal('C', true);

        Clause clause2 = new Clause(a2);
        clause2.addLiteral(c2);

        Literal a3 = new Literal('A', true);
        Literal c3 = new Literal('C', false);
        Literal d = new Literal('D', true);

        Clause clause3 = new Clause(a3);
        clause3.addLiteral(c3);
        clause3.addLiteral(d);

        Literal b4 = new Literal('B', false);
        Literal c4 = new Literal('C', false);

        Clause clause4 = new Clause(b4);
        clause4.addLiteral(c4);

        moreClauses.add(clause1);
        moreClauses.add(clause2);
        moreClauses.add(clause3);
        moreClauses.add(clause4);

        return moreClauses;*/

        ArrayList<Clause> moreClauses = new ArrayList<Clause>();


        Literal a = new Literal('A' , true);
        Literal a1 = new Literal('A', false);
        Literal b = new Literal('B', true);
        Literal b1 = new Literal('B', false);
        Literal c = new Literal ('C', true);
        Literal c1 = new Literal('C', false);
        //Literal e = new Literal(' ', false);

        Clause clas1 = new Clause(a1);
        clas1.addLiteral(b);
        moreClauses.add(clas1);

        Clause clas2 = new Clause(b1);
        clas2.addLiteral(c);
        clas2.addLiteral(a);
        moreClauses.add(clas2);

        Clause clas3 = new Clause(c1);
        clas3.addLiteral(b);
        moreClauses.add(clas3);

        Clause clas4 = new Clause(b1);
        moreClauses.add(clas4);

        return moreClauses;
    }

    public ArrayList<Clause> robberClauses (){
        ArrayList<Clause> robberInfo = new ArrayList<Clause>();

        Literal rA = new Literal('A', true);
        Literal notRA = new Literal('A', false);
        Literal rB = new Literal('B', true);
        Literal notRB = new Literal('B', false);
        Literal rC = new Literal('C', true);
        Literal notRC = new Literal('C', false);

        Literal dA = new Literal('X', true);
        Literal notDA = new Literal('X', false);
        Literal dB = new Literal('Y', true);
        Literal notDB = new Literal('Y', false);
        Literal dC = new Literal('Z', true);
        Literal notDC = new Literal('Z', false);

        Clause clause1 = new Clause(rA);
        return robberInfo;
    }
}
