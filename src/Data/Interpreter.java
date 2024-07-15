package Data;
import logic.Control;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import Input.Mouse;
import Main.Main;
import Sound.Sound;

public class Interpreter {
	
	public static String s="";
	public static String s2="";
	private static RECT rs[];
	private final static int dropShadow =2;
	private Main main = new Main();
	private Command a,b,c,d;
	//String tag = "";
	
	public static void showSprite(Control ctrl, ArrayList <Command> commands) {	
		

		for(Command c: commands) {
			if(c.isCommand("show_sprite") && c.getNumParms() == 3) {
				int x = Integer.parseInt(c.getParmByIndex(0));
				int y = Integer.parseInt(c.getParmByIndex(1));
				String tag = c.getParmByIndex(2);
				ctrl.addSpriteToFrontBuffer(x, y, tag);
			}
		}
			
	}
	
	public static void showText(Control ctrl, ArrayList<Command> commands) {
		for(Command c: commands) {
			if(c.isCommand("text") && c.getNumParms() ==3) {
				int x = Integer.parseInt(c.getParmByIndex(0));
				int y = Integer.parseInt(c.getParmByIndex(1));
				String display = c.getParmByIndex(2);
				ctrl.drawString(x, y, display, Color.WHITE);
			}
		}
		
	}
	
	public static void showRECT(Control ctrl, ArrayList<Command> commands) {
		//fields
		rs = new RECT[1];
		rs[0] = new RECT(199, 210, 332, 333, "warp2", "Warp whole is broke, youre stuck in here");
		
		for(Command c: commands) {
			if(c.isCommand("show_RECT") && c.getNumParms() == 6) {
				int x1 = Integer.parseInt(c.getParmByIndex(0));
				int y1 = Integer.parseInt(c.getParmByIndex(1));
				int x2 = Integer.parseInt(c.getParmByIndex(2));
				int y2 = Integer.parseInt(c.getParmByIndex(3));
				String tag = c.getParmByIndex(4);
				String hoverLabel = c.getParmByIndex(5);
				ctrl.addSpriteToFrontBuffer(x1, y1, tag);
				
				if(Control.getMouseInput() != null) {
					for(RECT r: rs) {
						if(r.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
							Main.saveData();
						}
						else {
							s = "";
						}
					}
				}
				ctrl.drawString(200, 200, s, Color.WHITE);
					
				Point p = Mouse.getMouseCoords();
				int a = (int)p.getX();
				int b = (int)p.getY();
				
				if(rs[0].isCollision(a, b)) 
					s2 = rs[0].getHoverLabel();
				else
					s2 = "";
				ctrl.drawString(a, (b-2), s2, Color.BLACK);
				ctrl.drawString(a-dropShadow, ((b-dropShadow)-2), s2, Color.PINK);
					
			}
		}
	}
	
	public static void showBackground(Control ctrl, ArrayList<Command> commands) {

		for(Command c: commands) {
			if (c.isCommand("bakcground") && c.getNumParms() == 3) {
				int x = Integer.parseInt(c.getParmByIndex(0));
				int y = Integer.parseInt(c.getParmByIndex(1));
				String tag = c.getParmByIndex(2);
				ctrl.addSpriteToFrontBuffer(x, y, tag);
			}
		}
	}
	
	public static void playSound(Control ctrl, ArrayList<Command> commands) {
	
		for(Command c: commands) {
			if(c.isCommand("playSound") && c.getNumParms() == 1) {
				String tag = c.getParmByIndex(0);
				Sound song = new Sound(tag);
				song.playWAV();
			}
		}
	}
	
	public static void showAnimation(Control ctrl, ArrayList<Command> commands) {
		for(Command c: commands) {
			
			if(c.isCommand("start_animation") && c.getNumParms() == 5) {
				int x = Integer.parseInt(c.getParmByIndex(0));
				int xpos = Integer.parseInt(c.getParmByIndex(1));
				int ypos = Integer.parseInt(c.getParmByIndex(2));
				int numFrames = Integer.parseInt(c.getParmByIndex(3));
				String tag = c.getParmByIndex(4);
				boolean isLooping = true;
				
				Animation anim = new Animation(x, isLooping);
			
				for(int i = 0; i < numFrames; i++) {
					anim.addFrame(new Frame(xpos, ypos, tag + i));
				}
				
				Frame curFrame = anim.getCurrentFrame();
				if(curFrame != null)
					ctrl.addSpriteToFrontBuffer(curFrame.getX(), curFrame.getY(), curFrame.getSpriteTag());
			}
		}
	}
	
	
}

		
	


