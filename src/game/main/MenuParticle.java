package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{

	
	private Handler handler;
	private Color col;

	Random r = new Random();
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(9 - -9) + -9);
		velY = (r.nextInt(9 - -9) + -9);
		//velocities between 9 and -9
		
		if (velX == 0) {
			velX = 1;
		}
		
		if (velY == 0) {
			velY = 1;
		}
		//velocities set to 1 if they are randomly selected as 0
		
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		//random colors are applied
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle (x, y, 18, 18);
		
	}
	
	
	public void tick() {
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 59) {
			
			velY *= -1;
		}
			
		if (x <= 0 || x >= Game.WIDTH - 41) {
				
			velX *= -1;	
		}//bounce off borders
	
		handler.addObject(new Trail (x, y, ID.Trail, col, 18, 18, 0.06f, handler));
		
	}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect(x, y, 18, 18);
	}
		//filling each particle with a random color
}
