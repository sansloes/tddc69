package gui;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import view.GameView;

/**
 * @author  Oliver
 */
public class AbstractGameState extends JPanel{
	/**
	 * @uml.property  name="view"
	 * @uml.associationEnd  
	 */
	private GameView view;



	public AbstractGameState(GameView view){
		this.view = view;
	}

	/**
	 * @return
	 * @uml.property  name="view"
	 */
	public GameView getView(){
		return this.view;
	}

	public void drawOn(Graphics g){}

	public void clocktick()
	{
	}

	public void handleKeyPressed(KeyEvent event){}
	public void handleKeyReleased(KeyEvent event) {}
}
