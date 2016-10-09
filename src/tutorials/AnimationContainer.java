package tutorials;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class AnimationContainer{
	
	private Animation crouchAnimation;
	private Animation walkAnimation;
	private Animation reverseWalkAnimation;
	private SpriteSheet crouchSprite;
	private SpriteSheet walkSprite;
	protected Vector2f vector;
	private boolean facingRight = true;
	
	
	
	public Animation getLastAnimation(){
		return (facingRight ? getWalkAnimation() : getReverseWalkAnimation());
	}
	
	public boolean isfacingRight(){
		return facingRight;
	}
	
	public Animation getCrouchAnimation() {
		return crouchAnimation;
	}
	public void setCrouchAnimation(SpriteSheet crouchSprite, int delta){
		this.crouchAnimation = new Animation(crouchSprite, delta);
	}
	public Animation getWalkAnimation() {
		facingRight = true;
		return walkAnimation;
	}
	public void setWalkAnimation(SpriteSheet walkSprite, int delta) {
		facingRight = true;
		this.walkAnimation = new Animation(walkSprite, delta);
	}
	public SpriteSheet getCrouchSprite() {
		return crouchSprite;
	}
	public void setCrouchSprite(String imagePath, int length, int width) throws SlickException {
		this.crouchSprite = new SpriteSheet(imagePath,length, width);
	}
	public SpriteSheet getWalkSprite() {
		return walkSprite;
	}
	public void setWalkSprite(String imagePath, int length, int width) throws SlickException {
		this.walkSprite =  new SpriteSheet(imagePath,length, width);
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
	
	public Animation reverseAnimation(Animation animation){
		facingRight = false;
		Animation reverse = new Animation();
		for(int i=0;i<walkAnimation.getFrameCount();i++){
			reverse.addFrame(animation.getImage(i).getFlippedCopy(true, false), animation.getDurations()[i]);
		}
		return reverse;
		
	}
	
	//helper method that returns the exact flipped images of the walkAnimation
	public Animation getReverseWalkAnimation(){
		
		facingRight = false;
		return reverseWalkAnimation;
		
	}
	
	
	
	
	
	
}
