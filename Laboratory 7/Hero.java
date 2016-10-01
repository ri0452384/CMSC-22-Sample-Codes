
/**
 * Created by nmenego on 9/29/16.
 Modified by Rayven Ingles and Manolo Codeneira 10/1/2016
 */
public abstract class Hero extends RPGCharacter {
    private int level;
	private int strength; //determines hp, and damage of some classes
	private int agility; //adds further damage reduction, and damage to some classes
	private int intelligence; //affects skill cooldown, adds damage to some skills, and damage reduction to some classes
	private int skillCD; //  skill cooldowns to be implemented in the attack method of all subclasses
	
	//constructor for a generic hero with base stats all set to 10
    public Hero(String name, int hp, int level) {
        super(name, hp);
        this.level = level;
		this.strength = 10;
		this.agility = 10;
		this.intelligence = 10;
	}
	
	//to add agility stats to the hero
	public void addAgi(int n){
		this.agility += n;
	}
	//to add intelligence stats to the hero
	public void addInt(int n){
		this.intelligence += n;
	}
	//to add strength stats to the hero
	public void addStr(int n){
		this.strength += n;
	}
	
	

    // getters and setters
    public int getLevel() {
        return level;
    }
	
	public int getStr(){
		return strength;
	}
	
	public int getAgi(){
		return agility;
	}
	
	public int getInt(){
		return intelligence;
	}
	
	public int getSkillCD(){
		return skillCD;
	}

    public void setLevel(int level) {
        this.level = level;
    }
	
	public void setSkillCD(int n){
		this.skillCD = n;
	}

}