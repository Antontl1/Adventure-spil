public class Player {
    // 1. Instance Variable (State)
    private Room currentRoom;

    // 2. Constructor
    public Player(Room startRoom) {
        this.currentRoom = startRoom;
    }

    // 3. Getter Method
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // 4. Directional Movement Methods
    public boolean goNorth() { return moveTo(currentRoom.getRoomNorth()); }
    public boolean goSouth() { return moveTo(currentRoom.getRoomSouth()); }
    public boolean goEast()  { return moveTo(currentRoom.getRoomEast());  }
    public boolean goWest()  { return moveTo(currentRoom.getRoomWest());  }

    // 5. Private Helper Method
    private boolean moveTo(Room room) {
        if (room == null) return false;
        currentRoom = room;
        return true;
    }
}