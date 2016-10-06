/**
 * Created by nmenego on 9/29/16. Modified by Rayven Ingles and Manolo Codeneira on 10/5/2016
 */
public abstract class RPGCharacter {

   
	private String name;
    private int hp;
	private int maxHp; // to be used for lifebar
	private int armor=0; //all characters have armor now. yay!
	private int stunDuration = 0; // variable to check whether the character is able to attack due to stuns/blinds, etc. 0 if not stunned, measures number of turns that the character cannot move

	//all Characters(Heroes, Monsters start off with 0 armor)
    public RPGCharacter(String name, int hp) {
        this.setName(name);
		this.maxHp = hp;
    }

    // implement in subclass
    public abstract int attack(RPGCharacter enemy);

    // checks to see if character is still alive
    public boolean isAlive() {
        return hp > 0 ? true : false;
    }
	
	public void setStun(int n)
	{
		if(n > 0){
			stunDuration = n;
		}
		else{
			stunDuration = 0;
		}
	}
	
	public int getStun(){
		return stunDuration;
	}

    // may be overriden in subclass, damage is dependent on hero type
    public int takeDamage(int damage) {
		
		if(damage > 0){
			double Dmg_multi = 1 - (0.06 * armor) / (1 + (0.06 * (armor)));
			hp -= (int)(Dmg_multi*damage);
			System.out.println(getName()+" took "+damage+" damage.");
		}
		
        return hp;
    }

    // getters setters
    public String getName() {
        return name;
    }
    //edited setname function to be able to change first character to upper case and all succeeding letters to lower case
    public void setName(String name) {
    	
    	if(name ==null || name.isEmpty()){
    		throw new IllegalArgumentException("Name must not be empty!");
    	}
    	
    	String[] tokens = name.split("\\s");
    	name = "";

    	for(int i = 0; i < tokens.length; i++){
    	    char capLetter = Character.toUpperCase(tokens[i].charAt(0));
    	    name +=  " " + capLetter + tokens[i].substring(1);
    	}
    	this.name = name.trim();
    	
    }

    public int getHp() {
        return hp;
    }
	
	public int getArmor(){
		return armor;
	}
	
	public void setArmor(int armor){
		
		this.armor = armor;
	}

    public void setHp(int hp) {
		
		if(hp > maxHp){
			//do nothing
		}else{
			this.hp = hp;
		}
    }
	
	public void setMaxHp(int max){
		this.maxHp = max;
	}	
	
	public int getMax(){
		return maxHp;
	}
	
	

    @Override
    public String toString() {
		
        return "\n  HP: <" +hp+"/"+maxHp+ ">  "+((getStun() >0)? "@-Stunned+": "" )+"\n";
    }
}