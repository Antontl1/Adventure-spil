public class Weapon extends Item {
    private final int damage;

    public Weapon(String shortName, String longName, String itemDescription, int damage) {
    super(shortName, longName, itemDescription);
    this.damage = damage;
}

public void equip(){
    IO.println("Weapon is equipped");
}

public attack(){

}
}
