package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Data.Sprite;
import logic.Control;

public class Rocket extends Object{
	//Fields
	private Handle handle;
	private Sprite s;

	public Rocket(int x, int y, ObjectID id, Handle handle) {
		super(x, y, id);
		this.handle = handle;
		
		velX = 4;
		velY = 4;
		
		
	}

	@Override
	public void tick() {
		
		x += velX;  //set horizontal velocity/movement
		y += velY;	//set vertical velocity/movement
		
		if(y <= 0 || y >= 768) velY *=-1;
		if(x <= 0 || x >= 1248) velX *=-1;
		
	}

	@Override
	public void update(Control ctrl) {
		BufferedImage pImage = ctrl.getSpriteFromBackBuffer("ss").getSprite();
		BufferedImage bi2 = pImage.getSubimage(306, 114, 16, 16);
		BufferedImage bi3 = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
		//BufferedImage bi2 = pImage.getSubimage(306, 360, 60, 60);
		Graphics g = bi3.getGraphics();
		g.drawImage(bi2, x, y, null);
		s = new Sprite(x,y, bi2, "rocket");
		
		ctrl.addSpriteToFrontBuffer(s);
		
		
		
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 16, 16);
	}

}
