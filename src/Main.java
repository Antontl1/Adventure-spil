// Starter spillet: bygger Adventure og giver det videre til UserInterface
public class Main {
    public static void main(String[] args) {
        Adventure adventure = new Adventure();
        UserInterface userInterface = new UserInterface(adventure);
        userInterface.runGame();
    }
}
