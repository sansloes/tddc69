package view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.LinkedList;
import java.util.Random;

import models.*;
import gui.*;



/**
 * @author  Oliver
 */
public class DeathZone extends JPanel {

	/**
	 * @uml.property  name="play"
	 * @uml.associationEnd  
	 */
	private PlayState play;
	private double motionleft= -2;
	private double motionright= 2;
	Random randomizer = new Random();
	//--------------------------------------------
	/**
	 * @uml.property  name="player"
	 * @uml.associationEnd  
	 */
	private PlayerModel player = new PlayerModel();
	/**
	 * @uml.property  name="survivingbox"
	 * @uml.associationEnd  
	 */
	private SurvivingboxModel survivingbox= new SurvivingboxModel();
	//--------------------------------------------
	LinkedList<ArrowleftModel> ArrowleftList = new LinkedList<ArrowleftModel>();
	LinkedList<ArrowrightModel> RemoveArrowrightList = new LinkedList<ArrowrightModel>();
	LinkedList<ArrowrightModel> ArrowrightList = new LinkedList<ArrowrightModel>();
	LinkedList<ArrowleftModel> RemoveArrowleftList = new LinkedList<ArrowleftModel>();


	public DeathZone(PlayState play){
		this.play=play;
		this.setBackground(new Color(153,255,255));


	}
	public void released(int e){
		if (KeyEvent.VK_UP == e && player.getY() > 190){// so the player doesn't get out of the box
			player.MoveupPlayer();
		}
		if (KeyEvent.VK_DOWN == e &&  player.getY() < 340){// so the player doesn't get out of the box
			player.MovedownPlayer();
		}

	}
	//All the things we are going to do each clocktick

	public void clocktick()  {

		motionleft = motionleft - 0.001;
		motionright = motionright +0.001;



		//------------------------------------------------------------------------------------------------	
		//If the arrow intersects with the player or gets out of the JPanel it adds it to the remove list.
		//Else it call clocktick
		if (this.ArrowrightList.size() > 0) {
			for (ArrowrightModel y : this.ArrowrightList) {
				ArrowrightModel temp = y;
				if (temp.getRect().intersects(player.getRect()))
				{ 
					play.Gameover();
				}
				else if (temp.getX() >750){
					RemoveArrowrightList.add(temp);
				}else {
					temp.clocktick();

				}

			}
		}
		if (this.ArrowleftList.size() > 0) {
			for (ArrowleftModel y : this.ArrowleftList) {
				ArrowleftModel temp = y;
				if (temp.getRect().intersects(player.getRect()))
				{
					play.Gameover();
				}
				else if (temp.getX() < 0){
					RemoveArrowleftList.add(temp);
				}else {
					temp.clocktick();

				}

			}
		}
		//-----------------------------------------------------------------------------------------------
		//removes all the selected objects in the remove arrows lists and then clears the lists
		if (this.RemoveArrowleftList.size() > 0) {
			for (ArrowleftModel y : this.RemoveArrowleftList) {
				ArrowleftModel temp = y;
				ArrowleftList.remove(temp);
			}
			RemoveArrowleftList.clear();
		}
		if (this.RemoveArrowrightList.size() > 0) {
			for (ArrowrightModel y : this.RemoveArrowrightList) {
				ArrowrightModel temp = y;
				ArrowrightList.remove(temp);
			}
			RemoveArrowrightList.clear();
		}
		//-----------------------------------------------------------------------------------------------
		//There is a 0.02 chance it will add an arrow if there is less then three on the playing field
		if (this.ArrowleftList.size() < 3 && randomizer.nextDouble()< 0.02) {

			this.ArrowleftList.add(new ArrowleftModel(750,((randomizer.nextInt(5) )*49) +178, motionleft));

		}
		if (this.ArrowrightList.size() < 3  && randomizer.nextDouble()< 0.02) {

			this.ArrowrightList.add(new ArrowrightModel(0, ((randomizer.nextInt(5) )*49) +178,motionright));

		}

	}





	//Here we paint all the objects on the JPanel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.survivingbox.drawOn(g);
		this.player.drawOn(g);

		if (this.ArrowleftList.size() > 0) {
			for (Object y : this.ArrowleftList) {
				ArrowleftModel temp = (ArrowleftModel)y;
				temp.drawOn(g);
			}
		}
		if (this.ArrowrightList.size() > 0) {
			for (Object y : this.ArrowrightList) {
				ArrowrightModel temp = (ArrowrightModel)y;
				temp.drawOn(g);
			}
		}
	}
}




