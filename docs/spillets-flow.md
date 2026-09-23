# Sådan hænger klasserne sammen

Fire klasser med hver sit ansvar:

| Klasse | Ansvar |
|---|---|
| `Main` | Samler tingene og starter spillet |
| `UserInterface` | Snakker med brugeren: læser kommandoer og printer tekst |
| `Adventure` | Bygger kortet, ejer spilleren, og er det eneste `UserInterface` kender |
| `Player` | Husker hvilket rum spilleren står i, og flytter ham |
| `Room` | Kender sit navn, sin beskrivelse og sine fire naboer |

`Room` kalder aldrig noget selv. Den bliver spurgt og svarer.

## 1. Opbygning — kører én gang ved spilstart

Her bruges `Room`s **settere**.

```
Main
 └─ new Adventure()
     ├─ createRoomOrder()
     │   ├─ new Room("Entrance Hall", "...")   opretter rummet med navn og beskrivelse
     │   ├─ room1.setRoomEast(room2)           fylder east-feltet i room1
     │   └─ room2.setRoomWest(room1)           fylder west-feltet i room2
     └─ new Player(firstRoom)                  spilleren sættes ind i rum 1
```

Sådan ser rum 1 ud undervejs:

| felt | efter `new Room(...)` | efter `setRoomEast(room2)` |
|---|---|---|
| name | "Entrance Hall" | "Entrance Hall" |
| description | "A large room..." | "A large room..." |
| east | null | room2 |
| north / south / west | null | null |

Bemærk at `room2.setRoomWest(room1)` ikke ændrer noget i rum 1. Hvert kald rører kun ét felt i ét rum,
og derfor skal hver dør sættes fra begge sider.

Efter opbygningen rører ingen setterne igen.

## 2. Spil — kører hver gang brugeren skriver noget

Her bruges `Room`s **gettere**.

```
Brugeren skriver "EAST"
 └─ UserInterface: case "EAST"
     └─ adventure.goEast()
         └─ player.goEast()
             └─ currentRoom.getRoomEast()      læser east-feltet fra fase 1
                 │
                 ├─ et Room  →  moveTo(): currentRoom = det nye rum  →  true
                 └─ null     →  moveTo(): spilleren står stille      →  false
                                    │
     "You go east" / "You cannot go east" ←────┘
```

```
Brugeren skriver "LOOK"
 └─ UserInterface: case "LOOK"
     └─ adventure.look()
         ├─ player.getCurrentRoom()                hvilket rum står han i?
         └─ getName() + getDescription()           teksten der printes
```

Retningen slås altid op i `currentRoom`, altså det rum spilleren står i lige nu.
Derfor giver de samme fire metoder et nyt svar for hvert rum, man går igennem.

## Kortet

```
1 - 2 - 3
|       |
4   5   6
|   |   |
7 - 8 - 9
```

| Nr. | Rum | Nr. | Rum |
|---|---|---|---|
| 1 | Entrance Hall | 6 | Dungeon |
| 2 | Library | 7 | Garden |
| 3 | Armoury | 8 | Hallway |
| 4 | Kitchen | 9 | Treasure Chamber |
| 5 | Throne Room | | |

Rum 5 har kun én dør, ned til rum 8. Man kommer kun derind nordfra og ud samme vej.
