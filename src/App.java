// Jacob Conrad and Jack Handy, Final Project, May 5, 2021, The app class extends JFrame.
// This class creates an AvatarHolder, GameBoard, ControlPanel, and TimingPanel. 
// It also loops music to play in the background of the game.

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class App extends JFrame {	
    
	private AvatarHolder avatarHolder;
	private ControlPanel controlPanel;
	private TimingPanel timingPanel;
    private Clip clip;
    private AudioInputStream audioInputStream;
    private static String filePath = "sound/game_sound.wav";
	
    
    // App constructor
	public App() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super("Avoid The Enemies!");
        this.setLayout(new BorderLayout());
        avatarHolder = new AvatarHolder(null);
        
        GameBoard gameBoard = new GameBoard(avatarHolder, this);
        
        timingPanel = new TimingPanel(gameBoard);
        
        controlPanel = new ControlPanel(gameBoard, timingPanel);
        
        this.setSize(1000, 900);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.add(controlPanel, BorderLayout.SOUTH);
        this.add(gameBoard, BorderLayout.CENTER);
        this.add(timingPanel, BorderLayout.NORTH);
        
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
          
        clip = AudioSystem.getClip();

        clip.open(audioInputStream);
          
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        this.setVisible(true);
    }
	
	// Calls control panel's hideAvatarPanel method
	public void hideAvatarPanel()
	{
		controlPanel.hideAvatarPanel();
	}
	
	// Calls control panel's hideDifficultyPanel method
	public void hideDifficultyButtons()
	{
		controlPanel.hideDifficultyPanel();
	}
	
	// Calls timing panel's hidePauseButton method
	public void hidePauseButton()
	{
		timingPanel.hidePauseButton();
	}
	
	// Calls timing panel's showPauseButton method
	public void showPauseButton()
	{
		timingPanel.showPauseButton();
	}

	// Main method, creates app object
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new App();
    }
}