import java.util.ArrayList;

// Holder styr på hvor spilleren står, og hvad han bærer på
public class Player {
    private Room currentRoom;
    // Tom fra start. Fyldes når spilleren tager ting
    private ArrayList<Item> inventory = new ArrayList<>();

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
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
