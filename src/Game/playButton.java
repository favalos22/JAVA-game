package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Data.Click;
import Data.Frame;
import Data.RECT;
import Data.Sprite;
import Input.Mouse;
import logic.Control;

public class playButton extends Object{
	
	private Sprite s;
	private RECT r = new RECT(570, 680, 691, 836, "warp2", new Frame(570,680, "warp2"));
	private RECT w = new RECT(570, 680, 691, 836, "warpHole", "NEW DIMENSION! CLICK");
	private String h = "";
	private final int dropShadow = 2;
	private Handle handle;

	public playButton(int x, int y, ObjectID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Control ctrl) {
		//ctrl.addSpriteToFrontBuffer(x, y, "play");
		BufferedImage pImage = ctrl.getSpriteFromBackBuffer("ss").getSprite();
		BufferedImage bi2 = pImage.getSubimage(0, 596, 120, 120);
		BufferedImage bi3 = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
		//BufferedImage bi2 = pImage.getSubimage(306, 360, 60, 60);
		Graphics g = bi3.getGraphics();
		g.drawImage(bi2, x, y, null);
		s = new Sprite(x,y, bi2, "hole");
		
		ctrl.addSpriteToFrontBuffer(s);
		
		Point p = Mouse.getMouseCoords();
		int x = (int)p.getX();
		int y = (int)p.getY();
		if(r.isCollision(x,y))
			ctrl.addSpriteToFrontBuffer(r.getGraphicalHover().getX(), r.getGraphicalHover().getY(), r.getGraphicalHover().getSpriteTag());
		
		if(w.isCollision(x,y))
			h = w.getHoverLabel();
		else	
			h = "";
		ctrl.drawString(x, (y-2), h, Color.BLACK);
		ctrl.drawString(x-dropShadow, ((y-dropShadow)-2), h, Color.RED);
		
		if(Control.getMouseInput() != null) {
	
				if(r.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
					newDimension();
				}
			
		}
		
			
		
	}
	
	public void newDimension() {
		for(int i = 0; i < handle.object.size(); i ++) {
			Object temp = handle.object.get(i);
			
			if(temp.getID() == ObjectID.play) {
				
					handle.object.clear();
					handle.addObject(new BacgroundE(0,0,ObjectID.BackGround));
					handle.addObject(new Player(600, 600, ObjectID.Player, handle));
					handle.addObject(new BasicE(200,200,ObjectID.Alien, 2, 2));
					handle.addObject(new BasicE(346, 654, ObjectID.Alien, 2, 2));
					handle.addObject(new BasicE(200,200,ObjectID.Alien, 3, 3));
					handle.addObject(new BasicE(346, 654, ObjectID.Alien, 3, 3));
					handle.addObject(new BasicE(200,200,ObjectID.Alien, 4, 4));
					handle.addObject(new BasicE(346, 654, ObjectID.Alien, 2, 2));
					handle.addObject(new BasicE(200,200,ObjectID.Alien, 4, 2));
					handle.addObject(new BasicE(346, 654, ObjectID.Alien, 2, 2));
					handle.addObject(new Points(0,0,ObjectID.Points));
					
				
			}
			
		}
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle( x, y, 128, 128);
	}

}
