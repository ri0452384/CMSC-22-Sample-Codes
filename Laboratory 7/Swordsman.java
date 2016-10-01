
/**
 * Created by nmenego on 9/29/16.
	Modified by Rayven Ingles and Manolo Codeneira on 10/1/2016
	The Swordsman is a balanced melee class which means he/she will have a balance between str and agi while not needing int because of high normal attack damage
 */
public class Swordsman extends Hero {

    // because of sword
    private static final int BASE_ATTACK = 16;
    // because of armor
    private static final int ARMOR = 10;
	
	

    public Swordsman(String name, int level) {
        // + armor will no longer affect HP, but will still reduce damage, implemented a base HP of 100
        super(name, 100, level);// starts off with 100 base HP, but will be modified with strength
		super.addStr(3*level);
		super.addAgi(2*level);
		// starts off with 100 hp and increases hp by 25 per level
		this.setHp(100 + this.getStr()*25);
		//cooldown of Heroic Strike is 5 turns
		this.setSkillCD(5);
    }

	//helper method for a skill called heroicStrike which adds x damage to normal attack
	public int heroicStrike(){
		this.setSkillCD(5);
		System.out.println(this.getName()+" used Heroic Strike!");
		return BASE_ATTACK + this.getStr() * this.getAgi() ;
	}
	//modified attack method to check for a skill cooldown
    public int attack() {
        if(this.getSkillCD() == 0){
			return this.heroicStrike();
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