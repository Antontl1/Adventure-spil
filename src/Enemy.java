// En fjende, der står i et rum. Ikke en Item, for man kan ikke samle den op
public class Enemy {
    // final: navne og våben ændrer sig aldrig, efter fjenden er lavet
    private final String shortName;
    private final String longName;
    private final String description;
    private int health;
    private final Weapon weapon;
    // Rummet fjenden står i. Bruges, når den dør og skal fjernes
    private Room currentRoom;
    private Player player;

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

    // Trækker skade fra fjendens liv. Når den når 0, dør den
    public void takeDamage(int amount) {
        this.health -= amount;
        if (this.health <= 0) {
            // Liv sættes til 0, så det aldrig vises som et negativt tal
            this.health = 0;
            die();
        }
    }

    // Fjenden slår spilleren med sit våben og returnerer, hvor meget skade den gjorde
    public int attack() {
        if (isAlive() && weapon != null) {
            int damage = weapon.getDamage();
            player.takeDamage(damage);
            return damage;
        }
        return 0;
    }

    // Kaldes af takeDamage. Fjenden taber sit våben i rummet og forsvinder fra rummets liste
    private void die() {
        if (currentRoom != null) {
            if (weapon != null) {
                currentRoom.addItem(weapon);
            }
            currentRoom.removeEnemy(this);
        }
    }
}
