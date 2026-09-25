import java.util.ArrayList;

//Bygger kortet og ejer spilleren. Er bindeled mellem UserInterface og resten af spillet
public class Adventure {
    //Vi skal bruge player, for at kalde diverse metoder på den.
    private Player player;

    public Adventure(){
        Map map1 = new Map();
        //Spilleren sættes ind i det første rum, når kortet er bygget
        player = new Player(map1.firstRoom);
    }

    // Beder player om at tage en ting fra det nuværende rum
    public Item takeItem(String itemName) {
        return player.takeItem(itemName);
    }

    // Beder player om at smide en ting i det nuværende rum
    public Item dropItem(String itemName) {
        return player.dropItem(itemName);
    }

    // Henter listen af ting som spilleren bærer på
    public ArrayList<Item> getPlayerInventory() {
        return player.getInventory();
    }

    // Henter listen af ting der ligger i det rum spilleren står i
    public ArrayList<Item> getRoomItems() {
        return player.getCurrentRoom().getItems();
    }

    //Metoder til at gå en retning. De laver ikke selv arbejdet, men sender beskeden videre
    //til goXXX() i Player, som er den der kender og flytter spillerens nuværende rum.
    //Svaret er true hvis spilleren blev flyttet, og false hvis der var en væg.
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

    //Spørger spilleren hvor han står, og laver rummets navn og beskrivelse om til én tekst
    public String look(){
        Room currentRoom = player.getCurrentRoom();
        return currentRoom.getName() + "\n" + currentRoom.getDescription();
    }

}