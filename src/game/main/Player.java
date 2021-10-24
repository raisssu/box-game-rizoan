package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		//adds to handler
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle (x, y, 30, 40);
	}
	//player size
		
	public void tick() {
		
		x += velX;
		y += velY;
		
		x = (int) Game.clamp(x, 0, Game.WIDTH - 46);
		
		y = (int) Game.clamp(y, 0, Game.HEIGHT - 79);
		//player cannot go past the border of the window
		
		handler.addObject(new Trail (x, y, ID.Trail, new Color(202,202,202), 30, 40, 0.25f, handler));
		//adds trail
		
		collision();
		//runs collision method every tick
	}
	
	private void collision() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.BossEnemy || tempObject.getId() == ID.BossAntiCheat) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					//collision detection
					HUD.HEALTH -= 2;
				}
				// if the position of the player and any of the other ids ever overlap, the health goes down
			}
			
			}
		}
		
	
	
	public void render(Graphics g) {
		
		g.setColor(new Color(202,202,202));
		g.fillRect(x,  y, 30, 40);
		
	}//fills the player rectangle white

}
