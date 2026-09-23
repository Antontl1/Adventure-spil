//Bygger kortet og ejer spilleren. Er bindeled mellem UserInterface og resten af spillet
public class Adventure {
    private Room firstRoom;
    private Player player;

    Adventure(){
        createRoomOrder();
        //Spilleren sættes ind i det første rum, når kortet er bygget
        player = new Player(firstRoom);
    }

    public Room getFirstRoom() {
        return firstRoom;
    }

    //Prøver at flytte spilleren. Giver false hvis der ikke er noget rum den vej
    public boolean goNorth(){
        return player.goNorth();
    }

    public boolean goSouth(){
        return player.goSouth();
    }

    public boolean goEast(){
        return player.goEast();
    }

    public boolean goWest(){
        return player.goWest();
    }

    private void createRoomOrder(){
        //Opret rummene med deres navn og beskrivelse
        Room room1 = new Room("Entrance Hall", "A large room with dusty paintings on the walls.");
        Room room2 = new Room("Library", "Shelves of old books reach all the way to the ceiling.");
        Room room3 = new Room("Armoury", "Rusty swords and shields hang on the walls.");
        Room room4 = new Room("Kitchen", "There is a faint smell of burnt bread.");
        Room room5 = new Room("Throne Room", "An empty throne stands in the middle of the room.");
        Room room6 = new Room("Dungeon", "Cold stone walls and rattling chains.");
        Room room7 = new Room("Garden", "Overgrown bushes and a dried out fountain.");
        Room room8 = new Room("Hallway", "A long hallway lit by torches.");
        Room room9 = new Room("Treasure Chamber", "Gold coins glitter in the dark.");

        //Spilleren starter i det første rum
        firstRoom = room1;

        //Forbind rummene to og to. Går man øst ind i et rum, skal man kunne gå vest tilbage igen
        room1.setRoomEast(room2);
        room2.setRoomWest(room1);

        room1.setRoomSouth(room4);
        room4.setRoomNorth(room1);

        room2.setRoomEast(room3);
        room3.setRoomWest(room2);

        room3.setRoomSouth(room6);
        room6.setRoomNorth(room3);

        room4.setRoomSouth(room7);
        room7.setRoomNorth(room4);

        room5.setRoomSouth(room8);
        room8.setRoomNorth(room5);

        room6.setRoomSouth(room9);
        room9.setRoomNorth(room6);

        room7.setRoomEast(room8);
        room8.setRoomWest(room7);

        room8.setRoomEast(room9);
        room9.setRoomWest(room8);
    }

    //Spørger spilleren hvor han står, og laver rummets navn og beskrivelse om til én tekst
    public String look(){
        Room currentRoom = player.getCurrentRoom();
        return currentRoom.getName() + "\n" + currentRoom.getDescription();
    }
}
