package models;
import java.awt.*;

import javax.swing.ImageIcon;

import view.Sprite;
public class PowerupModel extends Sprite {
	private Image image;

	public PowerupModel() {
	}
	public PowerupModel(double x, double y){

		image = new ImageIcon("images/Powerupcoin.png").getImage();
		super.setPosition(x, y);
		super.setMotion(-2, 0);
		super.setImage(image);
	}

	//when called it changes the powerupmodels motion in the y-axis
	public void clocktick()
	{
		super.clocktick();
		super.setDY(Math.sin(getX()/50));
	}


}