package models;
import java.awt.*;

import javax.swing.ImageIcon;

import view.Sprite;
public class PlayerModel extends Sprite {

	public PlayerModel() {
		Image image;
		image = new ImageIcon("images/player.png").getImage();
		this.setImage(image);
		this.setPosition(374, 272);
	} 
	//moves the the player -49 in y-axis
	public void MoveupPlayer(){
		this.setPosition(getX(), (getY()-49));
	}
	//moves the the player 49 in y-axis
	public void MovedownPlayer(){
		this.setPosition(getX(), (getY()+49));
	}
}
