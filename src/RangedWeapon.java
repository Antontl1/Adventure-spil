public class RangedWeapon extends Weapon {
    private int uses;

    public RangedWeapon(String shortName, String longName, String theLongName, String itemDescription, int weaponPower, int uses) {
        super(shortName, longName, theLongName, itemDescription, weaponPower);
        this.uses = uses;
    }

    @Override
    public boolean canUse() {
        return uses > 0;
    }

    @Override
    public void use() {
        if (canUse()) {
            uses--;
        }
    }

    @Override
    public int getRemainingUses() {
        return uses;
    }
}