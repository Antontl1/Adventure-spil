// Nærkampsvåben. Må kun bruges i Map til at oprette våben, alle andre steder hedder det Weapon
public class MeleeWeapon extends Weapon {

    // Har ingen ekstra felter, så alt sendes videre til Weapon
    public MeleeWeapon(String shortName, String longName, String itemDescription) {
        super(shortName, longName, itemDescription);
    }

    @Override
    public boolean canUse() {
        return true; // Et nærkampsvåben kan altid bruges
    }

    @Override
    public int use() {
        return 1; // Indikerer ubegrænset brug
    }

    @Override
    public int getRemainingUses() {
        return -1; // Løber aldrig tør
    }
}
