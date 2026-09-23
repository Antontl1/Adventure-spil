//Styrer selve spillet: skriver beskeder ud, læser brugerens kommandoer
//og holder spillet i gang indtil spilleren vælger at stoppe
public class UserInterface {

    public void startGame() {
        //Bygger rummene og holder styr på hvilket rum spilleren er i
        Adventure adventure = new Adventure();
        Room theFirstRoom = adventure.getFirstRoom();
        Player player = new Player(theFirstRoom);
        //Vurderer om spillet stadig er i gang
        boolean gameIsOver = false;
        IO.println("Welcome to Adventure! You are standing in the first room and can walk between the rooms.");
        IO.println("You can play the game using a few simple commands:");
        IO.println("Type LOOK to look around, NORTH, SOUTH, EAST or WEST to go that way, and EXIT to quit the game: ");

        //mens boolean er true
        while (!gameIsOver) {

            //Læser en linje fra brugeren. Der skal skrives med STORE bogstaver for at ramme en case
            String command = IO.readln();

            //Menu med valgmuligheder der tager parameteret command til at vælge en mulighed
            switch (command) {
                case "LOOK" -> {
                    //Spørger Player hvilket rum han står i, og henter navn og beskrivelse ud af rummet
                    Room currentRoom = player.getCurrentRoom();
                    IO.println(currentRoom.getName());
                    IO.println(currentRoom.getDescription());
                }
                case "NORTH" -> {
                    if (player.goNorth()) {
                        IO.println("You go north.");
                    } else {
                        IO.println("You cannot go north from here. Try another way.");
                    }
                }
                case "SOUTH" -> {
                    if (player.goSouth()) {
                        IO.println("You go south.");
                    } else {
                        IO.println("You cannot go south from here. Try another way.");
                    }
                }
                case "EAST" -> {
                    if (player.goEast()) {
                        IO.println("You go east.");
                    } else {
                        IO.println("You cannot go east from here. Try another way.");
                    }
                }
                case "WEST" -> {
                    if (player.goWest()) {
                        IO.println("You go west.");
                    } else {
                        IO.println("You cannot go west from here. Try another way.");
                    }
                }
                case "EXIT" -> {
                    IO.println("Thanks for playing!");
                    gameIsOver = true;
                }
            }
        }

    }
}
