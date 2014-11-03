package gui;

import java.awt.*;
import javax.swing.*;

import view.GameView;

public class GameFrame extends JFrame 
{
	public GameFrame() 
	{
		this.setTitle("Multi-tasking game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1500,600);
		this.setLocation(50,50);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.WHITE);

		GameView view = new GameView();
		view.setState(new IntroState(view));
		this.add(view, BorderLayout.CENTER);

		this.setVisible(true);
	}

	public static void main(String[] args) 
	{
		new GameFrame();
	}
}