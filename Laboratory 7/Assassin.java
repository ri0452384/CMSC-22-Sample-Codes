
/**
 * Created by Rayven Ingles and Manolo Codeneira on 10/1/2016
	The Assassin is a fast hitting melee class which means he/she will have a low str and very high agi while not needing int because of fast normal attack damage
 */
 import java.util.Random;
public class Assassin extends Hero {

    // because of two daggers(5 attack damage each)
    private static final int BASE_ATTACK = 10;
    // because of armor
    private static final int ARMOR = 5;
	private static int energy = 100; //a resource to be used for skills, starts at full then regenerates at a base rate of 25 per second PLUS 1 agi/level
	private static final int AGI_MULTIPLIER = 2;
	
	private void setTitles(){
		System.out.print(this.getName()+" will now henceforth be known as ");
		//apply random titles to the hero name
		Random r = new Random();
		String[] titles = new String[]{" the Merciless"," the Agile"," the Swift"," Quickblade"," of the Brotherhood", " the Silent"," of the South"};
		this.setName(this.getName().concat(titles[r.nextInt(titles.length)]));
		System.out.print(this.getName()+".\n");
	}

    public Assassin(String name, int level) {
        // + armor will no longer affect HP, but will still reduce damage, implemented a base HP of 100
		// starts off with 100 base HP, but will be modified with strength and increases hp by 25 per level
        super(name, 100 , level);
		this.setTitles();
		super.addStr(1*level);
		super.addAgi(4*level);
		super.setArmor(ARMOR + this.getAgi() * (1 / 7));
		this.setMaxHp(100 + this.getStr()*25);
		this.setHp(100 + this.getStr()*25);
		
		//cooldown of Dual Strike is 2 turns
		this.setSkillCD(2);
    }

	//helper method for a skill called dualStrike which adds x damage to each normal attack
	public int dualStrike(RPGCharacter enemy){
		
		System.out.println(this.getName()+" used Dual Strike for "+(int)(2*(BASE_ATTACK + this.getAgi()+ this.getStr()))+" damage! \n");
		if(energy <=75)
			energy += 25;
		enemy.setStun(0);
		return 2*(BASE_ATTACK + this.getAgi()+ this.getStr());
	}
	//helper method for another skill to be used in the attack function
	//the Assassin attempts to gouge the eyes of his/her enemy, dealing damage while blinding the opponent
	private int gouge(RPGCharacter enemy){
		enemy.setStun(2);
		this.setSkillCD(2);
		System.out.println(getName() +" used Gouge on "+enemy.getName()+"!");
		return BASE_ATTACK + 2*this.getAgi();
	}
	
	//modified attack method to check for a skill cooldown and sufficient resource
    public int attack(RPGCharacter enemy) {
		if(this.getStun() > 0){
			System.out.println(this.getName() + "cannot move.");
			return 0;
		}
			if(this.getSkillCD() == 0 && energy >= 30){
				this.energy -=30;
				return this.dualStrike(enemy);
			}else if(energy >= 45){
				this.energy -=45;
				return this.gouge(enemy);
			}else{
				this.setSkillCD(this.getSkillCD()-1);
				int dmg = (int) BASE_ATTACK + this.getAgi();
				return dmg;
			}
		
    }

   
    public String toString(){
    	
        return super.toString() + " Assassin\n";
    	
    }
}