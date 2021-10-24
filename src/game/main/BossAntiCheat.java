package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossAntiCheat extends GameObject{

	
	private Handler handler;
	
	private int Timer = 160;
	//timer that determines how long the object will move
	
	public BossAntiCheat(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 1;
	}//y velocity of 1, x 0

	
	public Rectangle getBounds() {
		
		return new Rectangle (x, y, 640, 100);
		//width of the game window
	}
	
	
	public void tick() {
		x += velX;
		y += velY;
		
		
		if(Timer <= 0) {
			velY = 0;
		}
		
		else {
			Timer--;
		}
	
		//every tick, if the timer is not 0, the timer will decrease. the velocity will then be set to 0 to stop it from moving
		//this creates an anti cheat so the player cannot hide above the boss
		
	}

	public void render(Graphics g) {
		g.setColor(new Color(40, 40, 38));
		g.fillRect(x, y, 640, 100);
	}
	//fills anti cheat with color of the background
}
