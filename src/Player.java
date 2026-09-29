import java.util.ArrayList;

// Holder styr på hvor spilleren står, og hvad han bærer på
public class Player {
    private Room currentRoom;
    int health = 100;
    // Tom fra start. Fyldes når spilleren tager ting
    private ArrayList<Item> inventory = new ArrayList<>();
    // Våbnet spilleren har i hånden. null indtil han equipper et
    private Weapon equippedWeapon = null;

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public  Food getEquippedFood (){
        return getEquippedFood;
    }

    // Finder en ting spilleren bærer på ud fra det korte navn. null hvis han ikke har den
    public Item findItemInInventory(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
// --- EQUIP & ATTACK ---

    // Prøver at equippe en ting fra inventory
    // Tingen bliver i inventory. equippedWeapon peger bare på den
    public EquipResult equipItem(String itemName) {
        Item item = findItemInInventory(itemName);

        if (item == null) {
            return EquipResult.NOT_IN_INVENTORY;
        }

        // instanceof tjekker om tingen er et våben. (Weapon) fortæller Java, at den må behandles som et
        if (item instanceof Weapon) {
            this.equippedWeapon = (Weapon) item;
            return EquipResult.SUCCESS;
        } else {
            return EquipResult.NOT_A_WEAPON;
        }
    }

    // --- EQUIP AND EAT ---


    // Angriber ud i luften med det equippede våben
    // Ved ikke om det er nærkamp eller skydevåben. canUse() og use() svarer forskelligt alt efter subklassen
    public AttackResult attack() {
        if (equippedWeapon == null) {
            return new AttackResult(AttackStatus.NO_WEAPON_EQUIPPED, null, 0);
        }

        if (!equippedWeapon.canUse()) {
            return new AttackResult(AttackStatus.OUT_OF_AMMO, equippedWeapon, 0);
        }

        int remainingUses = equippedWeapon.use();
        return new AttackResult(AttackStatus.SUCCESS, equippedWeapon, remainingUses);
    }

    // --- TAKE & DROP ---

    // Flytter en ting fra rummet til spilleren. null hvis den ikke lå i rummet
    public Item takeItem(String itemName) {
        Item item = currentRoom.findItem(itemName);
        if (item != null) {
            currentRoom.removeItem(item);
            inventory.add(item);
        }
        return item;
    }

    // Flytter en ting fra spilleren til rummet. null hvis spilleren ikke havde den
    public Item dropItem(String itemName) {
        Item item = findItemInInventory(itemName);
        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
        }
        return item;
    }

    // --- BEVÆGELSE ---

    // Spørger det nuværende rum om naboen i den retning. true hvis spilleren blev flyttet
    public boolean goNorth() {
        return moveTo(currentRoom.getRoomNorth());
    }

    public boolean goSouth() {
        return moveTo(currentRoom.getRoomSouth());
    }

    public boolean goEast() {
        return moveTo(currentRoom.getRoomEast());
    }

    public boolean goWest() {
        return moveTo(currentRoom.getRoomWest());
    }

    // null betyder en væg, så spilleren bliver stående
    private boolean moveTo(Room room) {
        if (room == null) {
            return false;
        }
        currentRoom = room;
        return true;
    }
}
