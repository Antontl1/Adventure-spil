public class UserInterface {
    private Adventure adventure;
    private Player player1;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        this.player1 = player1;
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
                case "NORTH" -> { player1.goNorth();

                }
                case "EAST" -> { player1.goEast();

                }
                case "WEST" -> { player1.goWest();

                }
                case "SOUTH" -> { player1.goSouth();

                }
                case "EXIT" -> {
                    IO.print("Goodbye!");
                    gameIsRunning = false;
                }
                case "LOOK" -> {
                    IO.println(adventure.look());
                }
                case "HELP" -> {
                    IO.print("--- COMMANDS ---");
                    IO.print("Type NORTH, EAST, WEST or SOUTH for the direction you want to go");
                    IO.print("Type EXIT to exit the game");
                    IO.print("Type LOOK for taking look around the room you currently standing in");
                }
            }
        }

    }

}
