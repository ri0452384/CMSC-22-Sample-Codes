
/**
 * Created by nmenego on 9/29/16.
 */
public abstract class Monster extends RPGCharacter{
    private int skillCD;
    

    public Monster(String name, int hp) {
        super(name, hp);
		this.setHp(hp);
		super.setMaxHp(hp);
    }

    
    public int getSkillCD(){
		return skillCD;
	}
    
    public void setSkillCD(int n){
		this.skillCD = n;
	}
	
	public String toString(){
		return "Name: " + getName() + super.toString();
	}

}