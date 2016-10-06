
/**
 * Created by Rayven Ingles and Manolo Codeneira on 10/1/2016
	The Ranger is a fast hitting ranged class which means he/she will have a low str and high agi while needing int because of her skill Launch Trap(more int = less cooldown for skill)
 */
 import java.util.Random;
public class Ranger extends Hero {

    
    private static final int BASE_ATTACK = 12;
    // because of armor
    private static final int ARMOR = 5;
	private int trapStacks;
	
	private void setTitles(){
		System.out.print(this.getName()+" will now henceforth be known as ");
		//apply random titles to the hero name
		Random r = new Random();
		String[] titles = new String[]{" the Far Seeing"," the Swift"," of the Vale"," of Mirkwood"," of Hearthglen", " the Adventurous"," of the North"};
		this.setName(this.getName().concat(titles[r.nextInt(titles.length)]));
		System.out.print(this.getName()+".\n");
	}

    public Ranger(String name, int level) {
        // + armor will no longer affect HP, but will still reduce damage, implemented a base HP of 100
        super(name, 100, level);// starts off with 100 base HP, but will be modified with strength
		this.setTitles();
		super.addStr(1*level);
		super.addAgi(3*level);
		super.addInt(1*level);
		// starts off with 100 hp and increases hp by 25 per level
		this.setMaxHp(100 + this.getStr()*25);
		this.setHp(100 + this.getStr()*25);
		super.setArmor(ARMOR + this.getAgi() * (1 / 7));
		//cooldown of Trap Strike is 6 turns
		this.setSkillCD(6);
		trapStacks = 0;
    }

	//helper method for a skill called Launch Trap which adds x damage to each normal attack and also increases its damage the longer the battle, as more traps tend to cause more damage
	private int launchTrap(){
		trapStacks++;
		this.setSkillCD(6);
		int damage = BASE_ATTACK + trapStacks*this.getAgi();
		System.out.println(this.getName()+" threw a Bear Trap for "+damage+" damage!");
		return (damage);
	}
	
	//modified attack method to check for a skill cooldown
    public int attack(RPGCharacter enemy) {
		if(getStun() > 0){
			System.out.println(this.getName() + " cannot move.");
			return 0;
		}
        if(this.getSkillCD() <= 0){
			
			return this.launchTrap();
		}else{
			this.setSkillCD(this.getSkillCD()-(int)(this.getInt()/3));
			return BASE_ATTACK + this.getAgi();
		}
    }

  
 public String toString(){
    	
        return super.toString() + " Ranger\n";
    	
    }
}