public class Player {
    private Player name;
    private Room currentRoom;

    public Player(Player name, Room currentRoom){
        this.name = name;
        this.currentRoom = currentRoom;
    }

    public boolean goNorth(){
        currentRoom = currentRoom.getRoomNorth();
        return currentRoom != null;
    }

    public boolean goSouth(){
        currentRoom = currentRoom.getRoomSouth();
        return currentRoom != null;
    }

    public boolean goEast(){
        currentRoom = currentRoom.getRoomEast();
        return currentRoom != null;
    }

    public boolean goWest(){
        currentRoom = currentRoom.getRoomWest();
        return currentRoom != null;
    }

    public Room whereAreYou(){
        return currentRoom;
    }
}
