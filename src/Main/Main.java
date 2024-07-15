package Main;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;

import Data.Sprite;
import FileIO.EZFileRead;
import FileIO.EZFileWrite;
import Data.Frame;
import Data.Interpreter;
import Data.RECT;
import Data.AText;
import Data.Animation;
import Data.Click;
import Data.Command;
import logic.Control;
import timer.stopWatchX;
import Input.Mouse;

import Particles.ParticleSystem;
import Particles.Rain;
import Particles.Acid;
import Game.Player;
import Graphics.Graphic;
import Input.Keyb;
import Game.Handle;
import Game.ObjectID;
import Game.BasicE;
import Game.Friend;
import Game.HUD;
import Game.SS;
import Game.Impostor;
import Sound.Sound;
import Game.playButton;
import Game.Object;
import Game.Background;
import Game.Points;
import Game.InventoryHUD;



public class Main{
	// Fields (Static) below...
	public static int width = 1280;
	public static int height  = 800;
	public static Rain rain;
	public static Acid acid;
	public static Graphic g;
	public static Keyb k;
	public static String s = "";
	public static Handle handle;
	public static Random r;
	public static HUD hud;
	public static InventoryHUD ihud;
	public static BasicE e;
	public static SS spawn;
	public static AText atext, atext2, atext3, atext4, atext5, atext6, atext7;
	public static Mouse m;
	public static Sound song = new Sound("Sound/SpaceSong.wav");
	public static Sound sfx = new Sound("Sound/funny_death.wav");
	public static Object object;
	public static Animation anim;
	public static String s2 = "";
	public static String s3 ="";
	public static String s4 ="";
	private static int[] buffer;
	private static RECT disk;
	private static RECT load;
	private static final int dropShadow =2;
	private static ArrayList<Command> commands;
	public static Sprite f, w, h;
	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
	
		
		//m = new Mouse();
		handle = new Handle();									//handle all the objects
		hud = new HUD();										//Display the HUD display
		ihud = new InventoryHUD();
		spawn = new SS(handle, hud);							//spawn objects and enemies
		acid = new Acid(-50, 8, 1200, 90, 35, 40, 60);			//particle system 
		//handle = new Handle();
		ctrl.hideDefaultCursor();
		atext = new AText("Be Careful, Boss is Incoming...", 30);							//animated text
		atext2 = new AText("Small Diamond Spaceship is just a Hologram, NO DAMAGE", 40); 	// animated text
		atext3 = new AText("Boss calling in rocket strike.... WATCH OUT!", 50);				// animated text
		atext4 = new AText("Warp hole is about to appear... Discover a new Dimension", 60); //animated text
		atext5 = new AText("Ships that look like yours will give you 100 hp if picked up", 60); //animated Text
		atext6 = new AText("WARNING NO WAY BACK!! YOU WILL DIE HERE!!", 60);
		atext7 = new AText("New Dimension will Appear at 16000 points", 60);
		song.setLoop();  //Start song
		handle.addObject(new Background(0,0, ObjectID.BackGround));	
		handle.addObject(new Points(0,0, ObjectID.Points));
		handle.addObject(new Player(0, 0, ObjectID.Player,handle));
		handle.addObject(new BasicE(200, 200, ObjectID.Alien, 1, 1));
		handle.addObject(new Impostor(327, 420, ObjectID.Impostor));
		
		EZFileRead ezr = new EZFileRead("script.txt");
		commands = new ArrayList<>();
		for(int i = 0; i < ezr.getNumLines(); i++) {
			String raw = ezr.getLine(i);
			raw = raw.trim();
			if(!raw.equals("")) {
				boolean b = raw.charAt(0) == '#';
				if(!b)		commands.add(new Command(raw));
			}
		
		}

		
		//Clickable Rect for save icon
		disk = new RECT(0, 80, 61, 140, "savetag", "Save Game");
		buffer = new int [600];
		for(int i = 0; i < buffer.length; i++) {
			int value = (int)(Math.random() *100);
			buffer[i] = value;
		}
	
		load = new RECT(0, 150, 59, 223, "loadtag", "Load Game");
		
		//Sub image of save and load icon from sprite sheet
		BufferedImage pImage = ctrl.getSpriteFromBackBuffer("ss").getSprite();
		BufferedImage bi2 = pImage.getSubimage(306, 0, 64, 64);
		BufferedImage bi3 = pImage.getSubimage(228, 482, 64, 64);
		f = new Sprite(0, 80,bi2, "save");
		w = new Sprite(0,160, bi3,"load");
		
	}


	

	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		handle.update(ctrl);   //start update handle
		hud.update(ctrl);		// update the hud
		hud.tick();				// update hud score, level , health
		ihud.update(ctrl); 		//inventory hud
		handle.tick();			//update sprite movement
		spawn.update(ctrl);		//spawn enemies
		spawn.tick();			//number of enemies spawned

		
		ParticleSystem burn = acid.getParticleSystem();
		Iterator <Frame> burn2 = burn.getParticles();
		while(burn2.hasNext()) {
			Data.Frame burn3 = burn2.next();
			ctrl.addSpriteToFrontBuffer(burn3.getX(),burn3.getY(), burn3.getSpriteTag());

		}
		
		//Custom cursor
		Point p = Mouse.getMouseCoords();
		ctrl.addSpriteToOverlayBuffer(p.x, p.y, "cursor");
		
		//Add Game over message if player helath has reached 0
		if(HUD.health <= 0) {
			handle.object.clear();
			ctrl.addSpriteToHudBuffer(540, 400, "spacehud");
			ctrl.drawHudString(560, 480, "GAME OVER!", Color.RED);
			ctrl.drawHudString(560, 510, "YOU HAVE LOST...", null);
			hud.setLevel(0000000);
			hud.setScore(000000);
			handle.addObject(new Points(0,0, ObjectID.Points));
		}
		
		//start the animated Text method
		animText(ctrl);
		
		//Save icon and hover + click message
		ctrl.addSpriteToFrontBuffer(f);
		//hover...
		Point e = Mouse.getMouseCoords();
		int x = (int) e.getX();
		int y = (int) e.getY();
		if(disk.isCollision(x, y))
			s = disk.getHoverLabel();
		else
			s = "";
		ctrl.drawString(x, (y-2), s, Color.black);
		ctrl.drawString(x-dropShadow,(y-dropShadow)-2, s, Color.CYAN);
		//Mouse polling
		if(Control.getMouseInput() != null) {
			if(disk.isClicked(Control.getMouseInput(),Click.LEFT_BUTTON)) {
				saveData();
				s2 = "GAME SAVED";
			}
		}
		ctrl.drawString(87, 14, s2, Color.WHITE);
		
		//Load icon and hover + click message
		ctrl.addSpriteToFrontBuffer(w);
		//hover...
		Point t = Mouse.getMouseCoords();
		int x2 = (int) t.getX();
		int y2 = (int) t.getY();
		if(load.isCollision(x2, y2))
			s3 = load.getHoverLabel();
		else
			s3 = "";
		ctrl.drawString(x2, (y2-2), s3, Color.black);
		ctrl.drawString(x2-dropShadow,(y2-dropShadow)-2, s3, Color.CYAN);
		//Mouse Polling
		if(Control.getMouseInput() != null) {
			if(load.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
				loadData();
				s4 = "Game Loaded";
			}
		}
		ctrl.drawString(87, 12, s4, Color.WHITE);
		
	
		
		
		
	}
	
	public static void saveData() {
		//Save data to a String output...
		String out ="";
		for(int i =0; i <buffer.length; i++) {
			out += buffer[i] + "*";
			out = out.substring(0, out.length()-1);//remove trailing delimiter
			//Save output String to file
			EZFileWrite ezw = new EZFileWrite("save.txt");
			ezw.writeLine(out);
			ezw.saveFile();
		}
	}
	
	public static void loadData() {
		//Retrieve data from the file
		EZFileRead ezr = new EZFileRead("save.txt");
		String  raw = ezr.getLine(0); //Read our one and only line
		//Break this down into tokens
		StringTokenizer st = new StringTokenizer(raw, "*");
		if(st.countTokens() != buffer.length)
			return;			//These must match!
		for(int i = 0; i < buffer.length; i++) {
			String value = st.nextToken();
			int val = Integer.parseInt(value);
			buffer[i] = val;
			
		}
		
	}
	
	//Animated text displays during certain parts of the game
	public static void animText(Control ctrl) {
		
		//Display  animated text if scores is between 0 and 1100
		if(HUD.score >= 0 && HUD.score <= 2000) {
			String s = atext2.getCurrentStr();
			ctrl.drawString(430, 100, s, Color.green);
			//Interpreter.showAnimation(ctrl, commands);
		}
		
		//Display the function of ships that look like yours
		if(HUD.score >= 0 && HUD.score <= 2000) {
			String s = atext5.getCurrentStr();
			ctrl.drawString(430, 200, s, Color.cyan);
		}
		
		
		//Display animated text if score is between 5000 and 5500
		if(HUD.score >= 4000 && HUD.score <= 4500) {
			String s = atext.getCurrentStr();
			ctrl.drawString(430, 100, s, Color.green);
		}
		
		//Display text for boss Rocket Strike
		if(HUD.score >= 5500 && HUD.score <= 6200) {
			String s = atext3.getCurrentStr();
			ctrl.drawHudString(430, 100, s, Color.RED);
		}
		
		//Display animated text for when warp hole is about to appear
		if(HUD.score >= 7000 && HUD.score <= 7900) {
			String s = atext4.getCurrentStr();
			ctrl.drawHudString(350, 100, s, Color.ORANGE);
		}
		
		if(HUD.score >= 8500 && HUD.score <= 9100) {
			String s = atext7.getCurrentStr();
			ctrl.drawHudString(430, 200, s, Color.RED);
		}
		//Display animated text for new incoming portal at 20000
		if(HUD.score >= 15500 && HUD.score <= 15900) {
			String s = atext4.getCurrentStr();
			ctrl.drawHudString(430, 200, s, Color.RED);
		}
		
		//Display animated text for the last scene
		if(HUD.score >= 17000 && HUD.score <= 25000) {
			String s = atext6.getCurrentStr();
			ctrl.drawHudString(430, 100, s, Color.RED);
			Interpreter.showSprite(ctrl, commands);
			Interpreter.showRECT(ctrl, commands);
		}
		
		
		
	}
	
	

	
	
	
}
