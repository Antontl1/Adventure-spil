// Nærkampsvåben. Bruges kun i Map til at oprette våben
public class MeleeWeapon extends Weapon {

    // Sender alt videre til Weapon. Et nærkampsvåben har ikke noget ekstra, fordi det aldrig løber tør
    public MeleeWeapon(String shortName, String longName, String itemDescription, int damage) {
        super(shortName, longName, itemDescription, damage);
    }
}
