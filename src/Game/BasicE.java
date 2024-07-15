package Game;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Data.Sprite;
import logic.Control;

public class BasicE extends Object {
	
	public Sprite s;
	public Handle handle;
	
	public BasicE(int x, int y, ObjectID id, int velX, int velY) {
		super(x,y, id);
		//this.handle = handle;
		this.velX = velX;
		this.velY = velY;
		
	}
	
	public void tick() {
		
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= 768) velY *=-1;
		if(x <= 0 || x >= 1248) velX *=-1;
	}
	
	public void update(Control ctrl) {
		BufferedImage pImage = ctrl.getSpriteFromBackBuffer("ss").getSprite();
		BufferedImage bi2 = pImage.getSubimage(306, 360, 60, 60);
		BufferedImage bi3 = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
		//BufferedImage bi2 = pImage.getSubimage(306, 360, 60, 60);
		Graphics g = bi3.getGraphics();
		g.drawImage(bi2, x, y, null);
		s = new Sprite(x,y, bi2, "alien");
		
		ctrl.addSpriteToFrontBuffer(s);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y, 32, 32);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
