package Game;

import java.awt.Color;
import java.awt.Graphics;

import logic.Control;

public class HUD {
	
	public static int health = 3000; //Health of the spaceship
	public static int score = 0; //Start score at 0
	private int level = 1; // Start level at 0
	
	public void tick() {
		score ++; // Increase score as time goes by and player is alive
	}
	
	public void update(Control ctrl) {
		//HUD display set up
		ctrl.addSpriteToHudBuffer(0, 630, "spacehud"); // HUD iamge
		ctrl.drawHudString(20, 700, "HEALTH: " + health,Color.GREEN); // Health
		ctrl.drawHudString(20, 730, "SCORE: " + score, Color.BLUE); //Score
		ctrl.drawHudString(20, 760, "LEVEL: " + level, Color.YELLOW); // Level
		
	}
	
	//Setters and Getters
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
}
