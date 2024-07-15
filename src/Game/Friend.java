package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import logic.Control;
import Data.RECT;
import Data.Sprite;
import Input.Mouse;
import Data.Animation;
import Data.Frame;

//This calss will create ship that is a Friend, if picked up award player +100 HP
public class Friend extends Object{
	
	public RECT r;
	public String s2 = "";
	public final int dropShadow = 2;
	private Handle handle;
	private Sprite s;
	private Animation anim = new Animation(120, true);

	public Friend(int x, int y, ObjectID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Control ctrl) {
		/*BufferedImage pImage = ctrl.getSpriteFromBackBuffer("spc2").getSprite();
		BufferedImage bi2 = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi2.getGraphics();
		g.drawImage(pImage, x, y, null);
		s = new Sprite(x,y, pImage, "pImage");
		r = new RECT(1127, 80, 140, 184, "spc2", "LOST SHIP"); //RECT for lost friend ship 
		ctrl.addSpriteToFrontBuffer(s);*/
		
		BufferedImage pImage = ctrl.getSpriteFromBackBuffer("ss").getSprite();
		BufferedImage bi2 = pImage.getSubimage(356, 596, 115, 120);
		BufferedImage bi3 = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi3.getGraphics();
		g.drawImage(bi2, x, y, null);
		s = new Sprite(x,y, bi2, "hp");
		
		ctrl.addSpriteToFrontBuffer(s);
		
		// Show hover label if mouse interacts with RECT object
		/*Point p = Mouse.getMouseCoords();
		int a = (int)p.getX();
		int b = (int)p.getY();
		
		if(r.isCollision(a, b)) 
			s2 = r.getHoverLabel();
		else
			s2 = "";
		ctrl.drawString(a, (b-2), s2, Color.YELLOW);
		ctrl.drawString(a-dropShadow, ((b-dropShadow)-2), s2, Color.BLUE);*/
		
	
		
	}
	
	

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, 128, 128);
	}

}
