public class Item {
    private String shortName;
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

    public String getItemDescription(){
        return itemDescription;
    }
    public String toString(){
        return String.format("%s. %s. %s",shortName,longName, itemDescription);
    }
}