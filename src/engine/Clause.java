package engine;
import java.util.ArrayList;
import java.util.Objects;

public class Clause {

	String clause;
    ArrayList<Literal> literals = new ArrayList<Literal>();

	public Clause(Literal l){
	    this.literals.add(l);
	    updateClause();
    }

	public Clause(ArrayList<Literal> l){
	    for(int i = 0; i<l.size(); ++i){
	        this.literals.add(l.get(i));
        }
        updateClause();
    }

    public void addLiteral(Literal l){
        this.literals.add(l);
        updateClause();
    }

    public void addLiterals(ArrayList<Literal> l){
        for(int i = 0; i < l.size(); ++i){
            this.literals.add(l.get(i));
        }
        updateClause();
    }

    public String toString(){
		return this.clause;
	}

	public void updateClause(){

        if( !literals.isEmpty() ) {

            StringBuilder sb = new StringBuilder("(");

            for (int i = 0; i < literals.size(); ++i) {
                if (literals.get(i).isPositive()) {
                    sb.append(literals.get(i).getSymbol() + "|");
                } else {
                    sb.append("-" + literals.get(i).getSymbol() + "|");
                }
            }

            sb.deleteCharAt(sb.lastIndexOf("|"));
            sb.append(")");
            clause = sb.toString();
        }
        else{
            clause = "";
        }
    }

    /* TODO: fix function names once old ones are pruned
    * */
	public Clause resolve2(Clause B){
	    Clause resolvent = new Clause(this.literals);
        resolvent.addLiterals(B.literals); // Clause is now like (A|-B|C|A|-C)
        resolvent.negation2();             // Clause will be updated to (A|-B|A) TODO: remove '2'
        resolvent.factor2();               // Clause will be updated to (A|-B). Done. TODO: remove '2'
        // TODO: add internal sort
	    return resolvent;
    }

    public void sortLiteral(){

    }



    // 0 1 2 3 4 5
    // 0 1 2 3 4 5

    // 0,1 0,2 0,3 0,4 0,5
    // 4,5


	/*	Input: ArrayList<Clause> containing two clauses i.e (A|B) and (A|-B)
	 * Factor will first call negation to see if there's elements that cancel each other. 
	 * Negation will in the case above find B and -B and cancel them and return (A) and (A)
	 * factor will find this redundant and will change the clause to (A)
	 * Output: Factored and negated clauses
	 * */
	public void factor2(){

	    for(int i = 0; i < literals.size(); ++i){
            for(int j = literals.size()-1; j > i; --j){
               // System.out.println("Factor. i and j: " + i + " " + j);
                if(factorCheckMatch(i,j)){
                    //System.out.println("Literals :" + i + " and " + j + " are duplicates. Flagged for removal");
                    deleteLiteral(j); // favor keeping the item to the left, i.e the first occurrence
                    --j;
                }
            }
        }


	}

	public boolean factorCheckMatch(int i, int j){
	    return this.literals.get(i).getSymbol() == this.literals.get(j).getSymbol() &&
                this.literals.get(i).isPositive() == this.literals.get(j).isPositive();
    }
	/* use on current clause to handle negation
	* 2 loops, one from the start and one from the end, since the clause is a mix of two clauses, more likely
	* to find duplicates near the end
	*
	* */

	public Boolean negationCheckMatch2(int i, int j){

        return  literals.get(i).getSymbol() == literals.get(j).getSymbol() &&
                literals.get(i).isPositive() != literals.get(j).isPositive();
    }
//  A -B C A -C
	public void negation2(){ // is to replace current function 'negation'

        for(int i = 0; i < literals.size();++i){
		    for(int j = literals.size()-1; j > i; --j){
		        //System.out.println("i and j: " + i + " , " + j);
                if(negationCheckMatch2(i,j)){
                    //System.out.println("Literals : " + literals.get(i).getSymbol() + " with index: " + i + " and " + literals.get(j).getSymbol() + " with index: " + j + " are negating each other. Flagged for removal");
                    this.deleteLiteralPair(i,j);
                    j=literals.size();

                }
            }
        }
	}

	/*  used in factor and negation respectively
	*
	* */


	@Override
    public boolean equals(Object o) {
        if (o != null) {
            Clause b = (Clause) o;
            return this.toString().equals(b.toString());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(clause);
    }

	public void deleteLiteral(int j){
	    literals.remove(j);
	    updateClause();
    }

	public void deleteLiteralPair(int i, int j){

	    //System.out.println("deletePair: size " + this.literals.size());
	    this.literals.remove(j);
	    this.literals.remove(i);
        //System.out.println("deletePair: size after delete " + this.literals.size());
        updateClause();
    }
    /** TODO: rewrite slightly to work with literals
     *
     *
     */

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
}
