
/**
 * Created by Rayven Ingles and Manolo Codeneira on 10/1/2016
	The Assassin is a fast hitting melee class which means he/she will have a low str and very high agi while not needing int because of fast normal attack damage
 */
public class Assassin extends Hero {

    // because of two daggers(5 attack damage each)
    private static final int BASE_ATTACK = 10;
    // because of armor
    private static final int ARMOR = 5;
	
	

    public Assassin(String name, int level) {
        // + armor will no longer affect HP, but will still reduce damage, implemented a base HP of 100
        super(name, 100, level);// starts off with 100 base HP, but will be modified with strength
		super.addStr(1*level);
		super.addAgi(4*level);
		// starts off with 100 hp and increases hp by 25 per level
		this.setHp(100 + this.getStr()*25);
		//cooldown of Dual Strike is 2 turns
		this.setSkillCD(2);
    }

	//helper method for a skill called dualStrike which adds x damage to each normal attack
	public int dualStrike(){
		this.setSkillCD(2);
		System.out.println(this.getName()+" used Dual Strike!");
		return 2*(BASE_ATTACK + this.getAgi()+ this.getStr());
	}
	
	//modified attack method to check for a skill cooldown
    public int attack() {
        if(this.getSkillCD() == 0){
			return this.dualStrike();
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