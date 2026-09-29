public abstract class Weapon extends Item {

    public Weapon(String shortName, String longName, String itemDescription) {
        super(shortName, longName, itemDescription);
    }

    // Returnerer true hvis våbenet kan bruges
    public abstract boolean canUse();

    // Udfører angreb og returnerer resterende skud/brug (-1 for ubegrænset)
    public abstract int use();
}