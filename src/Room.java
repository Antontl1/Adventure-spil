import java.util.ArrayList;

// Et rum med navn, beskrivelse, fire mulige naboer, de ting der ligger der og de fjender der står der
public class Room {
    private final String name;
    private final String description;

    // null betyder at der ikke er en dør den vej
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    // Ting og fjender har hver sin liste, så TAKE aldrig kan samle en fjende op
    private final ArrayList<Item> inventory = new ArrayList<>();
    private final ArrayList<Enemy> enemies = new ArrayList<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    // --- NABORUM ---
    // Setterne bruges i Map, når kortet bygges. Getterne bruges af Player, når han går
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

    // Finder en ting i rummet ud fra det korte navn. null hvis den ikke ligger her
    public Item findItem(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    // --- ENEMIES ---
    // addEnemy bruges i Map. removeEnemy bruges af Enemy selv, når den dør
    public ArrayList<Enemy> getEnemies() { return enemies; }
    public void addEnemy(Enemy enemy) { enemies.add(enemy); }
    public void removeEnemy(Enemy enemy) { enemies.remove(enemy); }

    // Finder en fjende i rummet ud fra det korte navn, f.eks. "goblin". null hvis den ikke er her
    public Enemy findEnemy(String enemyName) {
        for (Enemy enemy : enemies) {
            if (enemy.getShortName().equalsIgnoreCase(enemyName)) {
                return enemy;
            }
        }
        return null;
    }

    // Lister de retninger der har en dør, f.eks. "Exits: East, South"
    public String getExits() {
        ArrayList<String> exits = new ArrayList<>();
        if (north != null) exits.add("North");
        if (east != null)  exits.add("East");
        if (south != null) exits.add("South");
        if (west != null)  exits.add("West");

        return exits.isEmpty() ? "No exits" : "Exits: " + String.join(", ", exits);
    }

    // Lister tingenes korte navne på én linje med komma imellem
    public String getFormattedItems() {
        if (inventory.isEmpty()) return "Here you see: nothing of interest.";
        StringBuilder sb = new StringBuilder("In this room there is: a ");
        for (int i = 0; i < inventory.size(); i++) {
            sb.append(inventory.get(i).getShortName());
            // Komma efter alle undtagen den sidste
            if (i < inventory.size() - 2) sb.append(", a ");
            else if (i == inventory.size() - 2) sb.append(", and a ");
        }
        return sb.toString();
    }

    // Som getFormattedItems, men for fjender. Tom tekst hvis rummet er fjendefrit
    public String getFormattedEnemies() {
        if (enemies.isEmpty()) return "";
        StringBuilder sb = new StringBuilder("Beware! Here lurks: ");
        for (int i = 0; i < enemies.size(); i++) {
            sb.append(enemies.get(i).getLongName() + "\n" + enemies.get(i).getDescription());
            if (i < enemies.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }
}
