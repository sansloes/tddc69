package view;

import gui.AbstractGameState;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author  Oliver
 */
public class GameView extends JComponent
{
    /**
	 * @uml.property  name="state"
	 * @uml.associationEnd  
	 */
    private AbstractGameState state;
    private Timer timer;
    public GameView(){
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
        this.setLayout(new BorderLayout());
        this.transferFocus();
        
    }
    
    /**
	 * @param state
	 * @uml.property  name="state"
	 */
    public void setState(AbstractGameState state)
    {
        this.state = state;
        this.removeAll();
        this.add(state,BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void paintComponent(Graphics g)
    {
        state.drawOn(g);
    }
    
    public void startClock(int milliseconds)
    {
        timer = new Timer(milliseconds, new TimerHandler());
        timer.start();
    }
    
    public void stopClock()
    {
        this.timer.stop();
    }
    


    
    class KeyHandler extends KeyAdapter
    {
        public void keyPressed(KeyEvent event)
        {
            state.handleKeyPressed(event);
        }
        public void keyReleased(KeyEvent event)
        {
            state.handleKeyReleased(event);
        }
    }
    
    class TimerHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            state.clocktick();
            repaint();
        }
    }

}
