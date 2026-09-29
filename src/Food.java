// Mad er en ting, der kan spises. Arver fra Item, så den kan ligge i rum og samles op som alt andet
public class Food extends Item{
    // Hvor meget liv maden giver. Negativt tal betyder gift
    // Ikke static: hvert stykke mad har sit eget tal
    private int healthPoints;

    // De tre tekster går videre til Item. healthPoints gemmer Food selv
    public Food(String shortName, String longName, String itemDescription, int healthPoints) {
        super(shortName, longName, itemDescription);
        this.healthPoints = healthPoints;
}

    // Bruges af Player, når maden bliver spist
    public int getHealthPoints() {
        return healthPoints;
    }
}
