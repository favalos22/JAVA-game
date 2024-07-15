package Game;

import java.awt.Color;
import java.awt.Rectangle;

import logic.Control;

public class GameOver extends Object{

	private Handle handle;
	
	public GameOver(int x, int y, ObjectID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Control ctrl) {
		ctrl.addSpriteToHudBuffer(x, y, "spacehud"); 
		ctrl.drawHudString(660, 430, "GAME OVER! YOU HAVE LOST... ",Color.RED); 
		//handle.addObject(new Points(680, 460, ObjectID.Points));
	
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
