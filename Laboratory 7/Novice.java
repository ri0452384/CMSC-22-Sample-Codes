
/**
 * Created by Rayven Ingles and Manolo Codeneira on 10/1/2016
	The Novice is a default generic class which means he/she will have average str, average agi while not needing int because of normal attack damage
 */
public class Novice extends Hero {

    // unarmed weapon
    private static final int BASE_ATTACK = 5;
    // leather armor as default adventurer's armor
    private static final int ARMOR = 4;
	
	

    public Novice(String name, int level) {
        // + armor will no longer affect HP, but will still reduce damage, implemented a base HP of 100
        super(name, 100, level);// starts off with 100 base HP, but will be modified with strength
		super.addStr(1*level);
		super.addAgi(1*level);
		// starts off with 100 hp and increases hp by 25 per level
		this.setHp(100 + this.getStr()*25);
		//cooldown of N00b Strike is 3 turns
		this.setSkillCD(3);
    }

	//helper method for a skill called n00bStrike which adds x damage to normal attack
	public int n00bStrike(){
		this.setSkillCD(3);
		System.out.println(this.getName()+" used N00b Strike!");
		return (BASE_ATTACK + this.getAgi()+ this.getStr());
	}
	
	//modified attack method to check for a skill cooldown
    public int attack() {
        if(this.getSkillCD() == 0){
			return this.n00bStrike();
		}else{
			this.setSkillCD(this.getSkillCD()-1);
			return BASE_ATTACK + this.getLevel();
		}
    }

    public int takeDamage(int damage) {
        // reduce damage because of armor!!! oh yeah!
        damage -= ARMOR + this.getAgi(); //damage is reduced by 1 per agility point
        // set new hp
        return super.takeDamage(damage);
    }
}