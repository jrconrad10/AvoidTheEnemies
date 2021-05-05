// Jacob Conrad and Jack Handy, Final Project, May 5, 2021, AvatarButtonPanel class.
// This class is a panel for AvatarButtons. Each button switches the the current avatar to their respective avatar.

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AvatarButtonPanel extends JPanel {
	
	// AvatarButtonPanel constructor
	public AvatarButtonPanel(GameBoard gameBoard) {
		this.setLayout(new FlowLayout());
		this.setBackground(Color.white);

		JButton Avatar1 = new JButton("Deku");
		JButton Avatar2 = new JButton("Spy");
		JButton Avatar3 = new JButton("Apex");
		
		Avatar1.setFocusable(false);
		Avatar2.setFocusable(false);
		Avatar3.setFocusable(false);
		
		add(Avatar1);
		add(Avatar2);
		add(Avatar3);
		
		Avatar1.addActionListener(new ActionListener() {
			
			@Override
			// Sets avatar to Deku
			public void actionPerformed(ActionEvent e) {
				gameBoard.changeAvatar(1);
			}
			});

		Avatar2.addActionListener(new ActionListener() {
			
			@Override
			// Sets avatar to Spy
			public void actionPerformed(ActionEvent e) {
				gameBoard.changeAvatar(2);
			}
			});
		
		Avatar3.addActionListener(new ActionListener() {
			
			@Override
			// Sets avatar to Apex
			public void actionPerformed(ActionEvent e) {
				gameBoard.changeAvatar(3);
			}
			});
	}
}