package tutorials;

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
import org.newdawn.slick.state.transition.Transition;

public class GameState extends BasicGameState {
	
	
	private AnimationContainer zakuAnimation;
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		zakuAnimation = new AnimationContainer();
		zakuAnimation.setWalkSprite("Images/zaku_walk.png", 64, 64);
		zakuAnimation.setCrouchSprite("Images/zaku_crouch.png", 64, 64); 
		zakuAnimation.setCrouchAnimation(zakuAnimation.getCrouchSprite(), 300);
		zakuAnimation.setWalkAnimation(zakuAnimation.getWalkSprite(), 300);
		zakuAnimation.setReverseWalkAnimation(zakuAnimation.getWalkAnimation());
		zakuAnimation.setVector(new Vector2f());
		zakuAnimation.getVector().set(50,  200);
		
	}
	
	
	//helper method that is invoked when the user holds down right arrow
	private void moveRight(GameContainer container, int delta){
		container.sleep(100);
		zakuAnimation.getVector().set(zakuAnimation.getVector().getX() + 10, zakuAnimation.getVector().getY());
		zakuAnimation.getWalkAnimation().start();
		zakuAnimation.getWalkAnimation().update(delta);
	}
	//helper method that is invoked when the user holds down left arrow
	private void moveLeft(GameContainer container, int delta){
		container.sleep(100);
		zakuAnimation.getVector().set(zakuAnimation.getVector().getX() -10, zakuAnimation.getVector().getY());
		zakuAnimation.getReverseWalkAnimation().start();
		zakuAnimation.getReverseWalkAnimation().update(delta);
	}
	//helper method that is invoked when the user holds down the down arrow key
	private void crouch(GameContainer container, int delta) throws SlickException{
		container.sleep(100);
		// vector doesn't need to change, but the frames need to.
		zakuAnimation.getCrouchAnimation().start();
		zakuAnimation.getCrouchAnimation().update(delta);
	}
	
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		
		
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			sbg.enterState(1,new FadeOutTransition(), new FadeInTransition());
		}
		if(container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyPressed(Input.KEY_RIGHT)){
			moveRight(container, delta);
		}else if(container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyPressed(Input.KEY_LEFT)){
			moveLeft(container, delta);
		}else if(container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyPressed(Input.KEY_DOWN)){
			crouch(container,delta);
		}
		else{
			// do nothing, for now
		}

		
		
		
		
	}
	
	
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(new Image("Images/background.jpg"), 0, 0);
		g.drawString("Stage 1", 50, 50);
		
		if(container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyPressed(Input.KEY_RIGHT)){
			//original orientation of the sprite is facing right, no need to flip
			
			//zakuAnimation.getWalkAnimation().draw(zakuAnimation.getVector().getX(),zakuAnimation.getVector().getY());
			zakuAnimation.getWalkAnimation().draw(zakuAnimation.getVector().x, zakuAnimation.getVector().y);
		}else if(container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyPressed(Input.KEY_LEFT)){
			//if we need to make the image face left, we flip the sprite(frame by frame)
			
			//zakuAnimation.getWalkAnimation().getCurrentFrame().getFlippedCopy(true, false).draw(zakuAnimation.getVector().getX(),zakuAnimation.getVector().getY());
			zakuAnimation.getReverseWalkAnimation().draw(zakuAnimation.getVector().x, zakuAnimation.getVector().y);
		}else if(container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyPressed(Input.KEY_DOWN)){
			
			
			if(zakuAnimation.isfacingRight()){
				zakuAnimation.getCrouchAnimation().draw(zakuAnimation.getVector().x, zakuAnimation.getVector().y);
			}else {
				//if the image is facing left
				zakuAnimation.reverseAnimation(zakuAnimation.getCrouchAnimation()).draw(zakuAnimation.getVector().x, zakuAnimation.getVector().y);
			}
		}
		else{
			//if nothing is pressed, the animation stands still, 
			zakuAnimation.getLastAnimation().draw(zakuAnimation.getVector().x, zakuAnimation.getVector().y);
			zakuAnimation.getLastAnimation().stop();
		}
		
	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
