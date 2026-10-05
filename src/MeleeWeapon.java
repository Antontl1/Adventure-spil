// Nærkampsvåben. Må kun bruges i Map til at oprette våben, alle andre steder hedder det Weapon
public class MeleeWeapon extends Weapon {

    // Har ingen ekstra felter, så alt sendes videre til Weapon
    public MeleeWeapon(String shortName, String longName, String theLongName, String itemDescription, int WeaponPower) {
        super(shortName, longName, theLongName, itemDescription, WeaponPower);
    }

    @Override
    public boolean canUse() {
        return true; // Et nærkampsvåben kan altid bruges
    }

    @Override
    public void use() {
    }

    @Override
    public int getRemainingUses() {
        return -1; // Løber aldrig tør
    }
}
