    // Samler alt om ét angreb i ét objekt: hvordan det gik, hvor meget skade hver side tog,
    // og om spilleren døde. Bruges ikke endnu, attack() returnerer kun AttackStatus
    public class AttackResult {
        private AttackStatus status;
        private String enemyName;
        private int damageDealt;
        private int enemyHealthRemaining;
        private int damageReceived;
        private Weapon droppedWeapon;
        private boolean playerDied;

        public AttackResult(AttackStatus status, String enemyName, int damageDealt, int enemyHealthRemaining, int damageReceived, Weapon droppedWeapon, boolean playerDied) {
            this.status = status;
            this.enemyName = enemyName;
            this.damageDealt = damageDealt;
            this.enemyHealthRemaining = enemyHealthRemaining;
            this.damageReceived = damageReceived;
            this.droppedWeapon = droppedWeapon;
            this.playerDied = playerDied;
        }

        public AttackStatus getStatus() { return status; }
        public boolean isPlayerDied() { return playerDied; }

        // Bygger beskeden til spilleren. switch med yield giver en værdi tilbage i stedet for at printe
        public String getFormattedMessage() {
            return switch (status) {
                case NO_WEAPON -> "You don't have a weapon equipped!";
                case WEAPON_OUT_OF_AMMO -> "Click! Your weapon is out of ammo or cannot be used.";
                case NO_ENEMY_SPECIFIED_AND_ROOM_EMPTY -> "You swing into thin air! There are no enemies here.";
                case ENEMY_NOT_FOUND -> "There is no enemy called '" + enemyName + "' here.";
                case SUCCESS_ENEMY_KILLED -> {
                    String msg = "You hit the " + enemyName + " for " + damageDealt + " damage.\n" +
                            "The " + enemyName + " dies!";
                    if (droppedWeapon != null) {
                        msg += " It dropped its " + droppedWeapon.getShortName() + ".";
                    }
                    yield msg;
                }
                case SUCCESS_ENEMY_SURVIVED -> {
                    String msg = "You hit the " + enemyName + " for " + damageDealt + " damage (Health left: " + enemyHealthRemaining + ").\n" +
                            "The " + enemyName + " attacks back and deals " + damageReceived + " damage to you!";
if (playerDied) {
               msg += "\nYou have succumbed to your injuries... GAME OVER!";
               }
                yield msg;
           }
       };
   }
}