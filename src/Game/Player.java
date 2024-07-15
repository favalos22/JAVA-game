package Game;




import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import logic.Control;
import Data.Sprite;
import FileIO.EZFileRead;
import Data.AText;
//import Main.Main.Screen;
import Data.Animation;
import Data.Click;
import Data.Frame;
import Data.RECT;
import Main.Main;
import Sound.Sound;
import Game.BacgroundE;
import Data.Interpreter;
import Data.Command;

public class Player extends Object{
	
	Handle handle;
	public Sprite s;
	public int hdrop = 2;
	public RECT r;
	private Animation anim = new Animation(120,true);
	//private Control ctrl = new Control();
	private Sound sfx = new Sound("Sound/warpSound.wav");
	private RECT w = new RECT(570, 680, 691, 836, "warpHole", "NEW DIMENSION! CLICK");
	private RECT b = new RECT(600, 400, 721, 556, "warpHole2", "NEW DIMENSION! CLICK");
	private ArrayList <Command> commands;


	
	
	public Player(int x, int y, ObjectID id, Handle handle) {
		super(x, y, id);
		this.handle = handle;
		//velX = 1;
	}
	
	
	
	public void tick() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int mouseX = (int) b.getX();
		int mouseY = (int) b.getY();
		
		y = y + velY;
		x = x + velX;
		
		velX = (mouseX -x)/10;
		velY = (mouseY - y)/10;
		
		if (x < 0  || x > 1280) {
			velX = -velX;
		}
		
		if (y < 0 || y > 800) {
			velY = -velY;
		}
		
		
		
	}
	
	public void update(Control ctrl) {
		//Frame curFrame = anim.getCurrentFrame();
		BufferedImage pImage = ctrl.getSpriteFromBackBuffer("ss").getSprite();
		BufferedImage bi2 = pImage.getSubimage(356, 596, 115, 120);
		BufferedImage bi3 = new BufferedImage(1280, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi3.getGraphics();
		g.drawImage(bi2, x, y, null);
		s = new Sprite(x,y, bi2, "player");
		
		ctrl.addSpriteToFrontBuffer(s);
		
		
		if(Control.getMouseInput() != null) {
			
			if(w.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
				newDimension();
				sfx.playWAV();
			}
		
		}
		
		if(Control.getMouseInput() != null) {
			if(b.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
				//Interpreter.showSprite(ctrl, commands);
				newDimension2();
				sfx.playWAV();
				
			}
		}
	
		collision();
		collisionF();
		collisionR();
		collisionGas();
		collisionMoney();
		
		
		
		
	}
	
	//Collision method for when player touches enemies, decrease health
	public void collision() {
		for(int i = 0; i < handle.object.size(); i ++) {
			Object temp = handle.object.get(i);
			
			if(temp.getID() == ObjectID.Alien || temp.getID() == ObjectID.Boss) {
				if(getBounds().intersects(temp.getBounds())) {
					HUD.health -=1;
				
				}
			}
		}
	}
	
	//Method for when player collides with GAS 
	public void collisionGas() {
		for(int i =0; i < handle.object.size(); i++){
			Object temp = handle.object.get(i);
			
			if(temp.getID() == ObjectID.Gas) {
				if(getBounds().intersects(temp.getBounds())) {
					InventoryHUD.gas = InventoryHUD.gas +1; 
					handle.removeObject(temp);
				}
			}
		}
	}
	
	//Method for when player collides with MONEY
	public void collisionMoney() {
		for(int i =0; i < handle.object.size(); i++){
			Object temp = handle.object.get(i);
			
			if(temp.getID() == ObjectID.Money) {
				if(getBounds().intersects(temp.getBounds())) {
					InventoryHUD.money = InventoryHUD.money +1; 
					handle.removeObject(temp);
				}
			}
		}
	}
	
	
	
	//Collision method for when player collides with Rockets
	public void collisionR() {
		for(int i = 0; i < handle.object.size(); i ++) {
			Object temp = handle.object.get(i);
			
			if(temp.getID() == ObjectID.rocket) {
				if(getBounds().intersects(temp.getBounds())) {
					HUD.health -=1;
				
				}
			}
		}
	}
	
	public void newDimension() {
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
	
	public void newDimension2() {
		//Interpreter.showBackground(commands);
		handle.object.clear();
		//Interpreter.showBackground(commands);
		handle.addObject(new BackgroundM(0,0,ObjectID.BackGround));
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
	
	public void collisionSound() {
		for(int i = 0; i < handle.object.size(); i ++) {
			Object temp = handle.object.get(i);
			
			if(temp.getID() == ObjectID.Alien) {
				if(getBounds().intersects(temp.getBounds())) {
					sfx.playWAV();
				
				}
			}
		}
	}
	
	public void collisionB() {
		for(int i = 0; i < handle.object.size(); i ++) {
			Object temp = handle.object.get(i);
			
			if(temp.getID() == ObjectID.play) {
				if(getBounds().intersects(temp.getBounds())) {
					//Object temp2 = handle.object.getFirst();
					//handle.removeObject(temp2);
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

	}
	
	public void collisionD() {
		for(int i =0; i < handle.object.size(); i++) {
			Object temp = handle.object.get(i);
			
			if(temp.getID() == ObjectID.play2) {
				if(getBounds().intersects(temp.getBounds())) {
					handle.object.clear();
					handle.addObject(new BackgroundM(0,0, ObjectID.BackGround));
					handle.addObject(new Player(600, 600, ObjectID.Player, handle));
					handle.addObject(new BasicE(200,200,ObjectID.Alien, 2, 2));
					handle.addObject(new BasicE(346, 654, ObjectID.Alien, 2, 2));
					handle.addObject(new Points(0, 0, ObjectID.Points));
				}
			}
		}
	}
	//Method for collision with Friend, If player touches friend award +100 to health points
	public void collisionF() {
		for(int i = 0; i < handle.object.size(); i ++) {
			Object temp = handle.object.get(i);
			
			if(temp.getID() == ObjectID.Friend) {
				if(getBounds().intersects(temp.getBounds())) {
					HUD.health +=100;
					handle.removeObject(temp);
				}
			}
		}
	}
	
	// Create a rectangle around the player to know where collision bounds are
	public Rectangle getBounds() {
		return new Rectangle(x,y, 128,128);
	}
	
	public  int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}


	
}
