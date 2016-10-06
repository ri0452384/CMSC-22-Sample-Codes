
/**
 * Created by Rayven Ingles and Manolo Codeneira on 10/1/2016
	The Novice is a default generic class which means he/she will have average str, average agi while not needing int because of normal attack damage
 */
 import java.util.Random;
public class Novice extends Hero {

    // unarmed weapon
    private static final int BASE_ATTACK = 5;
    // leather armor as default adventurer's armor
    private static final int ARMOR = 4;
	
	private void setTitles(){
		System.out.print(this.getName()+" will now henceforth be known as ");
		//apply random titles to the hero name
		Random r = new Random();
		String[] titles = new String[]{" the Wanderer"," the Feeble"," the Useless"," the Cowardly"," the Runner", " the Deserter"," the Nomad"};
		this.setName(this.getName().concat(titles[r.nextInt(titles.length)]));
		System.out.print(this.getName()+".\n");
	}

    public Novice(String name, int level) {
        // + armor will no longer affect HP, but will still reduce damage, implemented a base HP of 100
        super(name, 100, level);// starts off with 100 base HP, but will be modified with strength
		this.setTitles();
		super.addStr(1*level);
		super.addAgi(1*level);
		// starts off with 100 hp and increases hp by 25 per level
		this.setMaxHp(100 + this.getStr()*25);
		this.setHp(100 + this.getStr()*25);
		super.setArmor(ARMOR + this.getAgi() * (1 / 7));
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
    public int attack(RPGCharacter enemy) {
		if(getStun() > 0){
			System.out.println(this.getName() + " cannot move.");
			return 0;
		}
        if(this.getSkillCD() == 0){
			return this.n00bStrike();
		}else{
			this.setSkillCD(this.getSkillCD()-1);
			return BASE_ATTACK + this.getLevel();
		}
    }

   
 public String toString(){
    	
        return super.toString() + " Novice\n";
    	
    }
}