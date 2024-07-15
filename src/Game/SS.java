package Game;

import java.util.Random;

import logic.Control;

public class SS {
	
	private Handle handle;
	private HUD hud;
	private int width = 1200;// width of the screen
	private int height = 600; //height of the screen 
	private int Stracker =0; //Variable to keep track of the score
	private Random random = new Random(); //Get random position for enemies
	
	//Constructor
	public SS(Handle handle, HUD hud) {
		this.handle = handle;
		this.hud = hud;
	}
	
	public void tick() {
		Stracker ++;// Keep track of score
		
		//Level increases every time score hits 2000
		if(Stracker >= 2000) {
			Stracker = 0;
			hud.setLevel(hud.getLevel() +1);
		}

		//Add enemies when level 2 is reached
		if(hud.getScore() ==2000) {
			handle.addObject(new BasicE(random.nextInt(width), random.nextInt(height), ObjectID.Alien, 1, 1));
			handle.addObject(new BasicE(random.nextInt(width), random.nextInt(height), ObjectID.Alien, 1, 1));
			handle.addObject(new BasicE(random.nextInt(width), random.nextInt(height), ObjectID.Alien, 1, 1));
			handle.addObject(new Gas(random.nextInt(width), random.nextInt(height), ObjectID.Gas));
			handle.addObject(new Money(random.nextInt(width), random.nextInt(height), ObjectID.Money));
		
		}
		
		//Add enemies when level 3 is reached going at a faster speed
		if(hud.getScore() == 4000) {
			handle.addObject(new BasicE(random.nextInt(width), random.nextInt(height), ObjectID.Alien, 3, 3));
			handle.addObject(new BasicE(random.nextInt(width), random.nextInt(height), ObjectID.Alien, 3, 3));
			handle.addObject(new Friend(300, 300, ObjectID.Friend));
			handle.addObject(new Gas(random.nextInt(width), random.nextInt(height), ObjectID.Gas));
			handle.addObject(new Money(random.nextInt(width), random.nextInt(height), ObjectID.Money));
		}
		
		//Boss Appears 
		if(hud.getScore() == 5000) {
			handle.addObject(new Boss(100, 0, ObjectID.Boss, handle));
			handle.addObject(new Gas(random.nextInt(width), random.nextInt(height), ObjectID.Gas));
			handle.addObject(new Money(random.nextInt(width), random.nextInt(height), ObjectID.Money));
		}
		
		if(hud.getScore() == 6000) {
			for(int i = 0; i < 20; i++) {
				handle.addObject(new Rocket(random.nextInt(width), random.nextInt(height), ObjectID.rocket, handle));
			}
			handle.addObject(new Gas(random.nextInt(width), random.nextInt(600), ObjectID.Gas));
			handle.addObject(new Money(random.nextInt(width), random.nextInt(600), ObjectID.Money));
		}
		
		if(hud.getScore() == 9000) {
			for(int i = 0; i < 4; i++) {
				handle.addObject(new BasicE(random.nextInt(width), random.nextInt(height), ObjectID.Alien, 3, 3));
		
			}
			
				handle.addObject(new Gas(random.nextInt(width), random.nextInt(600), ObjectID.Gas));
				handle.addObject(new Money(random.nextInt(width), random.nextInt(600), ObjectID.Money));
		}
		
		if(hud.getScore() == 11000) {
			for(int i = 0; i < 4; i++) {
				handle.addObject(new BasicE(random.nextInt(width), random.nextInt(height), ObjectID.Alien, 3, 3));
				handle.addObject(new Friend(random.nextInt(width), random.nextInt(height), ObjectID.Friend));
				handle.addObject(new Impostor(random.nextInt(width), random.nextInt(height), ObjectID.Impostor));
				
			}
			handle.addObject(new Gas(random.nextInt(width), random.nextInt(600), ObjectID.Gas));
			handle.addObject(new Money(random.nextInt(width), random.nextInt(600), ObjectID.Money));
		}
		
		if(hud.getScore() == 14000) {
			for(int i = 0; i <10; i++) {
				handle.addObject(new BasicE(random.nextInt(width), random.nextInt(height), ObjectID.Alien, 3, 3));
				
			}
			
			handle.addObject(new Boss(100 , 0, ObjectID.Boss, handle));
			handle.addObject(new Gas(random.nextInt(width), random.nextInt(height), ObjectID.Gas));
			handle.addObject(new Money(random.nextInt(width), random.nextInt(height), ObjectID.Money));
			for(int i = 0; i <3; i++) {
				handle.addObject(new Friend(random.nextInt(width), random.nextInt(height), ObjectID.Friend));
			}
		}
		
		if(hud.getScore() ==15000) {
			for (int i = 0; i <15; i++) {
				handle.addObject(new Rocket(random.nextInt(width), random.nextInt(height), ObjectID.rocket, handle));
				handle.addObject(new Gas(random.nextInt(width), random.nextInt(height), ObjectID.Gas));
				handle.addObject(new Money(random.nextInt(width), random.nextInt(height), ObjectID.Money));
			}
		}
		
		if(hud.getScore() == 18000) {
			for(int i = 0; i < 30; i++) {
				handle.addObject(new BasicE(random.nextInt(width), random.nextInt(height), ObjectID.Alien, 3, 3));
			}
		}
		
		if(hud.getScore() == 8000) {
			handle.addObject(new playButton(570,680, ObjectID.play));
		}
		
		if(hud.getScore() == 16000){
			handle.addObject(new playButton(600,400, ObjectID.play2));
		}
		
		
	
		
		
		
	}
	
	public void update(Control ctrl) {
	
	}
}
