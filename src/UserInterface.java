import java.util.ArrayList;

public class UserInterface {
    //Instansvariabel så alle metoder i klassen kan nå spillet.
    private Adventure adventure;

    //Konstruktøren får et færdigbygget Adventure udefra, fra Main, og gemmer det i feltet, Så man kan spille spillet.
    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }


    public void runGame() {
        //sætter gaming is running til at spillet kører ie. true
        boolean gameIsRunning = true;

        IO.println("Welcome to The Adventure Game! You find yourself in a darkest of dungeons ...");
        IO.println("You will have to find your way out!");
        IO.println("You now stand in the first room, with these four options, you can venture north, east, west or south ... ");
        IO.println("Write GO NORTH for north, GO EAST for east, GO WEST for west and GO SOUTH for south to choose you next move");
        IO.println("Which will it be ... ?");

        while (gameIsRunning) {

            //Brugeren kan skrive en kommando der bliver pases som et parameter til switch
            String input = IO.readln().trim();
            if (input.isEmpty()) continue;

            // Deler input op i kommando og argument (f.eks. "take" og "lamp")
            String[] parts = input.split(" ", 2);
            String kommando = parts[0].toUpperCase();
            String argument = parts.length > 1 ? parts[1] : "";
            switch (kommando) {
                case "GO NORTH", "N" -> tryMove(adventure.goNorth());
                case "GO EAST", "E" -> tryMove(adventure.goEast());
                case "GO WEST", "W" -> tryMove(adventure.goWest());
                case "GO SOUTH", "S" -> tryMove(adventure.goSouth());

                case "LOOK" -> IO.println(adventure.look());

                case "TAKE"-> handleTake(argument);
                case "DROP" -> handleDrop(argument);
                case "INVENTORY", "INV", "INVENT" -> showInventory();
                case "HELP" -> showHelp();
                }
            }
        }

    private void tryMove(boolean success) {
        if (!success) {
            IO.println("A wall is in front of you. You can't go that way.");
        } else {
            IO.println("You find yourself in ...");
            IO.println(adventure.look());
            //Listen skriver sig selv som [a, b]. Her klippes parenteserne væk, og kommaet byttes ud med et punktum
            IO.println("The room has a " + adventure.getRoomItems().toString()
                    .replace("[", "")
                    .replace("]", ""));
        }
    }

    // Udskriver rummets beskrivelse OG items i rummet
    //Som showInventory, men på rummets liste. Kaldes ikke endnu: LOOK bruger kun adventure.look()
    private void showRoomDescription() {
        IO.println(adventure.look());

        // Henter listen af items fra det nuværende rum
        var items = adventure.getRoomItems();
        if (items.isEmpty()) {
            IO.println("There are no items here.");
        } else {
            IO.println("Here you see:");
            for (Item item : items) {
                IO.println("- " + item.getLongName());
            }
        }
    }

    //Håndterer TAKE. itemName er ordet efter kommandoen, f.eks. "lamp".
    //Flytter tingen fra rummet til spilleren. null betyder at den ikke lå i rummet
    private void handleTake(String itemName) {
        if (itemName.isEmpty()) {
            IO.println("What do you want to take?");
            return;
        }

        Item item = adventure.takeItem(itemName);
        if (item != null) {
            IO.println("You have taken the " + item.getShortName());
        } else {
            IO.println("There is nothing like " + itemName + " to take around here");
        }
    }
    
    //Som handleTake, bare den anden vej: tingen lægges i rummet og kan samles op igen.
    //null betyder at spilleren ikke havde den
    private void handleDrop(String itemName) {
        if (itemName.isEmpty()) {
            IO.println("What do you want to drop?");
            return;
        }

        Item item = adventure.dropItem(itemName);
        if (item != null) {
            IO.println("You have dropped the " + item.getShortName());
        } else {
            IO.println("You don't have anything like " + itemName + " in your inventory");
        }
    }

    //Viser hvad spilleren bærer på. Den tomme liste fanges for sig,
    //ellers ville man få en overskrift uden noget under
    private void showInventory() {
        var inventory = adventure.getPlayerInventory();
        if (inventory.isEmpty()) {
            IO.println("Your inventory is empty.");
        } else {
            IO.println("You are carrying:");
            for (Item item : inventory) {
                IO.println("- " + item.getLongName());
            }
        }
    }
    private void showHelp() {
        IO.println("""
                --- COMMANDS ---
                • GO NORTH / GO EAST / GO WEST / GO SOUTH (eller N, E, W, S)
                • LOOK : Take a look around the current room you find yourself in
                • TAKE : TAKES ROOM ITEM
                • DROP : DROPS ITEM FROM INVENTORY
                • INVENTORY/ INV /INVENT(DISPLAYS INVENTORY)
                • HELP : Show this menu again if you forget your commands
                • EXIT : Exits the game
                """);
    }
}



