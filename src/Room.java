//Et enkelt rum. Kender sit eget navn og beskrivelse,
//og hvilke rum der ligger mod nord, syd, øst og vest
public class Room {
    private String name;
    private String description;
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    public Room(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Room getRoomNorth(){
        return  north;
    }

    public Room setRoomNorth(Room room){
        return this.north = room;
    }

    public Room getRoomSouth() {
        return south;
    }

    public Room setRoomSouth(Room room){
        return this.south = room;
    }

    public Room getRoomEast() {
        return east;
    }

    public Room setRoomEast(Room room){
        return this.east = room;
    }

    public Room getRoomWest() {
        return west;
    }

    public Room setRoomWest(Room room){
        return this.west = room;
    }
}
