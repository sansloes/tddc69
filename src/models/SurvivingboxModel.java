package models;

import java.awt.*;

import javax.swing.ImageIcon;

import view.Sprite;
public class SurvivingboxModel extends Sprite {

	public SurvivingboxModel() {
		Image image;
		image = new ImageIcon("images/survivingbox.png").getImage();
		this.setImage(image);
		this.setPosition(370, 170);
	} 
}

