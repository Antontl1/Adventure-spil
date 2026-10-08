import java.util.ArrayList;

// Holder styr på hvor spilleren står, og hvad han bærer på
public class Player {
    private Room currentRoom;
    private int maxHealth = 100;
    private int health = 100;
    private ArrayList<Item> inventory = new ArrayList<>();
    private Weapon equippedWeapon = null;

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int amount) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public Item findItemInInventory(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public EquipResult equipItem(String itemName) {
        Item item = findItemInInventory(itemName);

        if (item == null) {
            return EquipResult.NOT_IN_INVENTORY;
        }

        if (item instanceof Weapon weapon) {
            this.equippedWeapon = weapon;
            return EquipResult.SUCCESS;
        } else {
            return EquipResult.NOT_A_WEAPON;
        }
    }

    public AttackResult attack(String enemyName) {
        if (equippedWeapon == null) {
            return new AttackResult(AttackStatus.NO_WEAPON, enemyName, 0, 0, 0, null, false, health);
        }

        if (!equippedWeapon.canUse()) {
            return new AttackResult(AttackStatus.WEAPON_OUT_OF_AMMO, enemyName, 0, 0, 0, null, false, health);
        }

        Enemy enemy;
        if (enemyName.isEmpty()) {
            if (currentRoom.getEnemies().isEmpty()) {
                return new AttackResult(AttackStatus.NO_ENEMY_SPECIFIED_AND_ROOM_EMPTY, enemyName, 0, 0, 0, null, false, health);
            }
            enemy = currentRoom.getEnemies().get(0);
        } else {
            enemy = currentRoom.findEnemy(enemyName);
            if (enemy == null) {
                return new AttackResult(AttackStatus.ENEMY_NOT_FOUND, enemyName, 0, 0, 0, null, false, health);
            }
        }

        equippedWeapon.use();
        int damageDealt = equippedWeapon.getDamage();
        enemy.takeDamage(damageDealt);

        if (!enemy.isAlive()) {
            return new AttackResult(AttackStatus.SUCCESS_ENEMY_KILLED, enemy.getShortName(), damageDealt, 0, 0, enemy.getWeapon(), false, health);
        }

        int damageReceived = enemy.attack(this);
        boolean playerDied = (this.health <= 0);

        return new AttackResult(AttackStatus.SUCCESS_ENEMY_SURVIVED, enemy.getShortName(), damageDealt, enemy.getHealth(), damageReceived, null, playerDied, health);
    }

    public EatResult eat(String itemName) {
        Item item = findItemInInventory(itemName);
        if (item == null) {
            return new EatResult(FoodStatus.NOT_FOUND, itemName, 0, health, false);
        }

        if (item instanceof Food food) {
            int healthPoints = food.getHealthPoints();
            health += healthPoints;
            inventory.remove(food);

            if (health > maxHealth) {
                health = maxHealth;
            }

            boolean playerDied = (health <= 0);
            return new EatResult(FoodStatus.EATEN, itemName, healthPoints, health, playerDied);
        } else {
            return new EatResult(FoodStatus.NOT_FOOD, itemName, 0, health, false);
        }
    }

    public Item takeItem(String itemName) {
        Item item = currentRoom.findItem(itemName);
        if (item != null) {
            currentRoom.removeItem(item);
            inventory.add(item);
        }
        return item;
    }

    public Item dropItem(String itemName) {
        Item item = findItemInInventory(itemName);
        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
        }
        return item;
    }

    public boolean goNorth() { return moveTo(currentRoom.getRoomNorth()); }
    public boolean goSouth() { return moveTo(currentRoom.getRoomSouth()); }
    public boolean goEast()  { return moveTo(currentRoom.getRoomEast()); }
    public boolean goWest()  { return moveTo(currentRoom.getRoomWest()); }

    private boolean moveTo(Room room) {
        if (room == null) {
            return false;
        }
        currentRoom = room;
        return true;
    }
}