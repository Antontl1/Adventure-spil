void main() {

    Player player1 = new Player("Link", currentRoom);
    Adventure adventure = new Adventure();
    UserInterface userInterface = new UserInterface(adventure);
    userInterface.runGame();
}