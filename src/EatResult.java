// Samler alt om at spise én ting i ét objekt, så Player kan returnere flere værdier på én gang.
// Laves i Player.eat() og printes af UserInterface.handleEat()
public class EatResult {
    // Hvordan det gik. Afgør hvilken besked der vises
    private FoodStatus status;
    // Det navn spilleren skrev, f.eks. "apple"
    private String itemName;
    // Madens healthPoints. Negativt tal betyder gift
    private int healthGained;
    // Spillerens liv efter måltidet
    private int newHealth;
    // true hvis giften slog spilleren ihjel. Bruges til GAME OVER
    private boolean playerDied;

    // Ved NOT_FOUND og NOT_FOOD sendes 0 med som healthGained, fordi intet blev spist
    public EatResult(FoodStatus status, String itemName, int healthGained, int newHealth, boolean playerDied) {
        this.status = status;
        this.itemName = itemName;
        this.healthGained = healthGained;
        this.newHealth = newHealth;
        this.playerDied = playerDied;
    }

    public FoodStatus getStatus() { return status; }
    // UserInterface spørger her, om spillet skal stoppe
    public boolean isPlayerDied() { return playerDied; }

    // Bygger teksten til spilleren ud fra status. switch giver en String tilbage i stedet for at printe
    public String getFormattedMessage() {
        return switch (status) {
            case NOT_FOUND -> "There is no '" + itemName + "' in your inventory to eat.";
            case NOT_FOOD -> "'" + itemName + "' is not edible!";
            // Flere linjer kode i en case kræver { }, og yield er det, casen giver tilbage
            case EATEN -> {
                String msg = "You ate the " + itemName + ". ";
                // Math.abs fjerner minusset, så der står "lost 10" og ikke "lost -10"
                if (healthGained < 0) {
                    msg += "Ouch! It was poisonous! You lost " + Math.abs(healthGained) + " HP.";
                } else {
                    msg += "You restored " + healthGained + " HP.";
                }
                msg += " (Current Health: " + newHealth + " HP)";
                if (playerDied) {
                    msg += "\nThe poison was lethal...";
                }
                yield msg;
            }
        };
    }
}
