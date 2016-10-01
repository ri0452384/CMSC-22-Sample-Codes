
/**
 * Created by Rayven Ingles and Manolo Codeneira on 10/1/2016
	The Mage is a Caster class which means he/she will have a low str and very low agi while having high int to use spell damage
 */
public class Mage extends Hero {

    // because of two daggers(5 attack damage each)
    private static final int BASE_ATTACK = 10; //normal attack
    // because of armor
    private static final int ARMOR = 3;
	
	

    public Mage(String name, int level) {
        // + armor will no longer affect HP, but will still reduce damage, implemented a base HP of 100
        super(name, 100, level);// starts off with 100 base HP, but will be modified with strength
		super.addStr(1*level);
		super.addAgi(1*level);
		super.addInt(4*level);
		// starts off with 100 hp and increases hp by 25 per level
		this.setHp(100 + this.getStr()*25);
		//base cooldown of Fire Ball is 5 turns
		this.setSkillCD(5);
    }

	//helper method for a skill called fire ball which adds x damage to each normal attack
	public int fireBall(){
		this.setSkillCD(5);
		System.out.println(this.getName()+" casted Fire Ball!");
		return 5*(this.getInt());
	}
	
	//modified attack method to check for a skill cooldown modified by intelligence
    public int attack() {
        if(this.getSkillCD() <= 0){
			return this.fireBall();
		}else{
			this.setSkillCD(this.getSkillCD()-1*(int)(this.getInt()/this.getStr()));
			return BASE_ATTACK + this.getLevel();
		}
    }

    public int takeDamage(int damage) {
        //damage is reduced by ARMOR, and twice the Intelligence for Mage class only
        damage -= ARMOR + 2*this.getInt(); 
        // set new hp
        return super.takeDamage(damage);
    }
}