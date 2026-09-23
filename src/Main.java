void main() {
    //Opretter ny eventyrs obejekt?
    Adventure adventure = new Adventure();
    //Opretter interface objekt
    UserInterface userInterface = new UserInterface(adventure);
    //kalder run game metoden på userinterface som vi lige har oprettet
    userInterface.runGame();
}