package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

	
	private Handler handler;
		
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 6;
		velY = 6;
		//velocity x and y for basic enemy
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle (x, y, 35, 45);
		//dimensions of basic enemy
	}
	
	
	public void tick() {
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 79) {
			
			velY *= -1;
		}
			
		if (x <= 0 || x >= Game.WIDTH - 46) {
				
			velX *= -1;	
		}//makes the enemy go the opposite direction when hitting screen border
	
		handler.addObject(new Trail (x, y, ID.Trail, new Color(194, 58, 34), 35, 45, 0.19f, handler));
		//creating trail for the enemy 
	}

	public void render(Graphics g) {
		g.setColor(new Color(194, 58, 34));
		g.fillRect(x, y, 35, 45);
	}//filling the enemy as red

}
