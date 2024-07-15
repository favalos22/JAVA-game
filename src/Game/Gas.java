package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Data.Sprite;
import logic.Control;

public class Gas extends Object {
	
	private Sprite s;
	
	public Gas(int x, int y, ObjectID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Control ctrl) {
		BufferedImage pImage = ctrl.getSpriteFromBackBuffer("ss").getSprite();
		BufferedImage bi2 = pImage.getSubimage(0, 482, 60, 60);
		BufferedImage bi3 = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
		//BufferedImage bi2 = pImage.getSubimage(306, 360, 60, 60);
		Graphics g = bi3.getGraphics();
		g.drawImage(bi2, x, y, null);
		s = new Sprite(x,y, bi2, "gas");
		
		ctrl.addSpriteToFrontBuffer(s);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 64, 64);
	}

}
