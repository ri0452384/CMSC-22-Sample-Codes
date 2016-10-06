
public class Minotaur extends Monster {
	
	// minotaurs are strong creatures that are known to endure pain
	
	private static final int BASE_ATTACK = 90; //normal attack
	
	
	

	public Minotaur(String name, int hp) {
		super(name,hp);
		hp += 350;
	}
	
	public int takeDamage(int damage) {
		 		 
		 // damage reduction based on remaining hp.
		 
		 if(getHp()/ super.getMax()*100 >= 75){
			 damage -= (int)(damage*0.15);
		 }
		 
		 else if(getHp()/ super.getMax()*100 >= 50){
			 
			 damage -= (int)(damage*0.35);
		 }
		 
		 else if(((int)getHp()/ super.getMax())*100 >= 25){
			 
			 damage -= (int)(damage*0.70);
		 }
	        return super.takeDamage(damage);
	}
	
	public int EarthSplitter(){
		this.setSkillCD(3);
		System.out.println(this.getName()+" casted Earth Splitter!");
		// higher hp = higher damage
		return 200;
	}
	
	 public int attack(RPGCharacter enemy) {
		 if(getStun() > 0){
			System.out.println(this.getName() + " cannot move.");
			return 0;
		}
		 if(this.getSkillCD() <= 0){
				return this.EarthSplitter();
			}else
				this.setSkillCD(this.getSkillCD()-1);
			return BASE_ATTACK;
	 }
}