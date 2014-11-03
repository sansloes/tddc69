package models;

import java.awt.*;

import javax.swing.ImageIcon;

import view.Sprite;
public class NegativeMoneyModel extends Sprite {
	private Image image;

	public NegativeMoneyModel() {
	}
	public NegativeMoneyModel(double x, double y){

		image = new ImageIcon("images/negativemoney.png").getImage();
		super.setPosition(x, y);
		super.setMotion(-2, 0);
		super.setImage(image);
	}

	//when called it changes the negativemoney motion in the y-axis
	public void clocktick()
	{
		super.clocktick();
		super.setDY(Math.sin(getX()/50));
	}


}
