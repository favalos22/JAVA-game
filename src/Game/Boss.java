package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Data.Sprite;
import logic.Control;

public class Boss extends Object {
	//Fields
	private Handle handle;
	private Sprite s;
	private Random r;
	
	public Boss(int x, int y, ObjectID id, Handle handle) {
		super(x, y, id);
		this.handle = handle;
		
		velX = 0;
		velY = 2;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <=0 || y >= 612) velY *= -1;
		if(x <=0 || x >= 1280 - 128) velX *= -1;
		
		//int s = r.nextInt(10);
		//if(s ==0) handle.addObject(new Rocket((int)x, (int) y, ObjectID.rocket, handle));
		
	}

	@Override
	public void update(Control ctrl) {
		BufferedImage pImage = ctrl.getSpriteFromBackBuffer("ss").getSprite();
		BufferedImage bi2 = pImage.getSubimage(420, 0, 120, 120);
		BufferedImage bi3 = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
		//BufferedImage bi2 = pImage.getSubimage(306, 360, 60, 60);
		Graphics g = bi3.getGraphics();
		g.drawImage(bi2, x, y, null);
		s = new Sprite(x,y, bi2, "boss");
		
		ctrl.addSpriteToFrontBuffer(s);
		
		
		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, 128, 128);
	}

}
