// Samler alt om ét angreb i ét objekt, så Player kan returnere flere værdier på én gang.
// Laves i Player.attack() og printes af UserInterface.handleAttack()
public class AttackResult {
    // Hvordan angrebet gik. Afgør hvilken besked der vises
    private AttackStatus status;
    private String enemyName;
    // Skaden spilleren gav fjenden
    private int damageDealt;
    // Fjendens liv efter slaget. 0 hvis den døde
    private int enemyHealthRemaining;
    // Skaden fjenden gav tilbage. 0 hvis den døde først
    private int damageReceived;
    // Våbnet fjenden tabte, da den døde. null hvis den overlevede eller ikke havde et
    private Weapon droppedWeapon;
    // true hvis modangrebet slog spilleren ihjel. Bruges til GAME OVER
    private boolean playerDied;
    // Spillerens liv efter angrebet
    private int healthRemainig;

    // De fejl-udfald, der stopper før slaget, sender 0 og null med i de felter, der ikke bruges
    public AttackResult(AttackStatus status, String enemyName, int damageDealt, int enemyHealthRemaining, int damageReceived, Weapon droppedWeapon, boolean playerDied, int healthRemaining) {
        this.status = status;
        this.enemyName = enemyName;
        this.damageDealt = damageDealt;
        this.enemyHealthRemaining = enemyHealthRemaining;
        this.damageReceived = damageReceived;
        this.droppedWeapon = droppedWeapon;
        this.playerDied = playerDied;
        this.healthRemainig = healthRemaining;
    }

    public AttackStatus getStatus() { return status; }
    // UserInterface spørger her, om spillet skal stoppe
    public boolean isPlayerDied() { return playerDied; }

    // Bygger teksten til spilleren ud fra status. switch giver en String tilbage i stedet for at printe
    public String getFormattedMessage() {
        return switch (status) {
            // Fejl: angrebet blev aldrig til noget
            case NO_WEAPON -> "You don't have a weapon equipped!";
            case WEAPON_OUT_OF_AMMO -> "Click! Your weapon is out of ammo or cannot be used.";
            case NO_ENEMY_SPECIFIED_AND_ROOM_EMPTY -> "You swing into thin air! There are no enemies here.";
            case ENEMY_NOT_FOUND -> "There is no enemy called '" + enemyName + "' here.";
            // Flere linjer kode i en case kræver { }, og yield er det, casen giver tilbage
            case SUCCESS_ENEMY_KILLED -> {
                String msg = "You hit the " + enemyName + " for " + damageDealt + " damage.\n" +
                        "The " + enemyName + " died!";
                if (droppedWeapon != null) {
                    msg += " It dropped its " + droppedWeapon.getShortName() + ".";
                }
                yield msg;
            }
            // Fjenden overlevede, så både dens og spillerens liv vises
            case SUCCESS_ENEMY_SURVIVED -> {
                String msg = "You hit the " + enemyName + " for " + damageDealt + " damage (Health left: " + enemyHealthRemaining + " HP).\n" +
                        "The " + enemyName + " attacks back and deals " + damageReceived + " damage to you! (Health left: " + healthRemainig + " HP)";
                if (playerDied) {
                    msg += "\nYou have succumbed to your injuries...";
                }
                yield msg;
            }
        };
    }
}
