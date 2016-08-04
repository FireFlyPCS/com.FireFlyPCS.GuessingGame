package com.FireFlyPCS.GuessingGame;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class GuessingGame {

	// Scanner for users input on guess
	static Scanner guessInput = new Scanner(System.in);

	// Play Audio
	static void PlaySound(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);

			clip.start();

			Thread.sleep(clip.getMicrosecondLength() /1100);
			// Delays frame until sound finishes

		} catch (Exception e)

		{

		}

	}

	public static void main(String[] args) {

		// Sound initialized
		File nGame = new File("C:\\Users\\Ziifnab\\workspace\\com.FireFlyPCS.GuessingGame\\res\\sound\\game.wav");
		File lose = new File(
				"C:\\Users\\Ziifnab\\workspace\\com.FireFlyPCS.GuessingGame\\res\\sound\\yougetnothing.wav");
		File gOver = new File("C:\\Users\\Ziifnab\\workspace\\com.FireFlyPCS.GuessingGame\\res\\sound\\gameover.wav");
		File applause = new File(
				"C:\\Users\\Ziifnab\\workspace\\com.FireFlyPCS.GuessingGame\\res\\sound\\Applause.wav");
		File haha = new File("C:\\Users\\Ziifnab\\workspace\\com.FireFlyPCS.GuessingGame\\res\\sound\\haha.wav");

		// Test to play again, or user quits
		boolean keepPlaying = true;

		// Main sequence start
		PlaySound(nGame);
		JOptionPane.showMessageDialog(null, "Get ready to play my guessing game!");

		while (keepPlaying) {
			boolean validInput = true;
			int number, tries = 3;
			double guess = 0;
			String answer;

			// Random number generator
			number = (int) (Math.random() * 10) + 1;

			// Acquire guess from user
			do {
					do {
						
						String g;
						g = JOptionPane.showInputDialog(
								"I'm thinking of a number between 1 and 10. What do you think it is? I'll give you "
										+ tries + " more tries.");

						try { // Tries to catch Alex cheating by inputting Double
							  // variables to crash program ;)
							
							guess = Double.parseDouble(g);
							
						} catch (NumberFormatException e) 
						{
							JOptionPane.showMessageDialog(null, "Nice try Alex!");
						}
						
						validInput = true;
						if ((guess < 1) || (guess > 10)) {
							JOptionPane.showMessageDialog(null, "Nice try, I said BETWEEN 1 and 10!" + " Try again: ");
							validInput = false;
						}
							
						
					} while (!validInput);

				// Counts down number of tries left
				tries--;

				// Check the guess
				if (guess == number) {
					PlaySound(applause);
					JOptionPane.showMessageDialog(null, "Correct! Did you cheat????");

				}

				else if (tries >= 1) {
					PlaySound(haha);
					JOptionPane.showMessageDialog(null, "Go ahead and try again. You have " + tries + " left.");
				}

				else if (tries == 0) {
					PlaySound(lose);
					JOptionPane.showMessageDialog(null, "Sorry, you lose!");
				}

			} while (guess != number && tries > 0);

			// play again?

			do {

				answer = JOptionPane.showInputDialog("Would you like to try again? (Y or N)");

				validInput = true;
				if (answer.equalsIgnoreCase("Y"))
					;

				else if (answer.equalsIgnoreCase("N"))
					keepPlaying = false;
				else
					validInput = false;
			} while (!validInput);

		}

		PlaySound(gOver);

	}

}
