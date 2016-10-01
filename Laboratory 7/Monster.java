
/**
 * Created by nmenego on 9/29/16.
 */
public class Monster extends RPGCharacter{
    private int attackDamage;

    public Monster(String name, int hp, int attackDamage) {
        super(name, hp);
        this.attackDamage = attackDamage;
    }

    public int attack() {
        return attackDamage;
    }

}