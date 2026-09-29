// Samler svaret på et angreb i ét objekt, så Player kan returnere flere ting på én gang:
// hvordan det gik, hvilket våben der blev brugt, og hvor mange skud der er tilbage
public class AttackResult {
    private AttackStatus status;
    // null hvis spilleren ikke havde noget våben
    private Weapon weapon;
    private int remainingUses;

    public AttackResult(AttackStatus status, Weapon weapon, int remainingUses) {
        this.status = status;
        this.weapon = weapon;
        this.remainingUses = remainingUses;
    }

    public AttackStatus getStatus() {
        return status;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getRemainingUses() {
        return remainingUses;
    }
}
