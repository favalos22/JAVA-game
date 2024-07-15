package Game;

import java.awt.Color;
import java.awt.Rectangle;

import logic.Control;

public class InventoryHUD{
	
	private Player p;
	public static int gas;
	public static int money;


	public void update(Control ctrl) {
		ctrl.addSpriteToHudBuffer(995, 665, "inv");
		ctrl.drawHudString(1017, 735, "GAS: " + gas, Color.YELLOW);
		ctrl.drawHudString(1017, 755, "MONEY BAGS: " + money, Color.YELLOW);
		
		
	}

	
	public Rectangle getBounds() {
		return null;
	}
	
	
	public int getMoney() {
		return money;
	}
	
	public int getGas() {
		return gas;
	}
	
	public void setGas(int gas) {
		this.gas = gas;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}

}
