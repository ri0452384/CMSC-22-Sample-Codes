
public class Ogre extends Monster {
	 
	private static final int BASE_ATTACK = 100; //normal attack
	
	
	public Ogre(String name, int hp) {
        super(name, hp);
        // bonus
        hp += 250;
    }
	
	public int HeavySmash(){
		this.setSkillCD(4);
		System.out.println(this.getName()+" casted Heavy Smash!");
		// higher hp = higher damage
		return (int)(this.getHp()*0.2);
	}
	
	 public int attack(RPGCharacter enemy) {
		 if(getStun() > 0){
			System.out.println(this.getName() + " cannot move.");
			return 0;
		}
		 if(this.getSkillCD() <= 0){
				return this.HeavySmash();
			}else
				this.setSkillCD(this.getSkillCD()-1);
			return BASE_ATTACK;
	 }
}
