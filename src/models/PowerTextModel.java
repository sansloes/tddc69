package models;
import java.awt.*;

import javax.swing.ImageIcon;

import view.Sprite;
public class PowerTextModel extends Sprite {

	public PowerTextModel() {
		Image image;
		image = new ImageIcon("images/POWERUP2.png").getImage();
		this.setImage(image);
		this.setPosition(100, 0);
	} 
}
