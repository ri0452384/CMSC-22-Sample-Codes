package tutorials;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class AnimationContainer{
	//variable holders for animation
	private Animation crouchAnimation;
	private Animation reverseCrouchAnimation;
	private Animation walkAnimation;
	private Animation reverseWalkAnimation;
	private Animation currentAnimation;
	//variables to set the textures/sprites
	private SpriteSheet crouchSprite;
	private SpriteSheet walkSprite;
	protected Vector2f vector;
	private boolean facingRight;
	
	//variables for collision
	private Shape hitbox;
	private StaticLevel level;
	
	
	//variable for velocity and acceleration, and level-to-player collision testing
	private static float gravity;
	private static float jumpStrength;
	private static float speed;
	private int iterations;
	private float vX;
	private float vY;
	
	
	public AnimationContainer(StaticLevel level){
		this.level = level;
		facingRight = true;
		gravity = 9.8f;
		jumpStrength = -60;
		speed = 7;
		iterations = 500;
		vX = 0;
		vY = 0;
	}
	
	
	public Animation getLastAnimation(){
		return currentAnimation;
	}
	
	public boolean isfacingRight(){
		return facingRight;
	}
	
	public void getCrouchAnimation() {
		
		// vector doesn't need to change, but the frames need to.
		if(facingRight){
			currentAnimation = crouchAnimation;
		} else{ 
			currentAnimation = reverseCrouchAnimation;
		}
		
	}
	
	public void setCrouchAnimation(SpriteSheet crouchSprite, int delta){
		this.crouchAnimation = new Animation(crouchSprite, delta);
	}
	
	public void getWalkAnimation() {
		facingRight = true;
		currentAnimation = walkAnimation;
	}
	public void setWalkAnimation(SpriteSheet walkSprite, int delta) {
		facingRight = true;
		this.walkAnimation = new Animation(walkSprite, delta);
	}
	public SpriteSheet getCrouchSprite() {
		return crouchSprite;
	}
	
	public void setCrouchSprite(SpriteSheet crouchSprite) throws SlickException {
		this.crouchSprite = crouchSprite;
	}
	
	public SpriteSheet getWalkSprite() {
		return walkSprite;
	}
	public void setWalkSprite(SpriteSheet walkSprite) throws SlickException {
		this.walkSprite =  walkSprite;
	}
	public Vector2f getVector() {
		return vector;
	}
	public void setVector(Vector2f vector) {
		this.vector = vector;
	}
	
	
	//must be invoked in the init state of the GameState so it can initialize the reverse-walk animation
	public void setReverseWalkAnimation(Animation walkAnimation){
		facingRight = false;
		reverseWalkAnimation = new Animation();
		for(int i=0;i<walkAnimation.getFrameCount();i++){
			reverseWalkAnimation.addFrame(walkAnimation.getImage(i).getFlippedCopy(true, false), walkAnimation.getDurations()[i]);
		}
		
	}
	
	public void setReverseCrouchAnimation(Animation crouchAnimation){
		facingRight = false;
		reverseCrouchAnimation = new Animation();
		for(int i=0;i<crouchAnimation.getFrameCount();i++){
			reverseCrouchAnimation.addFrame(crouchAnimation.getImage(i).getFlippedCopy(true, false), crouchAnimation.getDurations()[i]);
		}
		
	}
	
	
	//helper method that returns the exact flipped images of the walkAnimation
	public void getReverseWalkAnimation(){
		
		facingRight = false;
		currentAnimation = reverseWalkAnimation;
		
	}
	
	public void getReverseCrouchAnimation(){
		facingRight = false;
		currentAnimation = reverseCrouchAnimation;
	}
	
	
	public void init(GameContainer arg0, StateBasedGame arg1,SpriteSheet walkSprite, SpriteSheet crouchSprite,Vector2f vector)
			throws SlickException {
		
		
		setCrouchAnimation(crouchSprite, 300);
		setWalkAnimation(walkSprite, 300);
		setReverseWalkAnimation(walkAnimation);
		setReverseCrouchAnimation(crouchAnimation);
		
		facingRight = true;
		currentAnimation = walkAnimation;
		setVector(vector);
		
		hitbox = new Rectangle(vector.x,vector.y,currentAnimation.getCurrentFrame().getWidth(),currentAnimation.getCurrentFrame().getHeight());
		
	}
	
	
	//helper method that is invoked when the user holds down right arrow
	private void moveRight(GameContainer container, int delta){
		//update the animation to make the player appear to walk towards the right
		facingRight = true;
		getVector().set(getVector().getX() + vX, getVector().getY());
		currentAnimation = walkAnimation;
		getLastAnimation().start();
		getLastAnimation().update(delta);
		
		//physics to increase the x-vector of the player by the current speed
		vX = speed;
		float vXTemp = vX/iterations;
		for(int i = 0; i  <iterations; i++){
		hitbox.setX(hitbox.getX() + vXTemp);
			if(level.collidesWith(hitbox)){
				vector.x = ((float) (vector.getX() - vXTemp));
				hitbox.setX((float) (vector.getX() - vXTemp));
				vX = 0;
			}
		}
		
		
	}
	//helper method that is invoked when the user holds down left arrow
	private void moveLeft(GameContainer container, int delta){
		//update the animation to make the player appear to walk towards the right
		getVector().set(getVector().getX() +vX, getVector().getY());
		facingRight = false;
		currentAnimation = reverseWalkAnimation;
		getLastAnimation().start();
		getLastAnimation().update(delta);
		//physics to increase the x-vector of the player by the current speed(negative, since going to the left
		vX = -speed;
		float vXTemp = vX/iterations;
		for(int i = 0; i  <iterations; i++){
				hitbox.setX(hitbox.getX() + vXTemp);
				if(level.collidesWith(hitbox)){
					vector.x = ((float) (vector.getX() - vXTemp));
					hitbox.setX((float) (vector.getX() - vXTemp));
					vX = 0;
				}
		}	
		
		
	}
	//helper method that is invoked when the user holds down the down arrow key
	private void crouch(GameContainer container, int delta) throws SlickException{
		//nothing much, just updates the crouch animation depending on where the player is facing
		if(facingRight){
			currentAnimation = crouchAnimation;
		}else{
			currentAnimation = reverseCrouchAnimation;
		}
		
		float vYTemp = vY/iterations;
		for(int i = 0; i  <iterations; i++){
			vector.y = (float) (vector.getY() + vYTemp);
			hitbox.setY((float) (hitbox.getY() + vYTemp));
			if(level.collidesWith(hitbox)){
				//move the y value ever so slightly upward to avoid clipping through floors
				vector.y = ((float) (vector.getY() - vYTemp -.0001));
				hitbox.setY((float) (vector.getY() - vYTemp -.0001));
				vY = 0;
			}
		}
		getLastAnimation().start();
		getLastAnimation().update(delta);
		
	}
	
	/*take note that in order to jump, the player must be on the ground
	  to simulate that effect, this function will add the y value to make it collide with the surface temporarily to enable jumping
	*/	
	private void jump(GameContainer container,int delta)throws SlickException{
		
		vector.y = (float) (vector.getY() + .1);
		hitbox.setY((float) (hitbox.getY() + .1));
		if(level.collidesWith(hitbox)){
			vY = jumpStrength;
		}
		//the offset value is then added again to the y values to avoid clipping through the floor
		vector.y = (float) (vector.getY() - .1);
		hitbox.setY((float) (hitbox.getY() - .1));
		
	}
	
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		
		vY += gravity;
		//gravity is checked ALL THE TIME, else player will free float and escape the level
		float vYTemp = vY/iterations;
		for(int i = 0; i  <iterations; i++){
			vector.y = (float) (vector.getY() + vYTemp);
			hitbox.setY((float) (hitbox.getY() + vYTemp));
			if(level.collidesWith(hitbox)){
				//move the y value ever so slightly upward to avoid clipping through floors
				vector.y = ((float) (vector.getY() - vYTemp -.0001));
				hitbox.setY((float) (vector.getY() - vYTemp -.0001));
				vY = 0;
			}
		}
		
		if( container.getInput().isKeyPressed(Input.KEY_SPACE)|| container.getInput().isKeyDown(Input.KEY_SPACE)){
			
			jump(container,delta);
		}
		
		if(container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyPressed(Input.KEY_DOWN)){
			crouch(container,delta);
		}
		
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			sbg.enterState(1,new FadeOutTransition(), new FadeInTransition());
		}
		
		if(container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyPressed(Input.KEY_RIGHT)){
			
			moveRight(container, delta);
		}else if(container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyPressed(Input.KEY_LEFT)){
			
			moveLeft(container, delta);
		}else{
			// do nothing, for now
			vX = 0;
		}

		
		
		
		
	}
	
	
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		container.sleep(50);
		g.draw(hitbox);
		if(container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyPressed(Input.KEY_RIGHT)){
			//original orientation of the sprite is facing right, no need to flip
			this.getLastAnimation().draw(this.getVector().x, this.getVector().y);
		}else if(container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyPressed(Input.KEY_LEFT)){
			//if we need to make the image face left, we flip the sprite(frame by frame)
			this.getLastAnimation().draw(this.getVector().x, this.getVector().y);
		}else if(container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyPressed(Input.KEY_DOWN)){
			
				this.getLastAnimation().draw(this.getVector().x, this.getVector().y);
		}
		else{
			//if nothing is pressed, the animation stands still, 
			 if(facingRight){
				 currentAnimation = walkAnimation;
			 } else{
				 currentAnimation = reverseWalkAnimation;
			 }
			this.getLastAnimation().draw(this.getVector().x, this.getVector().y);
			this.getLastAnimation().stop();
		}
		
		
	}

	public Shape getPlayer() {
		return hitbox;
	}

	public void setPlayer(Shape player) {
		this.hitbox = player;
	}

	public StaticLevel getLevel() {
		return level;
	}

	public void setLevel(StaticLevel level) {
		this.level = level;
	}
	
	
	
}
