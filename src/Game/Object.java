package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

import logic.Control;
import Data.RECT;
import Data.Sprite;

public abstract class Object {
	
	protected int x;
	protected int y;
	protected ObjectID id;
	protected int velX;
	protected int velY;
	
	public Object(int x, int y, ObjectID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void update(Control ctrl);
	public abstract Rectangle getBounds();

	
	public void setX(int x) {
		this.x = x;
	
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void sedId(ObjectID id) {
		this.id = id;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public ObjectID getID() {
		return id;
	}
	
	public int getVelX() {
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	
}
