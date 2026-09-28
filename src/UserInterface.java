import java.util.ArrayList;

// Snakker med brugeren: læser kommandoer og printer svar. Kender kun Adventure
public class UserInterface {
    private Adventure adventure;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }

    // Spillets løkke. Kører indtil brugeren skriver EXIT
    public void runGame() {
        boolean gameIsRunning = true;

        IO.println("Welcome to The Adventure Game! You find yourself in the darkest of dungeons ...");
        IO.println("Type HELP to see available commands.\n");
        IO.println(adventure.look());

        while (gameIsRunning) {
            IO.print("\n> ");
            String input = IO.readln().trim();
            // Tom linje: spring over og spørg igen
            if (input.isEmpty()) continue;

            // Deler input i kommando og resten, f.eks. "TAKE" og "iron rod"
            String[] parts = input.split(" ", 2);
            String command = parts[0].toUpperCase();
            String argument = parts.length > 1 ? parts[1] : "";

            switch (command) {
                case "N", "NORTH" -> tryMove(adventure.goNorth());
                case "S", "SOUTH" -> tryMove(adventure.goSouth());
                case "E", "EAST" -> tryMove(adventure.goEast());
                case "W", "WEST" -> tryMove(adventure.goWest());
                case "GO" -> handleGo(argument);

                case "LOOK" -> IO.println(adventure.look());
                case "TAKE" -> handleTake(argument);
                case "DROP" -> handleDrop(argument);

                case "EQUIP" -> handleEquip(argument);
                case "ATTACK" -> handleAttack();

                case "INVENTORY", "INV", "INVENT" -> showInventory();
                case "HELP" -> showHelp();
                case "EXIT" -> {
                    IO.println("Thank you for playing!");
                    gameIsRunning = false;
                }
                default -> IO.println("Unknown command. Type HELP for guidance.");
            }
        }
    }

    // Håndterer GO NORTH osv. Retningen er ordet efter GO
    private void handleGo(String direction) {
        if (direction.isEmpty()) {
            IO.println("Go where? (e.g. GO NORTH)");
            return;
        }

        switch (direction.toUpperCase()) {
            case "N", "NORTH" -> tryMove(adventure.goNorth());
            case "S", "SOUTH" -> tryMove(adventure.goSouth());
            case "E", "EAST" -> tryMove(adventure.goEast());
            case "W", "WEST" -> tryMove(adventure.goWest());
            default -> IO.println("Invalid direction! Use NORTH, SOUTH, EAST, or WEST.");
        }
    }

    // Printer svaret på et forsøg på at gå. false betyder en væg
    private void tryMove(boolean success) {
        if (!success) {
            IO.println("A solid wall blocks your path. You cannot go that way.");
        } else {
            IO.println("You move to a new area:\n");
            IO.println(adventure.look());
        }
    }

    // Håndterer TAKE. null betyder at tingen ikke lå i rummet
    private void handleTake(String itemName) {
        if (itemName.isEmpty()) {
            IO.println("What do you want to take?");
            return;
        }

        Item item = adventure.takeItem(itemName);
        if (item != null) {
            IO.println("You picked up: " + item.getShortName());
        } else {
            IO.println("There is no '" + itemName + "' here to take.");
        }
    }

    // Håndterer DROP. null betyder at spilleren ikke havde tingen
    private void handleDrop(String itemName) {
        if (itemName.isEmpty()) {
            IO.println("What do you want to drop?");
            return;
        }

        Item item = adventure.dropItem(itemName);
        if (item != null) {
            IO.println("You dropped: " + item.getShortName());
        } else {
            IO.println("You are not carrying any '" + itemName + "'.");
        }
    }

    // Håndterer EQUIP <våben>
    private void handleEquip(String itemName) {
        if (itemName.isEmpty()) {
            IO.println("What do you want to equip?");
            return;
        }

        EquipResult result = adventure.equipItem(itemName);
        switch (result) {
            case NOT_IN_INVENTORY -> IO.println("You do not have a '" + itemName + "' in your inventory.");
            case NOT_A_WEAPON -> IO.println("The " + itemName + " is not a weapon.");
            case SUCCESS -> {
                Weapon equipped = adventure.getEquippedWeapon();
                IO.println("You have equipped " + equipped.getLongName());
            }
        }
    }

    // Håndterer ATTACK
    private void handleAttack() {
        AttackResult result = adventure.attack();

        switch (result.getStatus()) {
            case NO_WEAPON_EQUIPPED -> IO.println("You don't have a weapon equipped!");
            case OUT_OF_AMMO -> IO.println("Click... The " + result.getWeapon().getLongName() + " is out of ammunition.");
            case SUCCESS -> {
                Weapon weapon = result.getWeapon();
                int remainingUses = result.getRemainingUses();

                if (remainingUses == -1) {
                    // MeleeWeapon (ubegrænset brug)
                    IO.println("You swing " + weapon.getLongName() + " at the empty air.");
                } else {
                    // RangedWeapon (begrænset skud)
                    IO.println("You fire " + weapon.getLongName() + " into the empty air. " + remainingUses + " shots left.");
                }
            }
        }
    }

    // Viser hvad spilleren bærer på og hvilket våben der er equipped
    private void showInventory() {
        ArrayList<Item> inventory = adventure.getPlayerInventory();
        if (inventory.isEmpty()) {
            IO.println("Your inventory is empty.");
        } else {
            IO.println("You are carrying:");
            for (Item item : inventory) {
                IO.println("- " + item.getShortName() + ": " + item.getLongName());
            }
        }

        // Viser equipped våben
        Weapon equipped = adventure.getEquippedWeapon();
        if (equipped != null) {
            IO.println("Equipped: " + equipped.getLongName());
        } else {
            IO.println("Equipped: none");
        }
    }

    private void showHelp() {
        IO.println("""
                --- COMMANDS ---
                • GO <DIRECTION> / N, S, E, W : Move around the map
                • LOOK                        : Inspect the current room and items
                • TAKE <ITEM>                 : Take an item from the room this works like take itemname eg. take iron rod
                • DROP <ITEM>                 : Drop an item from your inventory works the same as take
                • EQUIP <WEAPON>              : Equip a weapon from your inventory
                • ATTACK                      : Use your equipped weapon
                • INVENTORY / INV             : View carried items and equipped weapon
                • HELP                        : Show this menu
                • EXIT                        : Quit the game
                """);
    }
}