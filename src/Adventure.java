import java.util.ArrayList;

public class Adventure {
    private Player player;

    public Adventure() {
        Map map = new Map();
        player = new Player(map.getFirstRoom());
    }

    public Item takeItem(String itemName) {
        return player.takeItem(itemName);
    }

    public Item dropItem(String itemName) {
        return player.dropItem(itemName);
    }

    public ArrayList<Item> getPlayerInventory() {
        return player.getInventory();
    }

    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }

    public boolean goNorth() {
        return player.goNorth();
    }

    public boolean goSouth() {
        return player.goSouth();
    }

    public boolean goEast() {
        return player.goEast();
    }

    public boolean goWest() {
        return player.goWest();
    }

    public String look() {
        return player.getCurrentRoom().getFullDescription();
    }
}