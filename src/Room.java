public class Room {
    //instansvariabeler
    //rummet skal have et navn
    private String name;
    //en beskrivelse
    private String description;
    //og fire naboer: rummene der ligger mod nord, syd, øst og vest.
    //De er null indtil Adventure sætter dem, og null betyder "ingen dør den vej"
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    //konstruktør for rum, bruges i Adventure til at oprette rum
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }
    //Metode til at få navnet på et rum, bruges i look metoden i Adventure
    public String getName() {
        return name;
    }
    //Metode til at få beskrivelse på et rum, bruges i look metoden i Adventure
    public String getDescription() {
        return description;
    }

    //Setterne bruges kun én gang, i createRoomOrder i Adventure, hvor kortet bygges.
    //Getterne bruges hver gang spilleren går: Player spørger sit nuværende rum hvem naboen er den vej, og flytter kun hvis svaret ikke er null
    public Room setRoomNorth(Room room) {
        return this.north = room;
    }

    public Room getRoomNorth() {
        return north;
    }

    public Room getRoomSouth() {
        return south;
    }

    public Room setRoomSouth(Room room) {
        return this.south = room;
    }

    public Room getRoomEast() {
        return east;
    }

    public Room setRoomEast(Room room) {
        return this.east = room;
    }

    public Room getRoomWest() {
        return west;
    }

    public Room setRoomWest(Room room) {
        return this.west = room;
    }
}
