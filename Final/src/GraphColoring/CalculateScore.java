package GraphColoring;

public class CalculateScore {//can be easily changed later, if we want our grading system to be different

//hint levels will go from less revealing to more revealing
public static boolean hintOneUsed = false;//upper bound
public static boolean hintTwoUsed = false;//give possible colours
public static boolean hintThreeUsed = false;//most connected
public static boolean hintFourUsed = false;//show if new colour should be chosen
public static boolean hintFiveUsed = false;//biggest clique
public static boolean hintSixUsed = false;//show edges
public static boolean hintSevenUsed = false; //coloured edges
public double score = 100; //this will be in percent

	public String giveScore() {
		calculate();
		resetHints(); //so, for the next game, the hints will be not-used again
		return Integer.toString((int) score);
	}
	public void calculate() {
		if(hintOneUsed) {
			score = (score/10 * 9.5);
		}
		if (hintTwoUsed) {
			score = (score/10 * 9.0);
		}
		if (hintThreeUsed) {
			score = (score/10 * 8.0);
		}
		if (hintFourUsed) {
			score = (score/10 * 7.5);
		}
		if (hintFiveUsed) {
			score = (score/10 * 6.5);
		}
		if (hintSixUsed) {
			score = (score/10 * 5.5);
		}
		if (hintSevenUsed) {
			score = (score/10 * 5.0);
		}
	}
	
	public void resetHints() {//use this whenever there's a new graph, or whenever a new game is started!
		hintOneUsed = false;
		hintTwoUsed = false;
		hintThreeUsed = false;
		hintFourUsed = false;
		hintFiveUsed = false;
		hintSixUsed = false;
		hintSevenUsed = false;
	}
	
}
