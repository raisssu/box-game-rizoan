package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	//main class, uses canvas to make screen and runnable to make use of threads
	
	private static final long serialVersionUID = -473349850293143017L;
	//auto generated serial version UID used to verify running classes
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	//dimensions for game window
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	
	private Menu menu;
	//calling methods and creating variables used in execution of the game
	
	public enum STATE{
		Menu,
		Help,
		Game,
		End
	};
	//different game states 
	
	public static STATE gameState = STATE.Menu;
	//setting default game state to the menu when the game is launched
	
	public Game(){
	//constructor	
	
		
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		//initializing class objects
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		//initializing keyboard and mouse input
		
		new Window(WIDTH, HEIGHT, "game", this);
		
		spawner = new Spawn(handler, hud);		
		//initializing game window and spawn
		
		r = new Random();
		
		if (gameState == STATE.Game) {
			handler.addObject(new Player((Game.WIDTH)/2 - 30, (Game.HEIGHT)/2 - 40, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 130), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
		}
		else {
			for (int i = 0; i < 10; i++) {
				handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 45), r.nextInt(Game.HEIGHT - 50), ID.MenuParticle, handler));
			}
		}
		//spawning player and basic enemy when the game starts and spawns menu particles in the menu. (player and enemy spawn not needed in if statement, they are spawned in menu class)
		
	}
	
	
	public synchronized void start() {
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//starting and stopping thread use when game starts and stops
	
	public void run() {
		
		this.requestFocus();
		//system will focus on game window
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
			
		}
		stop();
	}
	//creating timer and FPS
	
	private void tick() {
		
		handler.tick();
		//starting tick method with handler class
		
		if (gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
		}//HUD and spawner tick timers will start when the game state is game
		
		if (HUD.HEALTH <= 0) {
			HUD.HEALTH = 100;
			gameState = STATE.End;
			handler.clearEnemies();
			
			for (int i = 0; i < 10; i++) {
				handler.addObject(new EndParticle(r.nextInt(Game.WIDTH - 45), r.nextInt(Game.HEIGHT - 50), ID.EndParticle, handler));
			}
		}//when the game ends (player dies), health is reset to 100, game ends, enemies are cleared and end particles are spawned
		
		else if (gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}//otherwise the menu tick will start
		
	}
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(40, 40, 38));
		g.fillRect(0 , 0, WIDTH, HEIGHT);
			
		handler.render(g);
		
		if (gameState == STATE.Game) {
			hud.render(g);
		}
		
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		}

		
		g.dispose();
		bs.show();
			
	}//rendering the background and screens based on game state
	
	
	public static float clamp(float var, int min, int max) {
		
		if (var >= max) {
			return var = max;
		}
		
		else if (var <= min) {
			return var = min;
		}
		
		else 
			return var;
		
	}//clamp for max and min values
	
	
	public static void main(String args[]) {
		
		new Game();
		
	}//runs game
	
}
