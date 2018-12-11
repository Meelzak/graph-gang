package GraphColoring;

public class CalculateScore {//can be easily changed later, if we want our grading system to be different

//hint levels will go from less revealing to more revealing
public static boolean hintOneUsed = false; //upper bound
public static boolean hintTwoUsed = false; //lower bound
public static boolean hintThreeUsed = false; //chromatic number
public static boolean hintFourUsed = false; //possible colours, once
public static boolean hintFiveUsed = false; //most connected
public static boolean hintSixUsed = false; //new colour
public static boolean hintSevenUsed = false; //biggest clique
public static boolean hintEightUsed = false; //show coloured vertices
public static boolean hintNineUsed = false; //says if you can use colours for the whole game
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
			score = (score/10 * 9.5);
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
			score = (score/10 * 6.0);
		}
		if (hintSevenUsed) {
			score = (score/10 * 5.0);
		}
		if (hintEightUsed) {
			score = (score/10 * 4.0);
		}
		if (hintNineUsed) {
			score = (score/10 * 5.0);
		}
	}
	
	public static void resetHints() {//use this whenever there's a new graph, or whenever a new game is started!
		hintOneUsed = false;
		hintTwoUsed = false;
		hintThreeUsed = false;
		hintFourUsed = false;
		hintFiveUsed = false;
		hintSixUsed = false;
		hintSevenUsed = false;
		hintEightUsed = false;
		hintNineUsed = false;
		GameMode.hint4 = false;
		GameMode.hint9 = false;
	}
	
}
