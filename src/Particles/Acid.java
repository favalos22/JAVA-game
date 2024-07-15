package Particles;

public class Acid {
	//Fields
	private ParticleSystem parts;
	private String[] spriteTags;
	
	//Constructor
	public Acid(int xpos, int ypos, int xrange, int yrange, int minlife, int maxlife, int numparticles) {
		spriteTags = new String[4];
		spriteTags[0] = "acid1";
		spriteTags[1] = "acid2";
		spriteTags[2] = "acid3";
		spriteTags[3] = "acid4";
		

		int xspeed = 1;
		int yspeed = 20;
		parts = new ParticleSystem(numparticles, xpos, ypos, xrange, yrange, minlife, maxlife, xspeed, yspeed, 60, 70, spriteTags);
	}
	
	public Acid(int xpos, int ypos, int xrange, int yrange, int minlife, int maxlife, int numparticles, int dieSides) {
		spriteTags = new String[4];
		for(int i =0; i< 4; i++) {
			spriteTags[i] = String.format("acid%d", (i+1));
		}

		int xspeed = 3;
		int yspeed = 20;
		parts = new ParticleSystem(numparticles, xpos, ypos, xrange, yrange, minlife, maxlife, xspeed, yspeed, 60, 70, spriteTags, dieSides);
	}
	
	
	//Methods 
	private void updateParticleSprites() {
		Particle [] pa = parts.getParticlesArray();
		for(int i= 0; i < pa.length; i++) {
			int stages = spriteTags.length;
			int life = pa[i].getLifeCycle();
			int range = life / stages;
			int age = pa[i].getAge();
			for(int j = 0; j < stages; j++) {
				if (age >= (range *j) && age < (range*(j+1))) {
					pa[i].changeSprite(spriteTags[j]);
					break;
				}
			}
		}
	}
	
	public ParticleSystem getParticleSystem() {
		updateParticleSprites();
		return parts;
	}
}