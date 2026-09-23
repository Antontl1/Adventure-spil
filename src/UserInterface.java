public class UserInterface {
    private Adventure adventure;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }


    public void runGame() {

        //Spilleren starter i det første rum

        boolean gameIsRunning = true;

        IO.println("Welcome to The Adventure Game!\n You find yourself in a darkest of dungeons ...");
        IO.println("You will have to find your way out!");
        IO.println("You now stand in the first room, with these four options, you can venture north, east, west or south ... ");
        IO.println("Write NORTH for north, EAST for east, WEST for west and SOUTH for south to choose you next move");
        IO.println("Which will it be ... ?");

        while (gameIsRunning) {

            String kommando = IO.readln();

            switch (kommando) {
                case "NORTH" -> {
                    if (adventure.goNorth() == false) {
                        IO.println("A wall is infront of you, you can't go that way");
                    } else {
                        adventure.goNorth();
                        IO.println("You find yourself in ...");
                        IO.println(adventure.look());
                    }
                }
                case "EAST" -> {
                    if (adventure.goEast() == false) {
                        IO.println("A wall is infront of you, you can't go that way");
                    } else {
                        adventure.goEast();
                        IO.println("You find yourself in ...");
                        IO.println(adventure.look());
                    }
                }
                case "WEST" -> {

                }
                case "SOUTH" -> {

                }
                case "EXIT" -> {
                    IO.print("Goodbye!");
                    gameIsRunning = false;
                }
                case "LOOK" -> {
                    IO.println(adventure.look());
                }
                case "HELP" -> {
                    IO.println("--- COMMANDS ---");
                    IO.println("Type NORTH, EAST, WEST or SOUTH for the direction you want to go");
                    IO.println("Type EXIT to exit the game");
                    IO.println("Type LOOK for taking look around the room you currently standing in");
                }
            }
        }

    }

}
