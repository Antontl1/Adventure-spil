public class Map {

    // Vi skal bruge firstRoom til at se, hvor brugeren starter
    private Room firstRoom;

    public Map() {
        createRoomOrder();
    }

    // GETTER: Giver Adventure adgang til startrummet
    public Room getFirstRoom() {
        return firstRoom;
    }

    public void createRoomOrder() {
        // Opret rummene med deres navn og beskrivelse
        Room room1 = new Room("Entrance Hall", "A large room with dusty paintings on the walls.");
        Room room2 = new Room("Library", "Shelves of old books reach all the way to the ceiling.");
        Room room3 = new Room("Armoury", "Rusty swords and shields hang on the walls.");
        Room room4 = new Room("Kitchen", "There is a faint smell of burnt bread.");
        Room room5 = new Room("Throne Room", "An empty throne stands in the middle of the room.");
        Room room6 = new Room("Dungeon", "Cold stone walls and rattling chains.");
        Room room7 = new Room("Garden", "Overgrown bushes and a dried out fountain.");
        Room room8 = new Room("Hallway", "A long hallway lit by torches.");
        Room room9 = new Room("Treasure Chamber", "Gold coins glitter in the dark.");

        Item item1 = new Item("iron rod", "A fireplace iron rod", "Still a little warm from the last fire"); // Entrance Hall
        Item item2 = new Item("the bible", "The Holy Bible", "It's very worn out. May the lord show me the way through these rooms"); // Library
        Item item3 = new Item("sword", "A Royal Sword", "It features a purple handle and triangular crest markings on the blade"); // Armoury
        Item item4 = new Item("bread", "A moldy corn bread", "Seems like the rats have already taken a few bites"); // Kitchen
        Item item5 = new Item("crown", "A royal crown", "It is fitted with large beautiful gem stones"); // Throne room
        Item item6 = new Item("dead rat", "A stinky dead rat", "It smells awful and is probably filled with diseases"); // Dungeon
        Item item7 = new Item("bottle of water", "A bottle of water", "Probably from the fountain before it dried out"); // Garden
        Item item8 = new Item("torch", "A small torch", "Currently it's not lit"); // Hallway
        Item item9 = new Item("coins", "a couple of gold coins", "They are quite shiny. Probably has some value to them"); // Treasure chamber

        // Nærkampsvåben: kort navn, langt navn, beskrivelse
        Weapon weapon1 = new MeleeWeapon("poker", "A heavy fireplace poker", "The tip is blackened from years in the fire"); // Entrance Hall
        Weapon weapon2 = new MeleeWeapon("dagger", "A silver letter dagger", "Hidden inside a hollowed out book. Small, but sharp"); // Library
        Weapon weapon3 = new MeleeWeapon("axe", "A rusty battle axe", "Heavy and dull, but it will still leave a mark"); // Armoury
        Weapon weapon4 = new MeleeWeapon("cleaver", "A butcher's cleaver", "Stained with something you would rather not think about"); // Kitchen
        Weapon weapon5 = new MeleeWeapon("scepter", "A golden scepter", "Made for ruling, but heavy enough to swing"); // Throne Room
        Weapon weapon6 = new MeleeWeapon("whip", "A leather whip", "Once used by the guards. It cracks loudly in the silence"); // Dungeon
        Weapon weapon8 = new MeleeWeapon("spear", "A guard's spear", "Taken from a rack on the wall. Long enough to keep enemies away"); // Hallway

        // Skydevåben: kort navn, langt navn, beskrivelse, antal skud
        Weapon weapon7 = new RangedWeapon("slingshot", "A wooden slingshot", "Pebbles from the dry fountain would make good ammo", 10); // Garden
        Weapon weapon9 = new RangedWeapon("bow", "An ornate longbow", "Decorated with gold. A quiver of arrows lies next to it", 5); // Treasure Chamber

        // Mad: kort navn, langt navn, beskrivelse, healthPoints. Negativt tal betyder gift
        Food food1 = new Food("biscuit", "A dry biscuit", "Left on a side table. Stale, but still edible", 5); // Entrance Hall
        Food food2 = new Food("tea", "A cold cup of tea", "Someone forgot it between the books long ago", 3); // Library
        Food food3 = new Food("ration", "A soldier's ration", "Salted meat wrapped in cloth. Made to last", 10); // Armoury
        Food food4 = new Food("stew", "A bowl of stew", "Still warm on the stove. Smells better than the bread", 15); // Kitchen
        Food food5 = new Food("goblet", "A goblet of wine", "Dark red and strangely bitter. Maybe that is why the throne is empty", -20); // Throne Room
        Food food6 = new Food("mushroom", "A glowing mushroom", "It grows between the stones and glows a sickly green", -10); // Dungeon
        Food food7 = new Food("apple", "A red apple", "Fallen from an overgrown tree by the fountain", 8); // Garden
        Food food8 = new Food("cheese", "A wedge of cheese", "The rats have not found it yet", 6); // Hallway
        Food food9 = new Food("cake", "A golden honey cake", "Fit for a king and hidden among the treasure", 25); // Treasure Chamber

        //tilføj items til rum
        room1.addItem(item1);
        room2.addItem(item2);
        room3.addItem(item3);
        room4.addItem(item4);
        room5.addItem(item5);
        room6.addItem(item6);
        room7.addItem(item7);
        room8.addItem(item8);
        room9.addItem(item9);

        //tilføj våben til rum
        room1.addItem(weapon1);
        room2.addItem(weapon2);
        room3.addItem(weapon3);
        room4.addItem(weapon4);
        room5.addItem(weapon5);
        room6.addItem(weapon6);
        room7.addItem(weapon7);
        room8.addItem(weapon8);
        room9.addItem(weapon9);

        //tilføj mad til rum
        room1.addItem(food1);
        room2.addItem(food2);
        room3.addItem(food3);
        room4.addItem(food4);
        room5.addItem(food5);
        room6.addItem(food6);
        room7.addItem(food7);
        room8.addItem(food8);
        room9.addItem(food9);

        // Spilleren starter i det første rum
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

        // Rum 5 ligger i midten og har kun én dør, ned til rum 8
        room5.setRoomSouth(room8);
        room8.setRoomNorth(room5);

        // Nederste række: 7 - 8 - 9
        room7.setRoomEast(room8);
        room8.setRoomWest(room7);
        room8.setRoomEast(room9);
        room9.setRoomWest(room8);
    }
}