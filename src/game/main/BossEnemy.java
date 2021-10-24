package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject{

	
	private Handler handler;
	Random r = new Random();
	
	private int Timer = 160;
	private int Timer2 = 100;
	//timers for determining movement
	
	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 1;
	}//boss starts by coming down from the top

	
	public Rectangle getBounds() {
		
		return new Rectangle (x, y, 50, 50);
		
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
		//timer1 decides how far down the boss comes
		
		if (Timer <= 0) {
			Timer2--;
		}
		
		if (Timer2 <=0) {
			if (velX == 0) {
				velX = 3;
			}//timer2 will then make the velX 3
					
			if (velX > 0) {
				velX += 0.005f;
			}
			else if (velX < 0) {
				velX -= 0.005f;
			}//this increases the velocity by 0.005 every time the timer2 goes down
			
			velX = Game.clamp(velX, -10, 10);
			//limits the velX to 10 and -10
			
			int spawn = r.nextInt(6);
			
			if (spawn == 0) {
				handler.addObject(new BossEnemyBullet((int)x + 19, (int)y + 45, ID.BasicEnemy, handler));
			}
		}//randomly spawns boss bullets 
		
		
		if (x <= 0 || x >= Game.WIDTH - 65) {
				
			velX *= -1;	
		}//opposite velocity x when the boss touches border
	
		handler.addObject(new Trail (x, y, ID.Trail, new Color(178, 24, 7), 50, 50, 1f, handler));
		//trail
	}

	public void render(Graphics g) {
		g.setColor(new Color(178, 24, 7));
		g.fillRect(x, y, 50, 50);
	}//fills the color of the boss

}
