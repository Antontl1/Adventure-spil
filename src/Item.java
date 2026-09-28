// En ting der kan ligge i et rum eller bæres af spilleren
public class Item {
    // Det spilleren skriver, f.eks. TAKE sword
    private String shortName;
    // Det der vises som navn, f.eks. "A Royal Sword"
    private String longName;
    private String itemDescription;

    public Item(String shortName, String longName, String itemDescription) {
        this.shortName = shortName;
        this.longName = longName;
        this.itemDescription = itemDescription;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    // Bruges automatisk, når et Item printes direkte
    @Override
    public String toString() {
        return String.format("%s. %s. %s", shortName, longName, itemDescription);
    }
}
