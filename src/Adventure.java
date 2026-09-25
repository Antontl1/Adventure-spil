import java.util.ArrayList;

//Bygger kortet og ejer spilleren. Er bindeled mellem UserInterface og resten af spillet
public class Adventure {
    private Player player;

    public Adventure(){
        Map map = new Map();
        // Rettet til at bruge getter fra Map
        player = new Player(map.getFirstRoom());
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
        return player.getCurrentRoom().getInventory();
    }

    // Metoder til at gå en retning
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

    // Spørger spilleren hvor han står
    public String look(){
        Room currentRoom = player.getCurrentRoom();
        return currentRoom.getName() + "\n" + currentRoom.getDescription();
    }
}