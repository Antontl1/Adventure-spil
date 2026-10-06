import java.util.ArrayList;

// Holder styr på hvor spilleren står, og hvad han bærer på
public class Player {
    private Player player;
    private Room currentRoom;
    // Spillerens liv. Starter på 100 og ændres, når han spiser
    int maxHealth = 100;
    int health = 100;
    // Tom fra start. Fyldes når spilleren tager ting
    private ArrayList<Item> inventory = new ArrayList<>();
    // Våbnet spilleren har i hånden. null indtil han equipper et
    private Weapon equippedWeapon = null;

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
        this.player = player;
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

    public void takeDamage(Enemy enemy){
        health = health - enemy.getWeapon().weaponPower;
    }

    // Finder en ting spilleren bærer på ud fra det korte navn. null hvis han ikke har den
    public Item findItemInInventory(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
// --- EQUIP & ATTACK ---

    // Prøver at equippe en ting fra inventory
    // Tingen bliver i inventory. equippedWeapon peger bare på den
    public EquipResult equipItem(String itemName) {
        Item item = findItemInInventory(itemName);

        if (item == null) {
            return EquipResult.NOT_IN_INVENTORY;
        }

        // instanceof tjekker om tingen er et våben. (Weapon) fortæller Java, at den må behandles som et
        if (item instanceof Weapon) {
            this.equippedWeapon = (Weapon) item;
            return EquipResult.SUCCESS;
        } else {
            return EquipResult.NOT_A_WEAPON;
        }
    }

    // Angriber en fjende i rummet. Uden navn rammes den første fjende i rummet
    // Ved ikke om det er nærkamp eller skydevåben. canUse() og use() svarer forskelligt alt efter subklassen
    public AttackStatus attack(String enemyName) {
        if (equippedWeapon == null) {
            return AttackStatus.NO_WEAPON;
        }

        if (!equippedWeapon.canUse()) {
            return AttackStatus.WEAPON_OUT_OF_AMMO;
        }

        // Find fjenden først, så der ikke bruges et skud, hvis der ikke er noget at ramme
        Enemy enemy;
        if (enemyName.isEmpty()) {
            if (currentRoom.getEnemies().isEmpty()) {
                return AttackStatus.NO_ENEMY_SPECIFIED_AND_ROOM_EMPTY;
            }
            enemy = currentRoom.getEnemies().get(0);
        } else {
            enemy = currentRoom.findEnemy(enemyName);
            if (enemy == null) {
                return AttackStatus.ENEMY_NOT_FOUND;
            }
        }

        // use() tæller et skud ned på skydevåben. Nærkampsvåben gør ingenting
        equippedWeapon.use();
        // takeDamage fjerner selv fjenden fra rummet og taber dens våben, hvis den dør
        enemy.takeDamage(equippedWeapon.getDamage());

        if (!enemy.isAlive()) {
            return AttackStatus.SUCCESS_ENEMY_KILLED;
        }

        // Fjenden overlevede og slår igen
        enemy.attack(Player player, Enemy enemyName);
        return AttackStatus.SUCCESS_ENEMY_SURVIVED;
    }

    // --- EAT ---

    // Spiser en ting fra inventory. Svaret fortæller UserInterface, hvordan det gik
    public FoodStatus eat(String itemName) {
        // Man kan kun spise noget, man bærer på
        Item item = findItemInInventory(itemName);
        if (item == null) {
            return FoodStatus.NOT_FOUND;
        }
        // instanceof tjekker om tingen er mad, så casten nedenfor ikke crasher
        if (item instanceof Food) {
            // Casten giver adgang til getHealthPoints(), som kun Food har
            Food food = (Food) item;
            // Negative healthPoints (gift) trækker automatisk fra
            health += food.getHealthPoints();
            // Maden er spist, så den forsvinder fra inventory
            inventory.remove(food);
            return FoodStatus.EATEN;
        } else {
            return FoodStatus.NOT_FOOD;
        }
    }

    // --- TAKE & DROP ---

    // Flytter en ting fra rummet til spilleren. null hvis den ikke lå i rummet
    public Item takeItem(String itemName) {
        Item item = currentRoom.findItem(itemName);
        if (item != null) {
            currentRoom.removeItem(item);
            inventory.add(item);
        }
        return item;
    }


    // Flytter en ting fra spilleren til rummet. null hvis spilleren ikke havde den
    public Item dropItem(String itemName) {
        Item item = findItemInInventory(itemName);
        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
        }
        return item;
    }

    // --- BEVÆGELSE ---

    // Spørger det nuværende rum om naboen i den retning. true hvis spilleren blev flyttet
    public boolean goNorth() {
        return moveTo(currentRoom.getRoomNorth());
    }

    public boolean goSouth() {
        return moveTo(currentRoom.getRoomSouth());
    }

    public boolean goEast() {
        return moveTo(currentRoom.getRoomEast());
    }

    public boolean goWest() {
        return moveTo(currentRoom.getRoomWest());
    }

    // null betyder en væg, så spilleren bliver stående
    private boolean moveTo(Room room) {
        if (room == null) {
            return false;
        }
        currentRoom = room;
        return true;
    }
}
