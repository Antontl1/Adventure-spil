public class RangedWeapon extends Weapon{
    int shotsLeft;
    // Sender alt videre til Weapon. Et
    public RangedWeapon(String shortName, String longName, String itemDescription, int damage, int shotsLeft) {
        super(shortName, longName, itemDescription, damage);
        this.shotsLeft = shotsLeft;
    }

    @Override
    public attack(){

    }
}
