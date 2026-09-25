import java.util.ArrayList;

public class Player {
    private Room currentRoom;
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

    public Item findItemInInventory(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    // --- TAKE & DROP ---

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

    // --- BEVÆGELSE ---

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

    private boolean moveTo(Room room) {
        if (room == null) {
            return false;
        }
        currentRoom = room;
        return true;
    }
}