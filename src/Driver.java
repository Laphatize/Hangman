/*	Hang-man Rewritten
 * 	Time Startelld => 9:58PM 11/25/19
 *  Time Ended => 
 *  Code Available @ https://laphatize.com/to/Hangman.html 
 */

// Prerequisites
import javax.swing.*;

// Core
public class Driver {
	public static void main(String[] args) throws Exception {
		// Generic JFrame Garble
		System.out.println("[DEBUG] Program started!");
		JFrame frame = new JFrame("Hangman - FULL SCREEN PLEASE :)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new gamePanel());
		frame.pack();
		frame.setVisible(true);	
	}
}
