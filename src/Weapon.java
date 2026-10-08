public abstract class Weapon extends Item {
    private final String theLongName;
    private final int weaponPower;

    public Weapon(String shortName, String longName, String theLongName, String itemDescription, int weaponPower) {
        super(shortName, longName, itemDescription);
        this.theLongName = theLongName;
        this.weaponPower = weaponPower;
    }

    public abstract boolean canUse();
    public abstract void use();
    public abstract int getRemainingUses();

    public String getTheLongName() {
        return theLongName;
    }

    public int getDamage() {
        return weaponPower;
    }
}