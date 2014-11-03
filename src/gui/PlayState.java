package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import view.DeathZone;
import view.GameView;
import view.ScoreZone;

/**
 * This is the game state where you play your game.  It adds two separate JPanels in its JPanel where the game get drawn
 */

public class PlayState extends AbstractGameState {
	/**
	 * @uml.property  name="sZone"
	 * @uml.associationEnd  
	 */
	final private ScoreZone sZone;
	/**
	 * @uml.property  name="dZone"
	 * @uml.associationEnd  
	 */
	final private DeathZone dZone;
	private int score;  


	// adds the two game zones and starts the clocktick
	public PlayState(GameView view) {
		super(view);
		this.sZone = new ScoreZone(this);
		this.setLayout(new GridLayout(1, 2, 0, 0));
		sZone.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(sZone);

		this.dZone = new DeathZone(this);
		dZone.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(dZone);
		this.getView().startClock(20);
	}

	//when a key its pressed it adds it to sZone button Hashset
	public void handleKeyPressed(KeyEvent e) {
		this.sZone.addkeytable(e.getKeyCode());
	}
	//tells the sZone and dZone that a key has been released
	public void handleKeyReleased(KeyEvent event) {
		this.dZone.released(event.getKeyCode());
		this.sZone.removekey(event.getKeyCode());

	}  
	//takes the score from scoreZone
	public void Scorecount(int score){
		this.score=score;
	}
	// Stops the clocktick and change to GameOverState
	public void Gameover(){
		this.getView().stopClock();
		this.getView().setState(new GameOverState(this.getView(),score));


	}
	//sends the clocktick to sZone and dZone
	public void clocktick() {

		this.sZone.clocktick();
		this.dZone.clocktick();
	}
}
