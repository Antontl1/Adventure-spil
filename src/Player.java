//Holder styr på hvilket rum spilleren står i, og flytter ham rundt
public class Player {
    private Player name;
    private Room currentRoom;

    public Player(Room currentRoom){
        this.name = name;
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }

    //Prøver at flytte spilleren. Giver false hvis der ikke er noget rum den vej
    public boolean goNorth(){
        return moveTo(currentRoom.getRoomNorth());
    }

    public boolean goSouth(){
        return moveTo(currentRoom.getRoomSouth());
    }

    public boolean goEast(){
        return moveTo(currentRoom.getRoomEast());
    }

    public boolean goWest(){
        return moveTo(currentRoom.getRoomWest());
    }

    //Flytter kun spilleren hvis rummet findes. Ellers bliver han stående
    private boolean moveTo(Room room){
        if (room == null) {
            return false;
        }
        currentRoom = room;
        return true;
    }
}
