// Fælles superklasse for alle våben. Abstrakt, så man kan ikke lave et "Weapon", kun et nærkamps- eller skydevåben.
// Arver fra Item, så et våben kan ligge i et rum og samles op som alle andre ting
public abstract class Weapon extends Item {
    String theLongName;
    int WeaponPower;

    // Sender teksterne videre til Item
    public Weapon(String shortName, String longName, String theLongName, String itemDescription, int WeaponPower) {
        super(shortName, longName, itemDescription);
        this.theLongName = theLongName;
        this.WeaponPower = WeaponPower;
    }

    // Returnerer true hvis våbenet kan bruges
    // Abstrakt: hver subklasse skriver sin egen udgave
    public abstract boolean canUse();

    // Udfører angreb og returnerer resterende skud/brug (-1 for ubegrænset)
    public abstract void use();

    // Hvor mange skud der er tilbage. -1 betyder ubegrænset
    public abstract int getRemainingUses();

    //skaffer det lange navn med the foran
    public String getTheLongName(){
        return theLongName;
    }
}
