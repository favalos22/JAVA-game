package Game;


import java.awt.Graphics;
import java.util.LinkedList;

import logic.Control;

public class Handle {
	
	public LinkedList<Object> object = new LinkedList<Object>();
	
	public void tick() {
		for (int i =0; i < object.size(); i++) {
			
			Object temp = object.get(i);
			temp.tick();
		}
	}
	
	public void update(Control ctrl) {
		for(int i =0; i < object.size(); i++) {
			Object temp = object.get(i);
			
			temp.update(ctrl);
		}
	}
	
	public void addObject(Object object) {
		this.object.add(object);
	}
	
	public void removeObject(Object object) {
		this.object.remove(object);
		
	}
}
