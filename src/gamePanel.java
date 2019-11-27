import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class gamePanel extends JPanel {

	public ArrayList<String> isolatedStrings = new ArrayList<String>();
	public ArrayList<String> holder = new ArrayList<String>();
	public String word;
	public int score = 0;
	public int lives = 5;
	private JLabel dash, notification;
	private JTextField userInput;
	private JLabel label1;
	private ImageIcon icon;
	private JRadioButton random;
	private JRadioButton history;
	private JRadioButton computer;
	public File filth;
	
	ArrayList<String> importedWords = new ArrayList<String>();
	ArrayList<Integer> location = new ArrayList<Integer>();

	public gamePanel() throws Exception {

		filth = new File("C:\\Users\\lapha\\eclipse-workspace\\HangmanRewritten\\src\\Database");
		Scanner sc = new Scanner(filth);

		while (sc.hasNextLine()) {
			importedWords.add(sc.nextLine());
		}

		System.out.println("[DEBUG] Database has been synced with the program!");

		dash = new JLabel("");
		userInput = new JTextField(5);
		notification = new JLabel("");
		random = new JRadioButton("Random");
		computer = new JRadioButton("Comp Sci");
		history = new JRadioButton("History");
		// When user clicks enter or exits the input box it will run some other
		// components.
		userInput.addActionListener(new Submit());

		icon = new ImageIcon("C:\\Users\\lapha\\eclipse-workspace\\HangmanRewritten\\src\\Frames\\Black5.png");

		add(dash);
		label1 = new JLabel(icon, SwingConstants.CENTER);
		add(label1);
		/// remove(label1);
		add(userInput);
		
		ButtonGroup group = new ButtonGroup();
		group.add(random);
		
	
		group.add(computer);
	
	
		
		Boomer listener = new Boomer();
		random.addActionListener(listener);
		
		computer.addActionListener(listener);
		add(random);
		add(computer);
		generate();

		// This is to generate the _ for the game.

		for (int i = 0; i < word.length(); i++) {
			holder.add("_");
			dash.setText(dash.getText() + "_");
			score++;
		}

		for (int i = 0; i < word.length(); i++) {

			isolatedStrings.add(String.valueOf(word.charAt(i)));
			System.out.println("[DEBUG] [63] The array isolatedStrings contains the following: " + isolatedStrings);

		}

	}
	

	private class Boomer implements ActionListener {

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		
		if (source == random) {
			filth = new File("C:\\Users\\lapha\\eclipse-workspace\\HangmanRewritten\\src\\Database");
			
		} else {
			filth = new File("C:\\Users\\lapha\\eclipse-workspace\\HangmanRewritten\\src\\DatabaseC");
				
		}
		
		
		generate();

		holder.clear();
		isolatedStrings.clear();

		for (int i = 0; i < word.length(); i++) {
			holder.add("_");
			dash.setText(dash.getText() + "_");
			score++;
		}

		for (int i = 0; i < word.length(); i++) {

			isolatedStrings.add(String.valueOf(word.charAt(i)));
			System.out.println("[DEBUG] [122] [DB TYPE CHANGE] The array isolatedStrings contains the following: " + isolatedStrings);

		}
		
		
	}
	}

	private class Submit implements ActionListener {
		public void actionPerformed(ActionEvent event) {
	
			if (lives == 0) {
				
				remove(userInput);
				remove(label1);
				
				add(notification);
				notification.setText("You lost the game! The word was " + word + ". Good luck next time!");
				
			} else {
			// iconic lines of code <3
			//icon = new ImageIcon("C:\\Users\\lapha\\eclipse-workspace\\HangmanRewritten\\src\\Frames\\Black3.png");
			//label1.setIcon(icon);
		
			
			

			if (lives != -1) {
				System.out.println(lives);
				if (dash.getText().equals(word)) {
				
					dash.setText("You win!");
				} else {

					System.out.println("[DEBUG] [70] The array holder contains the following: " + holder);
					// Isolate the Random Word into "w" "o" "r" "d".

					if (!isolatedStrings.contains(userInput.getText())) {
						System.out.println("[DEBUG] " + userInput.getText());
						System.out.println("[GAME] Bad guess! What a boomer!");
						lives--;
					} else {

						for (int i = 0; i < word.length(); i++) {

							if (isolatedStrings.contains(userInput.getText())) {

								if (isolatedStrings.get(i).contains(userInput.getText()))

									holder.set(i, isolatedStrings.get(i));

							} else {
							
								lives--;
							}
						}
						if (isolatedStrings.contains(userInput.getText())) {
							dash.setText("");
							for (int x = 0; x < word.length(); x++) {

								// System.out.println(holder.get(x));
								// System.out.println(holder);
								dash.setText(dash.getText() + holder.get(x));
							}
						} else {
							lives--;
						}
					}

				}
			}
			
			
			if (lives == 0) {
				icon = new ImageIcon("C:\\Users\\lapha\\eclipse-workspace\\HangmanRewritten\\src\\Frames\\Black5.png");
				label1.setIcon(icon);
			}
			
			if (lives == 1) {
				icon = new ImageIcon("C:\\Users\\lapha\\eclipse-workspace\\HangmanRewritten\\src\\Frames\\Black4.png");
				label1.setIcon(icon);
			}
			
			if (lives == 2) {
				icon = new ImageIcon("C:\\Users\\lapha\\eclipse-workspace\\HangmanRewritten\\src\\Frames\\Black3.png");
				label1.setIcon(icon);
			}
			
			if (lives == 3) {
				icon = new ImageIcon("C:\\Users\\lapha\\eclipse-workspace\\HangmanRewritten\\src\\Frames\\Black2.png");
				label1.setIcon(icon);
			}
			
			if (lives == 4) {
				icon = new ImageIcon("C:\\Users\\lapha\\eclipse-workspace\\HangmanRewritten\\src\\Frames\\Black1.png");
				label1.setIcon(icon);
			}
			
			if (lives == 5) {
				icon = new ImageIcon("C:\\Users\\lapha\\eclipse-workspace\\HangmanRewritten\\src\\Frames\\Black0.png");
				label1.setIcon(icon);
			}
		}
		}
	}

	public void generate() {
		// Generate a word from our list.
		Collections.shuffle(importedWords);
		word = importedWords.get(0);
	}

}