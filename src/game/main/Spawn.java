package game.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	
	private Random r = new Random();
	
	private int scoreKeep = 0; 
	//calling handler, HUD and creating score variable
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}//applies HUD and handler to constructor
	
	
	public void tick() {
		
		scoreKeep++;
		//score increases every tick
		
		if (scoreKeep >= 300) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			//every 300 score, the level will increase by one and the counter will reset
			
			if (hud.getLevel() == 2) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 130), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
			}
			else if (hud.getLevel() == 3) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 130), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
			}
			else if (hud.getLevel() == 4) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 110), r.nextInt(Game.HEIGHT - 95), ID.FastEnemy, handler));
			}
			else if (hud.getLevel() == 5) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 110), r.nextInt(Game.HEIGHT - 95), ID.SmartEnemy, handler));
			}
			else if (hud.getLevel() == 7) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 110), r.nextInt(Game.HEIGHT - 95), ID.FastEnemy, handler));
			}
			else if (hud.getLevel() == 10) {
				handler.clearEnemies();
				handler.addObject(new BossAntiCheat((0), Game.HEIGHT - 640, ID.BossAntiCheat, handler));
				handler.addObject(new BossEnemy((Game.WIDTH)/2 - 40, -60, ID.BossEnemy, handler));
			}
			//spawning different enemies as the level increases
		}
		
	}
	
	
}


