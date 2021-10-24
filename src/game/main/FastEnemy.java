package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{

	
	private Handler handler;
		
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 3;
		velY = 9;
	}//fast enemy is the same as basic enemy but with different velY

	
	public Rectangle getBounds() {
		
		return new Rectangle (x, y, 25, 20);
		//size is slightly altered from basic enemy
	}
	
	
	public void tick() {
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 59) {
			
			velY *= -1;
		}
			
		if (x <= 0 || x >= Game.WIDTH - 41) {
				
			velX *= -1;	
		}
	
		handler.addObject(new Trail (x, y, ID.Trail, new Color(0, 186, 186), 25, 20, 0.02f, handler));
		//different color
	}

	public void render(Graphics g) {
		g.setColor(new Color(0, 186, 186));
		g.fillRect(x, y, 25, 20);
	}

}
