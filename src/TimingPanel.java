// Jacob Conrad and Jack Handy, Final Project, May 5, TimingPanel class extends JPanel.
// This class has a border layout that contains the pause and start buttons.

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TimingPanel extends JPanel {
	
	private GameBoard gameBoard;
	private JButton start;
	private JButton pause;
	
	// TimingPanel constructor
	public TimingPanel(GameBoard gameBoard) {
		this.setLayout(new BorderLayout());
		this.gameBoard = gameBoard;
		pause = new JButton("pause");
		start = new JButton("start");
		
		start.setFocusable(false);
		pause.setFocusable(false);

		this.add(pause, BorderLayout.NORTH);
		this.add(start, BorderLayout.CENTER);
        
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
}