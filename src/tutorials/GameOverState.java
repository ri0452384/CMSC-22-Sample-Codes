package tutorials;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends BasicGameState {

	
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub

	}


	public void update(GameContainer container, StateBasedGame sbg, int g)
			throws SlickException {
		if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
			sbg.enterState(0);
		}

	}
	

	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString("Game Over!", 200, 200);

	}


	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
