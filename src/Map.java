public class Map {

    private Room firstRoom;

    public Map() {
        createRoomOrder();
    }

    public Room getFirstRoom() {
        return firstRoom;
    }

    public void createRoomOrder() {
        // Opret rummene
        Room room1 = new Room("Entrance Hall", "A large room with dusty paintings on the walls.");
        Room room2 = new Room("Library", "Shelves of old books reach all the way to the ceiling.");
        Room room3 = new Room("Armoury", "Rusty swords and shields hang on the walls.");
        Room room4 = new Room("Kitchen", "There is a faint smell of burnt bread.");
        Room room5 = new Room("Throne Room", "An empty throne stands in the middle of the room.");
        Room room6 = new Room("Dungeon", "Cold stone walls and rattling chains.");
        Room room7 = new Room("Garden", "Overgrown bushes and a dried out fountain.");
        Room room8 = new Room("Hallway", "A long hallway lit by torches.");
        Room room9 = new Room("Treasure Chamber", "Gold coins glitter in the dark.");

        // Opret items (shortName, longName)
        Item item1 = new Item("rod", "a fireplace iron rod");
        Item item2 = new Item("bible", "a worn out copy of the Holy Bible");
        Item item3 = new Item("sword", "the large shining Royal Sword");
        Item item4 = new Item("bread", "a moldy loaf of corn bread");
        Item item5 = new Item("crown", "the shiny gold royal crown");
        Item item6 = new Item("rat", "a stinking dead rat");
        Item item7 = new Item("bottle", "a bottle of water");
        Item item8 = new Item("torch", "a small unlit torch");
        Item item9 = new Item("coins", "some worn down gold coins");

        // Tilføj items til rummene
        room1.addItem(item1);
        room2.addItem(item2);
        room3.addItem(item3);
        room4.addItem(item4);
        room5.addItem(item5);
        room6.addItem(item6);
        room7.addItem(item7);
        room8.addItem(item8);
        room9.addItem(item9);

        // Sæt startrum
        firstRoom = room1;

        // Øverste række: 1 - 2 - 3
        room1.setRoomEast(room2);
        room2.setRoomWest(room1);
        room2.setRoomEast(room3);
        room3.setRoomWest(room2);

        // Venstre side ned: 1 - 4 - 7
        room1.setRoomSouth(room4);
        room4.setRoomNorth(room1);
        room4.setRoomSouth(room7);
        room7.setRoomNorth(room4);

        // Højre side ned: 3 - 6 - 9
        room3.setRoomSouth(room6);
        room6.setRoomNorth(room3);
        room6.setRoomSouth(room9);
        room9.setRoomNorth(room6);

        // Rum 5 (midten)
        room5.setRoomSouth(room8);
        room8.setRoomNorth(room5);

        // Nederste række: 7 - 8 - 9
        room7.setRoomEast(room8);
        room8.setRoomWest(room7);
        room8.setRoomEast(room9);
        room9.setRoomWest(room8);
    }
}