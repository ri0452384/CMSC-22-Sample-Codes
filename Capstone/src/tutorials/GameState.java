package tutorials;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameState extends BasicGameState {
	
	
	private AnimationContainer zakuAnimation;
	private StaticLevel level;
	
	//private AnimationContainer megaManAnimation;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		level = new StaticLevel();
		level.init(container, sbg);
		zakuAnimation = new AnimationContainer(level);
		//megaManAnimation = new AnimationContainer();
		Vector2f vector = new Vector2f();
		vector.set(50,200);
		zakuAnimation.init(container,sbg,new SpriteSheet("Images/zaku_walk.png",49, 49),new SpriteSheet("Images/zaku_crouch.png",41,49),vector);
		//vector = new Vector2f();
		//vector.set(50,350);
		//megaManAnimation.init(container, sbg, new SpriteSheet("Images/megaman_walk.png",32,32), new SpriteSheet("Images/megaman_crouch.png",32,32),vector);
	}
	
	
	
	
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		level.update(container, sbg,delta);
		zakuAnimation.update(container, sbg, delta);
		//megaManAnimation.update(container,sbg,delta);
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			sbg.enterState(1,new FadeOutTransition(), new FadeInTransition());
		}
		
		
		
		
	}
	
	
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		drawDebugLines(g,50);
		level.render(container,sbg,g);
		//g.drawImage(new Image("Images/background.jpg"), 0, 0);
		g.drawString("Stage 1", 50, 50);
		
		zakuAnimation.render(container, sbg, g);
		//megaManAnimation.render(container,sbg,g);
	}


	private void drawDebugLines(Graphics g, int size){
		int resolution = 800;
		g.setColor(Color.darkGray);
		for(int i=0; i<resolution; i+=size){
			g.drawLine(i,0,i,resolution);
			g.drawLine(0,i, resolution,i);
			g.drawString(Integer.toString(i), 0, i);
			g.drawString(Integer.toString(i), i, 0);
		}
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
