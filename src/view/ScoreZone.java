package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

import models.*;
import gui.*;

/**
 * The class that takes care of all the drawings on the left side on the playstate
 */

public class ScoreZone extends JPanel {

	/**
	 * @uml.property  name="play"
	 * @uml.associationEnd  
	 */
	private PlayState play;
	Random randomizer = new Random();
	private HashSet<Integer> keytable = new HashSet<Integer>(); 
	/**
	 * @uml.property  name="vessel"
	 * @uml.associationEnd  
	 */
	private VesselModel vessel = new VesselModel();

	//-------------------------
	private int powerupcounter;
	private int objects= 8;
	private double chance= 0.02;
	private boolean drawpoweruptext;
	/**
	 * @uml.property  name="powertext"
	 * @uml.associationEnd  
	 */
	private PowerTextModel powertext = new PowerTextModel();
	//-------------------------
	private int score = 0;
	private JLabel scoreboard = new JLabel(String.format("%S", score));
	//-------------------------

	LinkedList<NegativeMoneyModel> NegativemoneyList = new LinkedList<NegativeMoneyModel>();
	LinkedList<NegativeMoneyModel> RemoveNegativemoneyList = new LinkedList<NegativeMoneyModel>();
	LinkedList<PlusMoneyModel> PlusmoneyList = new LinkedList<PlusMoneyModel>();
	LinkedList<PlusMoneyModel> RemovePlusmoneyList = new LinkedList<PlusMoneyModel>();
	LinkedList<PowerupModel> PowerupList = new LinkedList<PowerupModel>();
	LinkedList<PowerupModel> RemovePowerupList = new LinkedList<PowerupModel>();



	public ScoreZone(PlayState play){
		this.play=play;
		this.setBackground(new Color(204,255,204));
		this.add(scoreboard,BorderLayout.PAGE_START);



	}
	public void addkeytable(int key){
		this.keytable.add(key);
	}
	public void removekey(int key){
		this.keytable.remove(key);
	}
	public boolean ispressed(int e){
		return this.keytable.contains(e);
	}

	//All the things we are going to do each clocktick
	public void clocktick()  {

		play.Scorecount(score);
		vessel.setMotion(0, 2.6);

		if (ispressed(KeyEvent.VK_SPACE)){
			vessel.setMotion(0,-2.6 );
		}

		vessel.clocktick();

		powerupcounter++;
		if (powerupcounter > 500) {
			chance = 0.02;
			objects = 8;
			drawpoweruptext = false;
			//		System.out.println("powerslut");
		}


		//Plusmoney----------------------------------------

		//If the coin intersects with the vessel or gets out of the JPanel it adds it to the remove list.
		//Else it changes the motion of it with the clocktick method
		if (this.PlusmoneyList.size() > 0) {
			for (PlusMoneyModel y : this.PlusmoneyList) {
				PlusMoneyModel temp = y;
				if (temp.getRect().intersects(vessel.getRect()))
				{
					score = score + 10;
					RemovePlusmoneyList.add(temp);
				}
				else if (temp.getX() < 0)
				{
					RemovePlusmoneyList.add(temp);
				}
				else {	
					temp.clocktick();

				}


			}
		}
		//removes all the selected objects in PlusemoneyList and then clears the list
		if (this.RemovePlusmoneyList.size() > 0) {
			for (PlusMoneyModel y : this.RemovePlusmoneyList) {
				PlusMoneyModel temp = y;
				PlusmoneyList.remove(temp);
			}
			RemovePlusmoneyList.clear();
		}
		//There is a 0.02 chance standard but if you take a powerup it will change to 0.08
		//and only make a new object if there is less then 8 (standard) and 20 when you have powerup.

		if (this.PlusmoneyList.size() < objects && randomizer.nextDouble()< chance) {

			this.PlusmoneyList.add(new PlusMoneyModel(750,randomizer.nextDouble()*460+40));//random the position of the coin in the y-axis

		}

		//Negativemoney-------------------------------------

		//If the coin intersects with the vessel or gets out of the JPanel it adds it to the remove list.
		//Else it changes the motion of it with the clocktick method

		if (this.NegativemoneyList.size() > 0) {
			for (NegativeMoneyModel y : this.NegativemoneyList) {
				NegativeMoneyModel temp = y;
				if (temp.getRect().intersects(vessel.getRect()))
				{
					if (score>20){
						score = score - 20;
					}else { score = 0;}
					RemoveNegativemoneyList.add(temp);
				}
				else if (temp.getX() < 0)
				{
					RemoveNegativemoneyList.add(temp);
				}else{
					temp.clocktick();

				}


			}
		}
		//removes all the selected objects in NegativemoneyList and then clears the list
		if (this.RemoveNegativemoneyList.size() > 0) {
			for (NegativeMoneyModel y : this.RemoveNegativemoneyList) {
				NegativeMoneyModel temp = y;
				NegativemoneyList.remove(temp);
			}
			RemoveNegativemoneyList.clear();
		}
		//There is a 0.02 chance it will add a negative money if there is less then five on the playing field
		if (this.NegativemoneyList.size() < 5 && randomizer.nextDouble()< 0.02) {

			this.NegativemoneyList.add(new NegativeMoneyModel(750,randomizer.nextDouble()*460+40));//random the position of the coin in the y-axis

		}




		//Powerup-------------------------------------------

		//If the coin intersects with the vessel or gets out of the JPanel it adds it to the remove list.
		//Else it changes the motion of it with the clocktick method

		if (this.PowerupList.size() > 0) {
			for (PowerupModel y : this.PowerupList) {
				PowerupModel temp = y;
				if (temp.getRect().intersects(vessel.getRect()))
				{
					chance= 0.08;
					objects = 20;
					powerupcounter = 0;
					RemovePowerupList.add(temp);
					drawpoweruptext = true;
					//	System.out.println("powerstart");
				}
				else if (temp.getX() < 0)
				{
					RemovePowerupList.add(temp);
				}else{
					temp.clocktick();
				}

			}
		}

		//removes all the selected objects in PowerupList and then clears the list
		if (this.RemovePowerupList.size() > 0) {
			for (PowerupModel y : this.RemovePowerupList) {
				PowerupModel temp = y;
				PowerupList.remove(temp);
			}
			RemovePowerupList.clear();
		}	

		//There is a 0.001 chance it will add a powerup if there is noone on the playing field
		if (this.PowerupList.size() < 1 && randomizer.nextDouble()< 0.001) {
			System.out.println("GÖR POWERUP");
			this.PowerupList.add(new PowerupModel(750,randomizer.nextDouble()*460+40));//random the position of the coin in the y-axis

		}
		//--------------------------------------------------

		scoreboard.setText("Score : "+" "+String.format("%S", score));

	}




	//Here we paint all the objects on the JPanel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.PlusmoneyList.size() > 0) {
			for (Object y : this.PlusmoneyList) {
				PlusMoneyModel temp = (PlusMoneyModel)y;
				temp.drawOn(g);
			}
		}
		if (this.NegativemoneyList.size() > 0) {
			for (Object y : this.NegativemoneyList) {
				NegativeMoneyModel temp = (NegativeMoneyModel)y;
				temp.drawOn(g);
			}
		}
		if (this.PowerupList.size() > 0) {
			for (Object y : this.PowerupList) {
				PowerupModel temp = (PowerupModel)y;
				temp.drawOn(g);
			}
		}
		if (vessel.getY()<5) {
			vessel.setPosition(vessel.getX(), 5);
		}
		if (vessel.getY()>550) {
			vessel.setPosition(vessel.getX(), 550);
		}
		this.vessel.drawOn(g);

		if (drawpoweruptext){
			this.powertext.drawOn(g);

		}
	}

}






