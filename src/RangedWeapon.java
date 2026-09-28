public class RangedWeapon extends Weapon {
    private int uses; // Antal resterende skud/magasin

    public RangedWeapon(String shortName, String longName, String itemDescription, int uses) {
        super(shortName, longName, itemDescription);
        this.uses = uses;
    }

    @Override
    public boolean canUse() {
        return uses > 0;
    }

    @Override
    public int use() {
        if (canUse()) {
            uses--;
        }
        return uses;
    }
}