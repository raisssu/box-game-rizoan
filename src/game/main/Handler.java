package game.main;

import java.awt.Graphics;
import java.util.LinkedList;

import game.main.Game.STATE;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	//creates a list of game objects
	
		public void tick() {
			for (int i = 0; i < object.size(); i++) {
				
				GameObject tempObject = object.get(i);
				
				tempObject.tick();
			}
		}//apply tick for every item in the linked list
		
		
		public void render(Graphics g) {
			for (int i = 0; i < object.size(); i++) {
				
				GameObject tempObject = object.get(i);
				
				tempObject.render(g);
			}
		}//apply graphics for every item in the linked list
		
		public void clearEnemies() {
			for (int i = 0; i < object.size(); i++) {
				
				GameObject tempObject = object.get(i);
				
				if (tempObject.getId() == ID.Player) {
					object.clear();
					if (Game.gameState != STATE.End) {
					addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
					}
					
				}
			}//removes enemies when the clear enemies method is called and removes the player when the game state is the ending
		}
		
		public void addObject(GameObject object) {
			this.object.add(object);
		}
		
		public void removeObject(GameObject object) {
			this.object.remove(object);
		}
		
}
