public class Adventure {
    Room currentRoom;

    Adventure(){
        currentRoom = new Room("Indgangshallen", "Et stort rum med støvede malerier.");
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
