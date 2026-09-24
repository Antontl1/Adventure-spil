public class Map {

    //Vi skal bruge firstRoom, til at se hvor brugeren starter
    Room firstRoom;

    public void createRoomOrder(){
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

        //Forbinder rummene to og to. Eks. går man øst ind i et rum, skal man kunne gå vest tilbage igen.
        //Det er de her forbindelser spillet slår op i, når spilleren skriver en retning:
        //Hvis brugeren indtaster "EAST" -> kalder adventure.goEast() -> player.goEast()
        //-> som spørger sit nuværende rum getRoomEast(). Svaret er præcis det rum, der blev sat herunder.

        //Øverste række: 1 - 2 - 3
        room1.setRoomEast(room2);
        room2.setRoomWest(room1);
        room2.setRoomEast(room3);
        room3.setRoomWest(room2);

        //Venstre side ned: 1 - 4 - 7
        room1.setRoomSouth(room4);
        room4.setRoomNorth(room1);
        room4.setRoomSouth(room7);
        room7.setRoomNorth(room4);

        //Højre side ned: 3 - 6 - 9
        room3.setRoomSouth(room6);
        room6.setRoomNorth(room3);
        room6.setRoomSouth(room9);
        room9.setRoomNorth(room6);

        //Rum 5 ligger i midten og har kun én dør, ned til rum 8
        room5.setRoomSouth(room8);
        room8.setRoomNorth(room5);

        //Nederste række: 7 - 8 - 9
        room7.setRoomEast(room8);
        room8.setRoomWest(room7);
        room8.setRoomEast(room9);
        room9.setRoomWest(room8);
    }

    public Map(){
        createRoomOrder();
    }
}
