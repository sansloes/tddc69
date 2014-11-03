package models;
import java.awt.*;

import javax.swing.ImageIcon;

import view.Sprite;
public class PlusMoneyModel extends Sprite {
	private Image image;

	public PlusMoneyModel(double x, double y){

		image = new ImageIcon("images/plusmoney1.png").getImage();
		super.setPosition(x, y);
		super.setMotion(-2, 0);
		super.setImage(image);
	}

	//when called it changes the plusmoney motion in the y-axis
	public void clocktick()
	{
		super.clocktick();
		super.setDY(Math.sin(getX()/50));
	}


}