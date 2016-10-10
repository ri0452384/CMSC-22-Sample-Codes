package tutorials;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends BasicGameState {

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	
	public void update(GameContainer container, StateBasedGame sbg, int arg2)
			throws SlickException {
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			sbg.enterState(0,new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString("Menu Screen", 20f, 30f);
		g.drawString("Controls", 25, 60);
		g.drawString(" Move Left  = Left Arrow", 25, 90);
		g.drawString(" Move Right = Right Arrow", 25, 120);
		g.drawString(" Crouch     = Down Arrow", 25, 150);
	}



	

	@Override
	public int getID() {
		
		return 1;
	}

}
