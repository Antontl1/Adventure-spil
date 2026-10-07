// De mulige udfald af ATTACK. Player.attack() vælger ét, og UserInterface printer en besked ud fra det
public enum AttackStatus {
    NO_WEAPON,
    WEAPON_OUT_OF_AMMO,
    // Spilleren skrev ikke et navn, og der er ingen fjender i rummet
    NO_ENEMY_SPECIFIED_AND_ROOM_EMPTY,
    // Spilleren skrev et navn, der ikke står i rummet
    ENEMY_NOT_FOUND,
    SUCCESS_ENEMY_KILLED,
    // Fjenden overlevede og slog igen
    SUCCESS_ENEMY_SURVIVED
}
