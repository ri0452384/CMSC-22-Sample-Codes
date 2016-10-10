package tutorials;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class StaticLevel {
	
	private Shape levelBase,platform;
	
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		float[] polygonPoints = new float[]{
				0,0,
				25,0,
				25,550,
				750,550,
				750,350,
				//600,350,
				//600,300,
				750,300,
				750,0,
				800,0,
				800,600,
				0,600
		};
		levelBase = new Polygon(polygonPoints);
		
		
		}
	
	
	
	
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		
		
		
	}
	
	
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.cyan);
		g.draw(levelBase);
		
	}
	
	public boolean collidesWith(Shape player){
		return levelBase.intersects(player);
	}

}
