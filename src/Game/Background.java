package Game;

import java.awt.Rectangle;
import java.util.ArrayList;

import Data.Command;
import Data.Interpreter;
import logic.Control;

public class Background extends Object {
	
	private ArrayList<Command> commands;
	
	public Background(int x, int y, ObjectID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Control ctrl) {
		ctrl.addSpriteToFrontBuffer(x,y ,"Earth");
		//Interpreter.showBackground(ctrl, commands);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
