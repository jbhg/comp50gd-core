/**
 * This code is based off of 'Sprite.java' by Ming Chow, Spring 2010.
 */

package comp50gd;

import java.awt.Graphics;
import java.awt.Image;

public class Sprite
{

	private Image image;
	
	public Sprite(Image img)
	{
		this.image = img;
	}
	
	public void draw(Graphics g, int x, int y)
	{
		g.drawImage(image, x, y, null);
	}
	public void draw(Graphics g, int x, int y, int width, int height)
	{
		g.drawImage(image.getScaledInstance(width, height, Image.SCALE_FAST), x, y, null);
	}
	
	public int getWidth() { return image.getWidth(null); }
	public int getHeight(){ return image.getHeight(null);}

}