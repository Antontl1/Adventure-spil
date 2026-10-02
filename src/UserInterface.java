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
            // toUpperCase gør, at både "look" og "LOOK" virker
            String command = parts[0].toUpperCase();
            // Har brugeren kun skrevet ét ord, er der ingen argument, og så bruges en tom tekst
            String argument = parts.length > 1 ? parts[1] : "";

            switch (command) {
                // En retning skrevet alene, f.eks. "N", sendes videre som om der stod "GO N"
                case "N", "NORTH", "S", "SOUTH", "E", "EAST", "W", "WEST" -> handleGo(command);
                case "GO" -> handleGo(argument);

                case "LOOK" -> IO.println(adventure.look());
                case "TAKE" -> handleTake(argument);
                case "DROP" -> handleDrop(argument);

                case "EQUIP" -> handleEquip(argument);
                case "ATTACK" -> handleAttack();
                case "EAT" -> handleEat(argument);

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
        AttackStatus status = adventure.attack();
        Weapon weapon = adventure.getEquippedWeapon();

        switch (status) {
            case NO_WEAPON_EQUIPPED -> IO.println("You don't have a weapon equipped!");
            case OUT_OF_AMMO -> IO.println("*Click...* " + weapon.getTheLongName() + " is out of ammunition.");
            case SUCCESS -> {
                int remainingUses = weapon.getRemainingUses();

                if (remainingUses == -1) {
                    IO.println("You swing " + weapon.getLongName() + " at the empty air.");
                } else {
                    IO.println("You fired " + weapon.getTheLongName() + " into the empty air. You have " + remainingUses + " shots left.");
                }
            }
        }
    }

    // Håndterer EAT <mad>
    public void handleEat(String itemName) {
        if (itemName.isEmpty()) {
            IO.println("What do you want to eat?");
            return;
        }

        int healthBefore = adventure.getHealth();
        FoodStatus status = adventure.eat(itemName);

        switch (status) {
            case NOT_FOUND -> IO.println("You don't have '" + itemName + "' in your inventory.");
            case NOT_FOOD -> IO.println("The '" + itemName + "' is not edible!");
            case EATEN -> {
                int currentHealth = adventure.getHealth();
                int difference = currentHealth - healthBefore;
                if (difference > 0) {
                    IO.println("You eat the " + itemName + " and restore " + difference + " HP!");
                } else if (difference < 0) {
                    IO.println("Ouch! The " + itemName + " was bad or poisonous and dealt " + Math.abs(difference) + " damage!");
                } else {
                    IO.println("You eat the " + itemName + ", but feel no change in health.");
                }
                IO.println("Your current health is now " + currentHealth + " HP");
            }
        }
    }

    // Vis spillerens nuværende helbred
    private void handleHealth() {
        IO.println("Current health: " + adventure.getHealth() + " HP");
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

        Weapon equipped = adventure.getEquippedWeapon();
        if (equipped != null) {
            IO.println("Equipped weapon: " + equipped.getLongName());
        } else {
            IO.println("Equipped weapon: None");
        }
    }

    // Dybdegående oversigt over kommandoer og eksempler
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
                  • ATTACK
                    Use your currently equipped weapon.
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