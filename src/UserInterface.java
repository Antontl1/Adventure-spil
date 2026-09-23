public class UserInterface {
    private Adventure adventure;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }


    public void runGame() {

        boolean gameIsRunning = true;

        IO.println("Welcome to The Adventure Game!\n You find yourself in a darkest of dungeons ...");
        IO.println("You will have to find your way out!");
        IO.println("You now stand in the first room, with these four options, you can venture north, east, west or south ... ");
        IO.println("Write NORTH for north, EAST for eat, WEST for west and SOUTH for south to choose you next move");
        IO.println("Which will it be ... ?");

        while (gameIsRunning) {

            String kommando = IO.readln();

            switch (kommando) {
                case "NORTH" -> {

                }
                case "EAST" -> {

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
                    IO.print("--- COMMANDS ---");
                    IO.print("Type NORTH, EAST, WEST or SOUTH for the direction you want to go");
                    IO.print("Type exit to exit the game");
                    IO.print("Type look for taking look around the room you currently standing in");
                }
            }
        }

    }

}
