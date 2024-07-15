package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Data.Animation;
import Data.Frame;
import Data.RECT;
import Data.Sprite;
import Input.Mouse;
import logic.Control;

public class Points extends Object{
	
	private Animation anim = new Animation(120,true);
	private RECT r;
	private String s2 ="";
	private final int dropShadow =2;
	private Sprite a,b,c;
	
	public Points(int x, int y, ObjectID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Control ctrl) {
		//BufferedImage pImage = ctrl.getSpriteFromBackBuffer("z0").getSprite();
		BufferedImage pImage = ctrl.getSpriteFromBackBuffer("ss").getSprite();
		BufferedImage bi2 = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
		BufferedImage bi3 = pImage.getSubimage(342, 482, 64, 64);
		BufferedImage bi4 = pImage.getSubimage(114, 482, 64, 64);
		BufferedImage bi5 = pImage.getSubimage(308, 180, 64, 64);
		Graphics g = bi2.getGraphics();
		g.drawImage(pImage, x, y,null);
		a = new Sprite(x, y,bi3, "z0");
		b = new Sprite(x, y, bi4, "z1");
		c = new Sprite(x,y, bi5, "z2");
		
		for(int i =0; i <3; i++) {
			anim.addFrame(new Frame(x,y, "z" +i));
		}
		
		Frame curFrame = anim.getCurrentFrame();
		if(curFrame != null)
			ctrl.addSpriteToFrontBuffer(curFrame.getX(), curFrame.getY(), curFrame.getSpriteTag());
		
		r = new RECT(0, 0, 64, 64,"z0", "PEACE OUT!");
		Point p = Mouse.getMouseCoords();
		int a = (int)p.getX();
		int b = (int)p.getY();
		
		if(r.isCollision(a, b)) 
			s2 = r.getHoverLabel();
		else
			s2 = "";
		ctrl.drawString(a, (b-2), s2, Color.BLACK);
		ctrl.drawString(a-dropShadow, ((b-dropShadow)-2), s2, Color.YELLOW);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y, 64,64);
	}
	
	//Getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
