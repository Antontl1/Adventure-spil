public class EatResult {
    private FoodStatus status;
    private String itemName;
    private int healthGained;
    private int newHealth;
    private boolean playerDied;

    public EatResult(FoodStatus status, String itemName, int healthGained, int newHealth, boolean playerDied) {
        this.status = status;
        this.itemName = itemName;
        this.healthGained = healthGained;
        this.newHealth = newHealth;
        this.playerDied = playerDied;
    }

    public FoodStatus getStatus() { return status; }
    public boolean isPlayerDied() { return playerDied; }

    public String getFormattedMessage() {
        return switch (status) {
            case NOT_FOUND -> "There is no '" + itemName + "' in your inventory to eat.";
            case NOT_FOOD -> "'" + itemName + "' is not edible!";
            case EATEN -> {
                String msg = "You ate the " + itemName + ". ";
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