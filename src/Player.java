import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>(); // Spillerens rygsæk

    public Player(Room startRoom) {
        this.currentRoom = startRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    // --- SØGEMETODE I INVENTORY ---
    public Item findItemInInventory(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null; // Returnerer null hvis spilleren ikke har tingen
    }

    // --- TAKE & DROP METODER ---

    // Flytter item fra rummet til spilleren
    public Item takeItem(String itemName) {
        Item item = currentRoom.findItem(itemName);
        if (item != null) {
            currentRoom.removeItem(item);
            inventory.add(item);
        }
        return item; // Returnerer objektet eller null hvis ikke fundet
    }

    // Flytter item fra spilleren til rummet
    public Item dropItem(String itemName) {
        Item item = findItemInInventory(itemName);
        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
        }
        return item; // Returnerer objektet eller null hvis ikke fundet
    }

    // --- RETNINGER ---
    public boolean goNorth() { return moveTo(currentRoom.getRoomNorth()); }
    public boolean goSouth() { return moveTo(currentRoom.getRoomSouth()); }
    public boolean goEast()  { return moveTo(currentRoom.getRoomEast());  }
    public boolean goWest()  { return moveTo(currentRoom.getRoomWest());  }

    private boolean moveTo(Room room) {
        if (room == null) return false;
        currentRoom = room;
        return true;
    }
}