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

	protected void updateClause(){

        if( !literals.isEmpty() ) {

            StringBuilder tempClauseString = new StringBuilder("(");

            for (int i = 0; i < literals.size(); ++i) {
                if (literals.get(i).isPositive()) {
                    tempClauseString.append(literals.get(i).getSymbol() + "|");
                } else {
                    tempClauseString.append("-" + literals.get(i).getSymbol() + "|");
                }
            }

            tempClauseString.deleteCharAt(tempClauseString.lastIndexOf("|"));
            tempClauseString.append(")");
            clause = tempClauseString.toString();
        }
        else{
            clause = "";
        }
    }

    /* TODO: fix function names once old ones are pruned
    * */
	protected Clause resolve2(Clause B){
	    Clause resolvent = new Clause(this.literals);
        resolvent.addLiterals(B.literals); // Clause is now like (A|-B|C|A|-C)
        resolvent.negation2();             // Clause will be updated to (A|-B|A) TODO: remove '2'
        resolvent.factor();               // Clause will be updated to (A|-B). Done. TODO: remove '2'
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
	private void factor(){

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

	private boolean factorCheckMatch(int i, int j){
	    return this.literals.get(i).getSymbol() == this.literals.get(j).getSymbol() &&
                this.literals.get(i).isPositive() == this.literals.get(j).isPositive();
    }
	/* use on current clause to handle negation
	* 2 loops, one from the start and one from the end, since the clause is a mix of two clauses, more likely
	* to find duplicates near the end
	*
	* */

	private Boolean negationCheckMatch2(int i, int j){

        return  literals.get(i).getSymbol() == literals.get(j).getSymbol() &&
                literals.get(i).isPositive() != literals.get(j).isPositive();
    }
//  A -B C A -C
	private void negation2(){ // is to replace current function 'negation'

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

	private void deleteLiteral(int j){
	    literals.remove(j);
	    updateClause();
    }

	private void deleteLiteralPair(int i, int j){

	    //System.out.println("deletePair: size " + this.literals.size());
	    this.literals.remove(j);
	    this.literals.remove(i);
        //System.out.println("deletePair: size after delete " + this.literals.size());
        updateClause();
    }
}
