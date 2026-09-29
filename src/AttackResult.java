public class AttackResult {
    private AttackStatus status;
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