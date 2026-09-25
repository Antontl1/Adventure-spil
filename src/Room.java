import java.util.ArrayList;

public class Room {
    private String name;
    private String description;

    // Directional connections (null means no door)
    private Room north, south, east, west;

    // List of items currently lying in this room
    private ArrayList<Item> items = new ArrayList<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // --- ITEM METHODS ---

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    // Search for an item by its short name
    public Item findItem(String itemName) {
        for (Item item : items) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null; // Item not found
    }

    // --- ROOM INFORMATION ---

    public String getName() { return name; }
    public String getDescription() { return description; }

    // --- NAVIGATION GETTERS & SETTERS ---

    public Room getRoomNorth() { return north; }
    public void setRoomNorth(Room room) { this.north = room; }

    public Room getRoomSouth() { return south; }
    public void setRoomSouth(Room room) { this.south = room; }

    public Room getRoomEast() { return east; }
    public void setRoomEast(Room room) { this.east = room; }

    public Room getRoomWest() { return west; }
    public void setRoomWest(Room room) { this.west = room; }
}