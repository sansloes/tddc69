package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JLabel;

import view.GameView;

/**You come to this state when you have lost the game, you can exit the game or play again*/


public class GameOverState extends AbstractGameState
{
	public GameOverState(GameView view,int score)
	{
		super(view);  

		this.setBackground(new Color(255,255,204));
		this.setLayout(new GridLayout(0, 1,0,0));
		JLabel label = new JLabel("GAME OVER",JLabel.CENTER);
		label.setFont(new Font("TimesRoman", Font.PLAIN, 100));
		this.add(label,BorderLayout.NORTH);
		this.add(new JLabel("You got"+ " "+String.format("%S", score) +" "+ "points", JLabel.CENTER), BorderLayout.CENTER);
		this.add(new JLabel("Press ENTER to retry and ESC to exit", JLabel.CENTER), BorderLayout.CENTER);
	}



	public void handleKeyPressed(KeyEvent event) {
		//exit the game if you press escape
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		//you will play again if you press enter
		if (event.getKeyCode() == KeyEvent.VK_ENTER){
			this.getView().setState(new PlayState(this.getView()));
		}
	} 

}
