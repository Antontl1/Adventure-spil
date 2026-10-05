import java.util.ArrayList;

public class Room {
    private String name;
    private String description;

    private Room north;
    private Room south;
    private Room east;
    private Room west;

    private ArrayList<Item> inventory = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setRoomNorth(Room room) { this.north = room; }
    public Room getRoomNorth() { return north; }

    public void setRoomSouth(Room room) { this.south = room; }
    public Room getRoomSouth() { return south; }

    public void setRoomEast(Room room) { this.east = room; }
    public Room getRoomEast() { return east; }

    public void setRoomWest(Room room) { this.west = room; }
    public Room getRoomWest() { return west; }

    // --- ITEMS ---
    public ArrayList<Item> getInventory() { return inventory; }
    public void addItem(Item item) { inventory.add(item); }
    public void removeItem(Item item) { inventory.remove(item); }

    public Item findItem(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    // --- ENEMIES ---
    public ArrayList<Enemy> getEnemies() { return enemies; }
    public void addEnemy(Enemy enemy) { enemies.add(enemy); }
    public void removeEnemy(Enemy enemy) { enemies.remove(enemy); }

    public Enemy findEnemy(String enemyName) {
        for (Enemy enemy : enemies) {
            if (enemy.getShortName().equalsIgnoreCase(enemyName)) {
                return enemy;
            }
        }
        return null;
    }

    public String getExits() {
        ArrayList<String> exits = new ArrayList<>();
        if (north != null) exits.add("North");
        if (east != null)  exits.add("East");
        if (south != null) exits.add("South");
        if (west != null)  exits.add("West");

        return exits.isEmpty() ? "No exits" : "Exits: " + String.join(", ", exits);
    }

    public String getFormattedItems() {
        if (inventory.isEmpty()) return "Here you see: nothing of interest.";
        StringBuilder sb = new StringBuilder("Here you see: ");
        for (int i = 0; i < inventory.size(); i++) {
            sb.append(inventory.get(i).getShortName());
            if (i < inventory.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    public String getFormattedEnemies() {
        if (enemies.isEmpty()) return "";
        StringBuilder sb = new StringBuilder("Beware! Here lurks: ");
        for (int i = 0; i < enemies.size(); i++) {
            sb.append(enemies.get(i).getLongName());
            if (i < enemies.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }
}