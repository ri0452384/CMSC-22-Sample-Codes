
/**
 * Created by Rayven Ingles and Manolo Codeneira on 10/1/2016
	The Ranger is a fast hitting ranged class which means he/she will have a low str and high agi while needing int because of her skill Launch Trap(more int = less cooldown for skill)
 */
public class Ranger extends Hero {

    
    private static final int BASE_ATTACK = 12;
    // because of armor
    private static final int ARMOR = 5;
	private int trapStacks;
	

    public Ranger(String name, int level) {
        // + armor will no longer affect HP, but will still reduce damage, implemented a base HP of 100
        super(name, 100, level);// starts off with 100 base HP, but will be modified with strength
		super.addStr(1*level);
		super.addAgi(3*level);
		super.addInt(1*level);
		// starts off with 100 hp and increases hp by 25 per level
		this.setHp(100 + this.getStr()*25);
		//cooldown of Trap Strike is 6 turns
		this.setSkillCD(6);
		trapStacks = 0;
    }

	//helper method for a skill called Launch Trap which adds x damage to each normal attack and also increases its damage the longer the battle, as more traps tend to cause more damage
	private int launchTrap(){
		trapStacks++;
		this.setSkillCD(6);
		System.out.println(this.getName()+" threw a Bear Trap!");
		return (BASE_ATTACK + trapStacks*this.getAgi());
	}
	
	//modified attack method to check for a skill cooldown
    public int attack() {
        if(this.getSkillCD() <= 0){
			return this.launchTrap();
		}else{
			this.setSkillCD(this.getSkillCD()-(int)(this.getInt()/3));
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