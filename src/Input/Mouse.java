package Input;




import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import Data.Click;
import Game.BacgroundE;
import Game.BasicE;
import Game.Friend;
import Game.HUD;
import Game.Handle;
import Game.Object;
import Game.ObjectID;
import Game.Player;
import Game.Points;






public class Mouse extends MouseAdapter implements MouseListener, MouseMotionListener{
	//Fields
	private boolean isReady;
	private Click lastClick;
	public static int x =0;
	public static int y = 0;
	Point clicked = new Point(0,0);
	Point moved = new Point(0,0);
	private Points points;
	private Handle handle;
	private Friend f;
	
	
	
	//Constructor
	public Mouse() {
		isReady = false;
		lastClick = null;
		
		
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(mIntersects(mouseX, mouseY, 0 , 0, 64, 64)) {
			System.exit(0);
		}
		
		
	
		
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		lastClick = new Click(arg0.getX(), arg0.getY(), arg0.getButton());
		isReady = true;
	}
	//Methods
	public Click pollClick() {
		if(!isReady)			return null;
		isReady = false;
		return lastClick;
	}
	
	public boolean isReady() {
		return isReady;
	}
	
	public static Point getMouseCoords() {
		return MouseInfo.getPointerInfo().getLocation();
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		moved = e.getPoint();
		x = e.getX();
		y = e.getY();
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	private boolean mIntersects(int mouseX, int mouseY, int x, int y, int width, int height) {
		if(mouseX > x && mouseX < x + width) {
			if(mouseY > y && mouseY < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	private Rectangle getBounds() {
		return new Rectangle(x,y,64, 64);
	}
	

}
