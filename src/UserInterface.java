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
            String kommando = IO.readln();

            switch (kommando) {
                case "GO NORTH", "N" -> tryMove(adventure.goNorth());
                case "GO EAST", "E" -> tryMove(adventure.goEast());
                case "GO WEST", "W" -> tryMove(adventure.goWest());
                case "GO SOUTH", "S" -> tryMove(adventure.goSouth());

                case "LOOK" -> IO.println(adventure.look());

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
        }
    }
    private void showHelp() {
        IO.println("""
                --- COMMANDS ---
                • GO NORTH / GO EAST / GO WEST / GO SOUTH (eller N, E, W, S)
                • LOOK : Take a look around the current room
                • HELP : Show this menu
                • EXIT : Exit the game
                """);
    }
}



