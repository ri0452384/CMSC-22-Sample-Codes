
/**
 * Created by Rayven Ingles and Manolo Codeneira on 10/1/2016
	The Mage is a Caster class which means he/she will have a low str and very low agi while having high int to use spell damage
 */
 import java.util.Random;
public class Mage extends Hero {

    // because of two daggers(5 attack damage each)
    private static final int BASE_ATTACK = 10; //normal attack
    // because of armor
    private static final int ARMOR = 3;
	private static final double INT_ARMOR_MULT = 1.5;
	
	private void setTitles(){
		System.out.print(this.getName()+" will now henceforth be known as ");
		//apply random titles to the hero name
		Random r = new Random();
		String[] titles = new String[]{" the Augur"," the Wise"," of the Kirin'Tor"," of Dalaran"," of House Targaryen", " the Steadfast"," of the East"};
		this.setName(this.getName().concat(titles[r.nextInt(titles.length)]));
		System.out.print(this.getName()+".\n");
	}

    public Mage(String name, int level) {
        // + armor will no longer affect HP, but will still reduce damage, implemented a base HP of 100
        super(name, 100, level);// starts off with 100 base HP, but will be modified with strength
		this.setTitles();
		super.addStr(1*level);
		super.addAgi(1*level);
		super.addInt(4*level);
		// starts off with 100 hp and increases hp by 25 per level
		this.setMaxHp(100 + this.getStr()*25);
		this.setHp(100 + this.getStr()*25);
		//set armor value for the hero, for Mage Int increases armor value
		super.setArmor(ARMOR + (int)(INT_ARMOR_MULT*this.getInt()) * (1 / 7));; 
		//base cooldown of Fire Ball is 5 turns
		this.setSkillCD(5);
    }

	//helper method for a skill called fire ball which adds x damage to each normal attack
	private int fireBall(){
		this.setSkillCD(5);
		System.out.println(this.getName()+" casted Fire Ball!");
		return 5*(this.getInt());
	}
	
	//modified attack method to check for a skill cooldown modified by intelligence
    public int attack(RPGCharacter enemy) {
		if(getStun() > 0){
			System.out.println(this.getName() + " cannot move.");
			return 0;
		}
        if(this.getSkillCD() <= 0){
			return this.fireBall();
		}else{
			this.setSkillCD(this.getSkillCD()-1*(int)(this.getInt()/this.getStr()));
			return BASE_ATTACK + this.getInt();
		}
    }

  
 public String toString(){
    	
        return super.toString() + " Mage\n";
    	
    }
}