package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JLabel;

import view.GameView;

/**The menu state here you can choose to start the game */

public class IntroState extends AbstractGameState
{

	public IntroState(GameView view)
	{	
		super(view);
		this.setBackground(new Color(255,255,204));
		this.setLayout(new GridLayout(0, 1,0,0));
		JLabel label = new JLabel("Menu",JLabel.CENTER);
		label.setFont(new Font("TimesRoman", Font.PLAIN, 100));
		this.add(label,BorderLayout.NORTH);
		this.add(new JLabel("    The controls for the left zone is SPACE                     for the right one it's UP and DOWN arrows ", JLabel.CENTER), BorderLayout.CENTER);

		this.add(new JLabel("You press SPACE to start", JLabel.CENTER), BorderLayout.CENTER);





	}



	public void handleKeyPressed(KeyEvent event)
	{
		//starts the game if you press space
		if (event.getKeyCode() == KeyEvent.VK_SPACE) 
		{
			this.getView().setState(new PlayState(this.getView()));
		}
	}
}