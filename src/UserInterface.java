import java.util.ArrayList;

// Snakker med brugeren: læser kommandoer og printer svar. Kender kun Adventure
public class UserInterface {
    private final Adventure adventure;

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
                case "N", "NORTH", "S", "SOUTH", "E", "EAST", "W", "WEST" -> handleGo(command);
                case "GO" -> handleGo(argument);

                case "LOOK" -> IO.println(adventure.look());
                case "TAKE" -> handleTake(argument);
                case "DROP" -> handleDrop(argument);

                case "EQUIP" -> handleEquip(argument);
                case "ATTACK" -> {
                    boolean alive = handleAttack(argument);
                    if (!alive) {
                        gameIsRunning = false;
                    }
                }
                case "EAT" -> {
                    boolean alive = handleEat(argument);
                    if (!alive) {
                        gameIsRunning = false;
                    }
                }

                case "HEALTH", "HP" -> handleHealth();
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

    private void tryMove(boolean success) {
        if (!success) {
            IO.println("A solid wall blocks your path. You cannot go that way.");
        } else {
            IO.println("You move to a new area:\n");
            IO.println(adventure.look());
        }
    }

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

    private boolean handleAttack(String enemyName) {
        AttackResult result = adventure.attack(enemyName);
        IO.println(result.getFormattedMessage());

        if (result.isPlayerDied()) {
            IO.println("\n*** YOU HAVE DIED! GAME OVER ***");
            return false;
        }
        return true;
    }

    private boolean handleEat(String itemName) {
        if (itemName.isEmpty()) {
            IO.println("What do you want to eat?");
            return true;
        }

        EatResult result = adventure.eat(itemName);
        IO.println(result.getFormattedMessage());

        if (result.isPlayerDied()) {
            IO.println("\n*** YOU DIED FROM POISONING! GAME OVER ***");
            return false;
        }
        return true;
    }

    private void handleHealth() {
        IO.println("Current health: " + adventure.getHealth() + " HP");
    }

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

        Weapon equipped = adventure.getEquippedWeapon();
        if (equipped != null) {
            IO.println("Equipped weapon: " + equipped.getLongName());
        } else {
            IO.println("Equipped weapon: None");
        }
    }

    private void showHelp() {
        IO.println("""
                =================================== GAME HELP & COMMANDS ===================================
                
                MOVEMENT:
                  • GO <DIRECTION> | N, S, E, W
                    Move north, south, east, or west between rooms.
                    Example: 'GO NORTH' or simply 'N'
                
                ENVIRONMENT & ITEMS:
                  • LOOK
                    Re-examine your current room to see its description and items lying on the floor.
                  • TAKE <ITEM>
                    Pick up an item from the current room and put it in your inventory.
                    Example: 'TAKE iron rod'
                  • DROP <ITEM>
                    Drop an item from your inventory into the current room.
                    Example: 'DROP iron rod'
                
                COMBAT & SURVIVAL:
                  • EQUIP <WEAPON>
                    Equip a weapon from your inventory to prepare it for combat.
                    Example: 'EQUIP sword'
                  • ATTACK [ENEMY]
                    Use your equipped weapon against a specified enemy, or the first enemy in the room.
                  • EAT <FOOD>
                    Eat a consumable item from your inventory to recover HP (beware of toxic food!).
                    Example: 'EAT apple'
                  • HEALTH | HP
                    Check your current health status and HP remaining.
                
                PLAYER STATUS & SYSTEM:
                  • INVENTORY | INV
                    Display all items in your possession and your currently equipped weapon.
                  • HELP
                    Display this help overview.
                  • EXIT
                    Quit the game.
                ===========================================================================================
                """);
    }
}