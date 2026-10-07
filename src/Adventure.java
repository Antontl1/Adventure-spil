import java.util.ArrayList;

// Ejer spilleren og er bindeled mellem UserInterface og resten af spillet
public class Adventure {
    private Player player;

    // Map bygger rummene. Spilleren sættes ind i det første
    public Adventure() {
        Map map = new Map();
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

    // Metoder til at gå en retning. Sendes videre til Player. true hvis spilleren blev flyttet
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

    // Våbenmetoder. Sendes videre til Player
    public EquipResult equipItem(String itemName) {
        return player.equipItem(itemName);
    }

    public AttackStatus attack(String enemyName) {
        return player.attack(enemyName);
    }

    // Det våben spilleren har i hånden. null hvis han ikke har equipped noget
    public Weapon getEquippedWeapon() {
        return player.getEquippedWeapon();
    }

    // Madmetoder. Sendes videre til Player
    public FoodStatus eat(String itemName){
        return player.eat(itemName);
    }

    // Bruges af UserInterface til at vise spillerens liv
    public int getHealth() {
        return player.getHealth();
    }

    // Henter rummets navn, beskrivelse, udgange og genstande
    public String look() {
        Room currentRoom = player.getCurrentRoom();
        return currentRoom.getName() + "\n" +
                currentRoom.getDescription() + "\n" +
                currentRoom.getExits() + "\n\n" +
                currentRoom.getFormattedItems();
    }

    public boolean playerIsDead() {
        return player.isDead();
    }
}