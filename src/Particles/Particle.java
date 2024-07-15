package Particles;




import Data.Frame;
import timer.stopWatchX;

public class Particle {
	
	//Fields
	private int x, y;						//These are the current positions of a given particle
	private String particleSpriteTag;		// Replace with sprite tag instead
	private int lifecycle;					//How many "steps" does the particle take before it dies
	private int age;						//How old the particle is.. counter
	private int xMove, yMove;				//How each particle moves every iteration
	private stopWatchX timer;
	//To preserve for resetting 
	private int rootX, rootY;
	private boolean isReset;
	
	
	//Constructor 
	public Particle(int minX, int maxX, int minY, int maxY, String particleSpriteTag, int minLife, int maxLife, int xMove,
					int yMove, int mindelay, int maxdelay) {
		//Initialize all of the needed data for a single particle
		this.particleSpriteTag = particleSpriteTag;
		this.x = getRandomInt(minX, maxX);
		this.y = getRandomInt(minY, maxY);
		lifecycle = getRandomInt(minLife,maxLife);
		this.xMove = xMove;
		this.yMove = yMove;
		int delay = getRandomInt(mindelay, maxdelay);
		timer = new stopWatchX(delay);
		rootX = x;
		rootY = y;
	}
	
	//Methods
	public boolean hasBeenReset() {
		return isReset;
	}
	
	public void changeX(int newX) {
		x = newX;
	}
	
	public int getX() {
		return x;
	}
	
	public int getLifeCycle() {
		return lifecycle;
	}
	
	public int getAge() {
		return age;
	}
	
	public void changeSprite(String newSpriteTag) {
		particleSpriteTag = newSpriteTag;
	}
	
	public boolean isParticleDead() {
		if(age >= lifecycle)     return true;
		
		if(y > 800 || x > 1279)    return true;
		return false;
	}
	
	// This helps solve the problem of particle "plume" in the beginning by artificially aging them off-screen
	public void simulateAge(){
		age++;
		x += xMove;
		y += yMove;
		if(isParticleDead()) {
			//Reset
			x = rootX;
			y = rootY;
			age = 0;
			isReset = true;
		}
	}
	
	public Frame getCurrentFrame() {
		//Update the particle and return results
		if(timer.isTimeUp()) {
			age++;
			x += xMove;
			y += yMove;
			if(isParticleDead()) {
				x = rootX;
				y = rootY;
				age = 0;
				isReset = true;
			}
			timer.resetWatch();
		}
		return new Frame(x, y, particleSpriteTag);
	}
	
	public static int getRandomInt(int first, int last) {
		int diff = last - first;
		double num = Math.random() * diff;
		int intNum = (int) num;
		return first + intNum;
	}
	
	public static int rollDie(int dieSides) {
		double result = Math.random() * dieSides;
		int res = (int)result;
		res ++;
		return res;
		
	}
}
