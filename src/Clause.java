import java.util.ArrayList;

public class Clause {

	String clause;
	
	ArrayList<Character> variables = new ArrayList<Character>(); // element 0 could be A
	ArrayList<Boolean> sign = new ArrayList<Boolean>();  // corresponds to the same element in variables.

	public Clause(String string, ArrayList<Character> vars, ArrayList<Boolean> truths){
	this.clause = string;	// clause as a string
	
		for(int i = 0; i < vars.size(); ++i){
			this.variables.add(vars.get(i));
			this.sign.add(truths.get(i));
		}
	}

	public String toString(){
		return this.clause;
	}
	
	public String elementToString(int index){
		
		String str = "";
		if(!this.sign.get(index)){
			str+="-";
		}
		str+=this.variables.get(index);
		
		return str;
	}
	
	public static String convertToString(ArrayList<Character> chars, ArrayList<Boolean> signs){
		
		StringBuilder toString = new StringBuilder("(");
		
		for(int i = 0; i <chars.size();++i){
			if(signs.get(i)){
				toString.append(chars.get(i) + "|");
			}
			else{	
				toString.append("-" + chars.get(i));
				toString.append("|");
			}
		}
		toString.deleteCharAt(toString.lastIndexOf("|"));
		toString.append(")");
		
		return toString.toString();
	}
	/*
		Hmm, den är löst... men jag tror det ligger i fel klass? Vi borde returna en lista med Clauses va?
		Hmmm, igen... vid närmare eftertanke så har jag nog löst fel funktion. Får kolla över den sen.

	 */
	public void resolve(Clause B) {

		String newClause = "";
		String newClauseSign = "";

		for (int i = 0; i < variables.size(); i++) {
			for (int j = 0; i < B.variables.size(); j++) {
				if (variables.get(j).equals(B.variables.get(j))) { //if element in i and j are the same, check if true false

					if (sign.get(i).equals(B.sign.get(j))) { //if truths are equal, add one of them
						newClause += variables.get(i);
						newClauseSign += sign.get(i);
					}
					else { continue; }	//if not equal truths, they cancel; just move along.
				}
			}
		}
	}

	public static String genString(ArrayList<Character> str){
		StringBuilder stb = new StringBuilder("(");
		
		for(int i=0;i<str.size();++i){
			stb.append(str.get(i));
			stb.append("|");
		}
		if(stb.lastIndexOf("|") == stb.length()-1){
			stb.deleteCharAt(stb.lastIndexOf("|"));
		}
		stb.append(")");
		
		return stb.toString();
	}

	/*	Input: ArrayList<Clause> containing two clauses i.e (A|B) and (A|-B)
	 * Factor will first call negation to see if there's elements that cancel each other. 
	 * Negation will in the case above find B and -B and cancel them and return (A) and (A)
	 * factor will find this redundant and will change the clause to (A)
	 * Output: Factored and negated clauses
	 * */
	
	public ArrayList<Clause> factor(ArrayList<Clause> statements){ //				used in resolution, remove duplicates
		
		if(statements.size() == 2){ // ensure there are only two clauses
		ArrayList<Clause> newClauses = new ArrayList<Clause>(); 
		int A = 0, B = 0;
		for(int i = 0;i<statements.get(0).variables.size();++i){
			for(int j = 0;j<statements.get(1).variables.size();++j){
				
			}
		}
		
		
		
		return newClauses;
		}
		return statements;
	}
	
	public static Boolean negationCheckMatch(Character a, Character b){
		if(a == b){
			return true;
		}
		return false;
	}
	
	public void negation(Clause b){  // used in factor, remove negated elements i.e A and -A

		Boolean flag = false;
		for(int i = 0;i<this.variables.size();++i){
			for(int j = 0;j<b.variables.size();++j){
				
				if(this.variables.get(i) == b.variables.get(j)){ // same char					
					if(this.sign.get(i) != b.sign.get(j)){	// sign differs			
						System.out.println("Found match: " + this.elementToString(i) + " and " + b.elementToString(j));
						System.out.println("Differing sign found. Element is redundant");
						flag = true;
					}						
				}
				if(flag){
					System.out.println("Deleting flagged elements");
					this.variables.remove(i);
					this.sign.remove(i);
					this.clause = convertToString(this.variables, this.sign);
					
					b.variables.remove(j);
					b.sign.remove(j);
					b.clause = convertToString(b.variables, b.sign);
					--i;
					--j;
				}
				flag = false;
			}
		}	
	}
	
	
	public static ArrayList<Clause> genClauses(){ // generates two clauses (A|-B|C) set to TTF, (A|-C) set to TT
		ArrayList<Clause> clauses = new ArrayList<Clause>();
		
		ArrayList<Character> var = new ArrayList<Character>();
		var.add('A');
		var.add('B');
		var.add('C');
		
		ArrayList<Character> var2 = new ArrayList<Character>();
		var2.add('A');
		var2.add('C');
		
		ArrayList<Boolean> sign = new ArrayList<Boolean>();
		sign.add(true);
		sign.add(false);
		sign.add(true);
		
		ArrayList<Boolean> sign2 = new ArrayList<Boolean>();
		sign2.add(true);
		sign2.add(false);
			
		String toString = convertToString(var, sign);
		String toString2 = convertToString(var2,sign2);
		System.out.println(toString);
		System.out.println(toString2);
		clauses.add(new Clause(toString, var, sign));
		clauses.add(new Clause(toString2, var2, sign2));
		
		return clauses;
	};
	
	
}
