package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
		
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				player = handler.object.get(i);
			}
		}
		
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle (x, y, 30, 30);
		
	}
	
	
	public void tick() {
		
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		
		velX = (int) Math.round((-2.0/distance) * diffX);
		velY = (int) Math.round((-2.0/distance) * diffY);
		//the smart enemy will always move based on the location of the player
		
//		if (y <= 0 || y >= Game.HEIGHT - 45) {
//			
//			velY *= -1;
//		}
//			
//		if (x <= 0 || x >= Game.WIDTH - 45) {
//				
//			velX *= -1;	
//		}
	
		handler.addObject(new Trail (x, y, ID.Trail, new Color(135,211,181), 30, 30, 0.03f, handler));
		//trail
	}

	public void render(Graphics g) {
		g.setColor(new Color(135,211,181));
		g.fillRect(x, y, 30, 30);
	}//fill enemy

}
