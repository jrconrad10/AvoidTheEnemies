// Jacob Conrad and Jack Handy, Final Project, May 5, ControlPanel class extends JPanel.
// This class has a border layout that contains the AvatarPanel, DifficultyPanel, and QuitButton.

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	
	private AvatarButtonPanel avatarPanel;
	private DifficultyPanel difficultyPanel;
	private TimingPanel timingPanel;
	
	// ControlPanel constructor
	public ControlPanel(GameBoard gameBoard, TimingPanel timingPanel) {
		this.setLayout(new BorderLayout());
		this.timingPanel = timingPanel;
		avatarPanel = new AvatarButtonPanel(gameBoard);
		difficultyPanel = new DifficultyPanel(gameBoard);
		JButton quitButton = new JButton("Quit");

		this.add(difficultyPanel, BorderLayout.NORTH);
		this.add(avatarPanel, BorderLayout.CENTER);
		this.add(quitButton, BorderLayout.SOUTH);
		
		quitButton.setFocusable(false);
		
		quitButton.addActionListener(new ActionListener() {

			@Override
			// Resets the game back to the main menu
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameBoard.setToDefault();
				avatarPanel.setVisible(true);
				difficultyPanel.setVisible(true);
				timingPanel.showStart();
			}			
		});
	}
	
	// Hides the avatarPanel
	public void hideAvatarPanel()
	{
		avatarPanel.setVisible(false);
	}
	
	// Hides the difficultyPanel
	public void hideDifficultyPanel()
	{
		difficultyPanel.setVisible(false);
	}
}