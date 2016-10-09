package tutorials;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class MainGame extends StateBasedGame{

	
	
	public MainGame(String title) {
		super(title);
		
	}

	

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new MainGame("Test Title"));
		app.setDisplayMode(640, 480, false);
		app.start();

	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		
		this.addState(new GameState());
		this.addState(new Menu());
		this.addState(new GameOverState());
		
	}
	

}
