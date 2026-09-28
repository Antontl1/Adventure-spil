public class MeleeWeapon extends Weapon {

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
}