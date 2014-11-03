package view;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


/**
 * the abstract class for all our models
 */
public abstract class Sprite
{
	private Image image;
	/**
	 * @uml.property  name="rect"
	 */
	private Rectangle2D rect;
	private Point2D motion;

	public Sprite()
	{
		this.image = null;
		this.rect = new Rectangle2D.Double(0.0, 0.0, 0.0, 0.0);
		this.motion = new Point2D.Double(0.0, 0.0);
	}

	/**
	 * @param image
	 * @uml.property  name="image"
	 */
	public void setImage(Image image)
	{
		this.image = image;
		this.rect.setFrame(rect.getX(), rect.getY(), image.getWidth(null) ,image.getHeight(null) );
	}

	public void setPosition(double x, double y)
	{
		this.rect.setFrame(x,y,getWidth(),getHeight());
	}

	/**
	 * @return
	 * @uml.property  name="rect"
	 */
	public Rectangle2D getRect()
	{
		return this.rect;
	}

	public double getX()
	{
		return this.rect.getX();
	}

	public double getY()
	{
		return this.rect.getY();
	}

	public double getWidth()
	{
		return this.rect.getWidth();
	}

	public double getHeight()
	{
		return this.rect.getHeight();
	}

	public void setMotion(double dx, double dy)
	{
		this.motion.setLocation(dx, dy);
	}

	public double getDX()
	{
		return this.motion.getX();
	}

	public double getDY()
	{
		return this.motion.getY();
	}
	public void setDY(double dy){
		this.motion.setLocation(getDX(), dy);
	}
	public void setDX(double dx){
		this.motion.setLocation(dx, getDY());
	}
	public void drawOn(Graphics g)
	{
		g.drawImage(this.image,(int)this.rect.getX(), (int)this.rect.getY(), null);
	}

	public void clocktick()
	{
		this.rect.setRect((getX() +getDX()), (getY()+ getDY()) , getWidth(), getHeight());
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

