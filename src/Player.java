//Holder styr på hvilket rum spilleren står i, og flytter ham rundt
public class Player {
    //Instansvariabler
    //Skal være et sted
    private Room currentRoom;

    //Konstruktør til at oprette spiller
    public Player(Room currentRoom){
        this.currentRoom = currentRoom;
    }
    //Metode til at få det rum hvor spilleren er
    public Room getCurrentRoom(){
        return currentRoom;
    }

    //Prøver at flytte spilleren. De kaldes fra goXXX() i Adventure, når spilleren har skrevet en retning.
    //getRoomXXX() spørger det rum spilleren står i lige nu, hvem naboen er den vej.
    //Svaret er enten et rum eller null, og moveTo() nedenfor afgør hvad der så skal ske.
    //retningen tages fra currentRoom, så det samme kald giver et nyt svar for hvert rum man går igennem
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
