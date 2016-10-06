
public class Dragon extends Monster {
	
	private static int attack = 75; //normal attack
	
	public Dragon( String name, int hp){
		super(name,hp);
		hp += 150;
		
	}
	
	public int attack(RPGCharacter enemy){
		if(getStun() > 0){
			System.out.println(this.getName() + " cannot move.");
			return 0;
		}
		if((int)(getHp() / super.getMax() * 100) <= 25){
			// enraged mode - lower hp = more damage.
			System.out.println(this.getName()+" is enraged!");
			attack +=42;
		}else if((int)(getHp() / getMax() * 100) <= 10){
			System.out.println(this.getName()+" is preparing its most powerful attack!");
			attack += 450;
		}
		return attack;
	}

}
