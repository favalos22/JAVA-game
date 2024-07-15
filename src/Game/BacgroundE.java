package Game;

import java.awt.Rectangle;

import logic.Control;

public class BacgroundE extends Object{

	public BacgroundE(int x, int y, ObjectID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Control ctrl) {
		ctrl.addSpriteToFrontBuffer(x,y, "l1");
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
