public class MeleeWeapon extends Weapon {
    public MeleeWeapon(String shortName, String longName, String theLongName, String itemDescription, int weaponPower) {
        super(shortName, longName, theLongName, itemDescription, weaponPower);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void use() {
        // Nærkampsvåben forbruger ikke ammunition
    }

    @Override
    public int getRemainingUses() {
        return -1;
    }
}