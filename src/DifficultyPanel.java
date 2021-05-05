// Jacob Conrad and Jack Handy, Final Project, May 5, DifficultyPanel class extends JPanel.
// This class is a panel for difficulty buttons.
// Each button switches the the current game difficulty to their respective difficulty by changing the number of enemies and the speed of the enemies.

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DifficultyPanel extends JPanel {
	
	// DifficultyPanel constructor
	public DifficultyPanel(GameBoard gameBoard) {
		this.setLayout(new FlowLayout());
		this.setBackground(Color.white);

		JButton easy = new JButton("Easy");
		JButton medium = new JButton("Medium");
		JButton hard = new JButton("Hard");
		
		easy.setFocusable(false);
		medium.setFocusable(false);
		hard.setFocusable(false);
		
		add(easy);
		add(medium);
		add(hard);
		
		easy.addActionListener(new ActionListener() {
			
			@Override
			// Sets game mode to easy
			public void actionPerformed(ActionEvent e) {
			// each time button is clicked, code in here is run
				gameBoard.setDifficulty(3, 5);
			}
			});

		medium.addActionListener(new ActionListener() {
			
			@Override
			// Sets game mode to medium
			public void actionPerformed(ActionEvent e) {
			// each time button is clicked, code in here is run
				gameBoard.setDifficulty(5, 7);
			}
			});
		
		hard.addActionListener(new ActionListener() {
			
			@Override
			// Sets game mode to hard
			public void actionPerformed(ActionEvent e) {
			// each time button is clicked, code in here is run
				gameBoard.setDifficulty(7, 8);
			}
			});
	}
}