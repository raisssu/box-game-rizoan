package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;

public class HUD {

	
	public static float HEALTH = 100;
	
	private float greenValue = 255 * HEALTH / 100;;
	//health bar color
	
	private float time = 0f;
	private int level = 1;
	private int score = 0;
	//initializing variables
	
	public void tick() {
		
		
		HEALTH = Game.clamp(HEALTH, 0, 100);
		//max health 100, minimum 0
		
		greenValue = Game.clamp(greenValue, 0, 255);
		//max green value = 255, least is 0
		
		greenValue = HEALTH*3;
		
		time += (1f/60f);
		//every tick, time is increased by 1/60th of a second, 60 ticks per second
		score += 1;
		//score increased by 1 every tick
		
	}
	
	
	public void render(Graphics g) {
		
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		//format for decimals so they only go up to 2 digits
		
		g.setColor(Color.black);
		g.fillRect(15, 15, 200, 32);
		//create a black rectangle for the health bar
		g.setColor(Color.getHSBColor( (1f * HEALTH) / 360, 1f, 1f));
		g.fillRect(15, 15, (int) (HEALTH * 2), 32);
		//creates the health bar that changes color based on how low it is
		
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		//white border around health bar
		
		g.drawString("Time: " + numberFormat.format(time), 15, 64);
		g.drawString("Score: " + score, 17, 92);
		g.drawString("Level: " + level, 16, 78);
		//printing time, score and level under health bar
	}
	
	
	public float getTime () {
		return time;
	}
	
	public void setTime (int Time) {
		
		this.time = Time;
		
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	//getter and setters for score time and level
	
}
