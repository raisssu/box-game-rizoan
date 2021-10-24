package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Random;

import game.main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game; 
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed (MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		//mouse location
		
		if (Game.gameState == STATE.Menu) {
		//mouse will only be used in menu, help and end states
			
		//play button
		if (mouseOver(mx, my, 220, 150, 200, 64)) {
			//creates a box at the specified coordinates that will allow the user to start the game
			Game.gameState = STATE.Game;
			
			handler.addObject(new Player((Game.WIDTH)/2 - 30, (Game.HEIGHT)/2 - 40, ID.Player, handler));
			//adds player
			handler.clearEnemies();
			//clears particles
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 130), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
			//adds first enemy
		}
		
		//help button
		if (mouseOver(mx, my, 220, 225, 200, 64)) {
			Game.gameState = STATE.Help;
		}
		
		//quit button
				if (mouseOver(mx, my, 220, 300, 200, 64)) {
					System.exit(0);
				}
		}
		
		//back for help
		if (Game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 220, 300, 200, 64)) {
				Game.gameState = STATE.Menu;
				return;
			}
		}
		
		//play again after death
		if (Game.gameState == STATE.End) {
			if (mouseOver(mx, my, 220, 275, 200, 64)) {
				Game.gameState = STATE.Game;

				hud.setLevel(1);
				hud.setScore(0);
				hud.setTime(0);
				
				handler.addObject(new Player((Game.WIDTH)/2 - 30, (Game.HEIGHT)/2 - 40, ID.Player, handler));
				
				handler.clearEnemies();
				
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 130), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
				
			}
		}
		
		//go to menu after death
		if (Game.gameState == STATE.End) {
			if (mouseOver(mx, my, 220, 350, 200, 64)) {
				Game.gameState = STATE.Menu;

				handler.object.clear();
				
				for (int i = 0; i < 10; i++) {
					handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 45), r.nextInt(Game.HEIGHT - 50), ID.MenuParticle, handler));
				}//add 10 menu particles
				
			}
		}
		
	}
	
	public void mouseReleased (MouseEvent e) {
		
	}
	
	private boolean mouseOver (int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}
			else {
				return false;
			}
		}
		
		else {
			return false;
		}
	}//method for checking whether the mouse is over the specified pixels
	
	public void tick() {
		
	}
	
	public void render (Graphics g) {
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		if (Game.gameState == STATE.Menu) {
		
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		//creating fonts
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("MENU", 245, 96);
		//printing menu text
		
		g.setFont(fnt2);
		g.setColor(Color.white);
		
		g.drawRect(220, 150, 200, 64);
		g.drawString("PLAY", 285, 192);
		
		g.drawRect(220, 225, 200, 64);
		g.drawString("HELP", 285, 267);
		
		g.drawRect(220, 300, 200, 64);
		g.drawString("QUIT", 285, 342);
		//printing play, help and quit boxes and text
		}
		
		else if (Game.gameState == STATE.Help) {
			
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		Font fnt3 = new Font("arial", 1, 20);
		//recreating fonts
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("HELP", 245, 96);
		
		g.setFont(fnt3);
		g.drawString("Use the WASD keys to move around", 150, 200);
		g.drawString("and dodge the enemies!", 200, 225);
		//printing help text
		g.setFont(fnt2);
		g.drawRect(220, 300, 200, 64);
		g.drawString("BACK", 285, 342);
		//back button
		}
		
		else if (Game.gameState == STATE.End) {
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			//fonts again
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("FAIL", 270, 96);
			//fail text
			g.setFont(fnt3);
			g.drawString("Your Score was " + hud.getScore(), 225, 200);
			g.drawString("You Survived " + numberFormat.format(hud.getTime()) + " Seconds", 190, 225);
			//printing text
			g.setFont(fnt2);
			g.drawRect(220, 275, 200, 64);
			g.drawString("Go Again", 257, 317);
			//play again button
			g.drawRect(220, 350, 200, 64);
			g.drawString("Menu", 283, 392);
			//menu button
			
			}
		
		
	}
	
}
