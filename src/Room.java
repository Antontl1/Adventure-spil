import java.util.ArrayList;

// Et rum med navn, beskrivelse, fire mulige naboer og de ting der ligger der
public class Room {
    private String name;
    private String description;

    // null betyder at der ikke er en dør den vej
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    private ArrayList<Item> inventory = new ArrayList<>();

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

    // --- NABORUM (SETTERS & GETTERS) ---
    // Setterne bruges i Map, når kortet bygges. Getterne bruges af Player, når han går

    public void setRoomNorth(Room room) {
        this.north = room;
    }

    public Room getRoomNorth() {
        return north;
    }

    public void setRoomSouth(Room room) {
        this.south = room;
    }

    public Room getRoomSouth() {
        return south;
    }

    public void setRoomEast(Room room) {
        this.east = room;
    }

    public Room getRoomEast() {
        return east;
    }

    public void setRoomWest(Room room) {
        this.west = room;
    }

    public Room getRoomWest() {
        return west;
    }

    // --- ITEMS I RUMMET ---

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    // Finder en ting i rummet ud fra det korte navn. null hvis den ikke ligger her
    public Item findItem(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    // Hjælpemetode til pæn udskrift af rummets genstande, én ting per linje
    public String getFormattedItems() {
        if (inventory.isEmpty()) {
            return "There are no items in this room.";
        }
        StringBuilder sb = new StringBuilder("Items in this room:\n");
        for (Item item : inventory) {
            sb.append("- ").append(item.getShortName()).append(": ").append(item.getItemDescription()).append("\n");
        }
        // trim fjerner det sidste linjeskift
        return sb.toString().trim();
    }

    // Lister de retninger der har en dør, f.eks. "Exits: East, South"
    public String getExits() {
        ArrayList<String> exits = new ArrayList<>();

        if (north != null) exits.add("North");
        if (east != null)  exits.add("East");
        if (south != null) exits.add("South");
        if (west != null)  exits.add("West");

        if (exits.isEmpty()) {
            return "There are no visible exits.";
        }

        return "Exits: " + String.join(", ", exits);
    }
}
