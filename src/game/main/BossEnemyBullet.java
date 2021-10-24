package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemyBullet extends GameObject{

	
	private Handler handler;
	Random r = new Random();
		
	
	public BossEnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(5 - -5) + -5);//x velocity is randomized between 5 and negative 5
		velY = 6;
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle (x, y, 15, 20);
		
	}
	
	
	public void tick() {
		x += velX;
		y += velY;
	
		
		if (y >= Game.HEIGHT) {
			handler.removeObject(this);
		}
		//removes bullet after it goes past y border as to not unnecessarily use memory
	
		handler.addObject(new Trail (x, y, ID.Trail, new Color(214, 196, 160), 15, 20, 0.09f, handler));
		
	}

	public void render(Graphics g) {
		g.setColor(new Color(214, 196, 160));
		g.fillRect(x, y, 15, 20);
	}

}