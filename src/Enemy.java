public class Enemy {
    private final String shortName;
    private final String longName;
    private final String description;
    private int health;
    private final Weapon weapon;
    private Room currentRoom;

    public Enemy(String shortName, String longName, String description, int health, Weapon weapon, Room currentRoom) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.currentRoom = currentRoom;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int amount) {
        this.health -= amount;
        if (this.health <= 0) {
            this.health = 0;
            die();
        }
    }

    public int attack(Player player) {
        if (isAlive() && weapon != null) {
            int damage = weapon.getDamage();
            player.takeDamage(damage);
            return damage;
        }
        return 0;
    }

    private void die() {
        if (currentRoom != null) {
            if (weapon != null) {
                currentRoom.addItem(weapon);
            }
            currentRoom.removeEnemy(this);
        }
    }
}