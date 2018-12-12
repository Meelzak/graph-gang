package GraphColoring;
/*
* Short summary:
* This is the class that calculates your score, based on how many hints you used during the game
*/

public class CalculateScore {

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
public double score = 100; //score is in percent

	//this can give the score (as a String) to another class, after it's calculated:
	public String giveScore() {
		calculate();
		return Integer.toString((int) score);
	}
	//calculates the score and subtracts points when hints are used
	//it can never be lower than 0, and when you use more hints, the score gets lower and lower
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
}
