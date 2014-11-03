package models;
import java.awt.*;

import javax.swing.ImageIcon;

import view.Sprite;
public class VesselModel extends Sprite {

	public VesselModel() {
		Image image;
		image = new ImageIcon("images/vessel.png").getImage();
		this.setImage(image);
		this.setMotion(0, 2.6);
		this.setPosition(50, 250);
	} 
}
