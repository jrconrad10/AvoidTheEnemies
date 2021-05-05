// Jacob Conrad and Jack Handy, Final Project, May 5, TimingPanel class extends JPanel.
// This class has a border layout that contains the pause button, start button, and a lives label.

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimingPanel extends JPanel {
	
	private GameBoard gameBoard;
	private JButton start;
	private JButton pause;
	private JLabel lives;
	
	// TimingPanel constructor
	public TimingPanel(GameBoard gameBoard) {
		this.setLayout(new BorderLayout());
		this.gameBoard = gameBoard;
		pause = new JButton("pause");
		start = new JButton("start");
		lives = new JLabel(gameBoard.getLives() + " lives", 0);
		
		lives.setFocusable(false);
		start.setFocusable(false);
		pause.setFocusable(false);

		this.add(lives, BorderLayout.NORTH);
		this.add(pause, BorderLayout.CENTER);
		this.add(start, BorderLayout.SOUTH);
        
        start.addActionListener(new ActionListener() {

        	@Override
        	// Hides the start button and calls the method to start the game
			public void actionPerformed(ActionEvent e) {
				start.setVisible(false);
				gameBoard.startGame();
			}      	
        });
        
		
		pause.addActionListener(new ActionListener() {

			@Override
			// Sets the paused boolean value to the opposite of what it is and then calls the pause method in the game board.
			public void actionPerformed(ActionEvent e) {
				gameBoard.setPaused();
				gameBoard.pause(gameBoard.getPaused());
			}		
		});
	}
	
	// Makes the start button visible
	public void showStart()
	{
		start.setVisible(true);
	}
	
	// Makes the pause button not visible
	public void hidePauseButton()
	{
		pause.setVisible(false);
	}
	
	// Makes the pause button visible
	public void showPauseButton()
	{
		pause.setVisible(true);
	}
	
	// Changes the number of lives in the label
	public void changeLives()
	{
		lives.setText(gameBoard.getLives() + " lives");
	}
}