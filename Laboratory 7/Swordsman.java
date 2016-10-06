
/**
 * Created by nmenego on 9/29/16.
	Modified by Rayven Ingles and Manolo Codeneira on 10/1/2016
	The Swordsman is a balanced melee class which means he/she will have a balance between str and agi while not needing int because of high normal attack damage
 */
 import java.util.Random;
 
public class Swordsman extends Hero {

    // because of sword
    private static final int BASE_ATTACK = 16;
    // because of armor
    private static final int ARMOR = 10;
	
	private void setTitles(){
		System.out.print(this.getName()+" will now henceforth be known as ");
		//apply random titles to the hero name
		Random r = new Random();
		String[] titles = new String[]{" the Brave"," the Valiant"," the Courageous"," the Lionhearted"," the Bold", " the Gallant"," the Daring"};
		this.setName(this.getName().concat(titles[r.nextInt(titles.length)]));
		System.out.print(this.getName()+".\n");
	}

    public Swordsman(String name, int level) {
        // + armor will no longer affect HP, but will still reduce damage, implemented a base HP of 100
		super(name, 100, level);// starts off with 100 base HP, but will be modified with strength
		
		this.setTitles();
		
		super.addStr(3*level);
		super.addAgi(2*level);
		// starts off with 100 hp and increases hp by 25 per level
		this.setMaxHp(100 + this.getStr()*25);
		this.setHp(100 + this.getStr()*25);
		super.setArmor(ARMOR + this.getAgi() * (1 / 7));
		//cooldown of Heroic Strike is 5 turns
		this.setSkillCD(5);
    }

	//helper method for a skill called heroicStrike which adds x damage to normal attack
	public int heroicStrike(){
		this.setSkillCD(5);
		System.out.println(this.getName()+" used Heroic Strike!");
		return BASE_ATTACK + 2*this.getStr() + this.getAgi() ;
	}
	//modified attack method to check for a skill cooldown
    public int attack(RPGCharacter enemy) {
		if(getStun() > 0){
			System.out.println(this.getName() + " cannot move.");
			return 0;
		}
        if(this.getSkillCD() == 0){
			return this.heroicStrike();
		}else{
			this.setSkillCD(this.getSkillCD()-1);
			return BASE_ATTACK + this.getStr();
		}
    }
   
 public String toString(){
    	
        return super.toString() + " Swordsman\n";
    	
    }
}