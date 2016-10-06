
public class Harpy extends Monster {

	// harpies are flying, making them hard to hit
	
	private static int attack = 65;

	
	
	public Harpy(String name, int hp) {
		super(name, hp);
	}
	
	public int AerialAce(){
		this.setSkillCD(4);
		System.out.println(this.getName()+" has casted Aerial Ace! Its claws has been sharpened!");
		attack += 15;
		return attack + 45;
	}
	
	public int attack(RPGCharacter enemy){
		if(getStun() > 0){
			System.out.println(this.getName() + " cannot move.");
			return 0;
		}
		if(this.getSkillCD() <= 0){
			return this.AerialAce();
		}else
			this.setSkillCD(this.getSkillCD()-1);
		return attack;
	}
	
	// high evasion , can be coded into taking 0 damage
	
	 public int takeDamage(int damage) {
		 
		 
		 if(this.getSkillCD() == 0){
			 this.setSkillCD(3);
			 System.out.println(getName() + " dodged the attack successfully.");
			 super.takeDamage(0);
			 return this.getHp();
		 }else{
		 	
		 	
		 	this.setSkillCD(getSkillCD()-1);
			super.takeDamage(damage);
		 	//this.setHp(this.getHp() - damage);
	        return this.getHp();
	    }
	 }

}
	