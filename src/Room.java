public class Room {
    private Room firstRoom;
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    public Room(){
       createRoomOrder;
    }

    public Room setRoomNorth(Room room){
        return this.north = room;
    }

    public Room setRoomSouth(Room room){
        return this.south = room;
    }

    public Room setRoomEast(Room room){
        return this.east = room;
    }

    public Room setRoomWest(Room room){
        return this.west = room;
    }

    public Room getFirstRoom(){
        return firstRoom;
    }

    private void createRoomOrder(){
        Room room1 = new Room();
        Room room2 = new Room();
        Room room3 = new Room();
        Room room4 = new Room();
        Room room5 = new Room();
        Room room6 = new Room();
        Room room7 = new Room();
        Room room8 = new Room();
        Room room9 = new Room();

        firstRoom = room1;

        room1.setRoomEast(room2);
        room1.setRoomSouth(room4);
        room2.setRoomWest(room1);
        room2.setRoomEast(room3);
        room3.setRoomWest(room2);
        room3.setRoomSouth(room6);
        room4.setRoomNorth(room1);
        room4.setRoomSouth(room7);
        room5.setRoomSouth(room8);
        room6.setRoomNorth(room3);
        room6.setRoomSouth(room9);
        room7.setRoomNorth(room4);
        room7.setRoomEast(room8);
        room8.setRoomWest(room7);
        room8.setRoomNorth(room5);
        room8.setRoomEast(room9);
        room9.setRoomWest(room8);
        room9.setRoomNorth(room6);

    }
}
