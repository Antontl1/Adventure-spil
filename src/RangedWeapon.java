// Skydevåben. Må kun bruges i Map til at oprette våben, alle andre steder hedder det Weapon
public class RangedWeapon extends Weapon {
    private int uses; // Antal resterende skud/magasin

    // De tre tekster går videre til Weapon. Antal skud gemmer RangedWeapon selv
    public RangedWeapon(String shortName, String longName, String itemDescription, int uses) {
        super(shortName, longName, itemDescription);
        this.uses = uses;
    }

    // Kan kun bruges, så længe der er skud tilbage
    @Override
    public boolean canUse() {
        return uses > 0;
    }

    // Bruger ét skud og returnerer hvor mange der er tilbage. Går aldrig under 0
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
