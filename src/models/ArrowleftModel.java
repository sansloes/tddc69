package models;

import java.awt.Image;

import javax.swing.ImageIcon;

import view.Sprite;
public class ArrowleftModel extends Sprite {

	public ArrowleftModel() {
		Image image;
		image = new ImageIcon("images/arrowleft.png").getImage();
		this.setImage(image);
		this.setMotion(-2, 0);
		this.setPosition(250, 250);
	} 
	public ArrowleftModel(double x, double y, double motion){

		Image image = new ImageIcon("images/arrowleft.png").getImage();
		super.setPosition(x, y);
		super.setMotion(motion, 0);
		super.setImage(image);
	}
}

