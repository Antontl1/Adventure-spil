public class Food extends Item{
    private int healthPoints;

    public Food(String shortName, String longName, String itemDescription, int healthPoints) {
        super(shortName, longName, itemDescription);
        this.healthPoints = healthPoints;
}

    public int getHealthPoints() {
        return healthPoints;
    }
}
