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

        IO.println("Welcome to The Adventure Game!\n You find yourself in a darkest of dungeons ...");
        IO.println("You will have to find your way out!");
        IO.println("You now stand in the first room, with these four options, you can venture north, east, west or south ... ");
        IO.println("Write GO NORTH for north, GO EAST for east, GO WEST for west and GO SOUTH for south to choose you next move");
        IO.println("Which will it be ... ?");

        while (gameIsRunning) {

            //Brugeren kan skrive en kommando der bliver pases som et parameter til switch
            String kommando = IO.readln();

            switch (kommando) {
                case "GO NORTH", "NORTH","north","n"  -> {
                    if (adventure.goNorth() == false) {
                        IO.println("A wall is infront of you. You can't go that way");
                    } else {
                        IO.println("You find yourself in ...");
                        IO.println(adventure.look());
                    }
                }
                case "GO EAST", "EAST","east","e" -> {
                    if (adventure.goEast() == false) {
                        IO.println("A wall is infront of you. You can't go that way");
                    } else {
                        IO.println("You find yourself in ...");
                        IO.println(adventure.look());
                    }
                }
                case "GO WEST", "WEST","west","w" -> {
                    if (adventure.goWest() == false) {
                        IO.println("A wall is infront of you. You can't go that way");
                    } else {
                        IO.println("You find yourself in ...");
                        IO.println(adventure.look());
                    }
                }
                case "GO SOUTH", "SOUTH","south","s" -> {
                    if (adventure.goSouth() == false) {
                        IO.println("A wall is infront of you. You can't go that way");
                    } else {
                        IO.println("You find yourself in ...");
                        IO.println(adventure.look());
                    }
                }
                case "EXIT" -> {
                    IO.print("Goodbye!");
                    //afslutter spillet ved at sætte game is running til false
                    gameIsRunning = false;
                }
                case "LOOK" -> {
                    IO.println(adventure.look());
                }
                case "HELP" -> {
                    IO.println("--- COMMANDS ---");
                    IO.println("Type GO NORTH, GO EAST, GO WEST or GO SOUTH for the direction you want to go");
                    IO.println("Type EXIT to exit the game");
                    IO.println("Type LOOK for taking look around the room you currently standing in");
                }
            }
        }

    }

}
