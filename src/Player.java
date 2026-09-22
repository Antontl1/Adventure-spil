public class Player {
    private Room currentRoom;

    public Player(Room currentRoom){
        this.currentRoom = currentRoom;
    }

    public boolean goNorth(){
        currentRoom = currentRoom.goNorth();
        return currentRoom != null;
    }

    public boolean goSouth(){
        currentRoom = currentRoom.goSouth();
        return currentRoom != null;
    }

    public boolean goEast(){
        currentRoom = currentRoom.goEast();
        return currentRoom != null;
    }

    public boolean goWest(){
        currentRoom = currentRoom.goWest();
        return currentRoom != null;
    }
}
