package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Input.Mouse;
import logic.Control;
import Data.RECT;
import Data.Sprite;


//This is the class for the enemy object that will be an impostor in the game
public class Impostor extends Object {
	
	
	public RECT r;
	public String s2 = "";
	public final int dropShadow = 2;
	private Sprite s;
	
	public Impostor(int x, int y, ObjectID id) {
		super(x, y, id);
		
		velX = 2;
		//velY =2;
	}

	@Override
	public void tick() {
		x += velX; // horizontal velocity
		y += velY; // vertical velocity
		
		//bounds for enemy to bounce
		if(y <= 0 || y >= 768) velY *=-1; // height of screen
		if(x <= 0 || x >= 1248) velX *=-1; // length of screen
		
	}

	@Override
	public void update(Control ctrl) {
		BufferedImage pImage = ctrl.getSpriteFromBackBuffer("ss").getSprite();
		BufferedImage bi2 = pImage.getSubimage(456, 482, 64, 64);
		BufferedImage bi3 = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi3.getGraphics();
		g.drawImage(bi2, x, y, null);
		s = new Sprite(x,y, bi2, "hologram");
		ctrl.addSpriteToFrontBuffer(s);
		
		r = new RECT(x, y, x+ 64, y+64, "Hologram, NO DAMAGE"); // add hover label on rect
		Point p = Mouse.getMouseCoords();
		int a = (int)p.getX();
		int b = (int)p.getY();
		
		if(r.isCollision(a, b)) 
			s2 = r.getHoverLabel();
		else
			s2 = "";
		ctrl.drawString(a, (b-2), s2, Color.YELLOW);
		ctrl.drawString(a-dropShadow, ((b-dropShadow)-2), s2, Color.BLUE);
		
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
