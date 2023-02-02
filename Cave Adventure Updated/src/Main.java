
public class Main {

    public static void main(String[] args) {

        int a = 0;
        int c = 0;
        int e = 0;

        // String player;
        // String[] room = new String[26]; // 26 because "0" isn't a room

        Door door = new Door();
        Key key = new Key();
        Map map = new Map();
        Player player = new Player();
        Hint hint = new Hint();
        Trapdoor trapdoor = new Trapdoor();

        while (c == 0) {
            trapdoor.trapdoorGetLocation(hint.getPositionIsBlocked()); // Trapdoor get location
            door.doorGetLocation(hint.getPositionIsBlocked()); // Door get location
            key.keygetLocation(hint.getPositionIsBlocked()); // Key get Location
            map.mapGetLocation(hint.getPositionIsBlocked()); // Map get Location
            c = hint.hintGetLocation(door.getDoorLocation(), key.getKeyLocation(), map.getMapLocation(), c); // All hints get locations
        }

        System.out.println(
                "You are caught in a dark cave.\nYou have to find the key and the door to escape the cave.\nIf you need help write help");

        while (a != 1) {

            map.mapUpdate(player.getPlayerWasThere(), player.getPlayerLocation()); // Map get updated
            e = player.playerAction(door, key, hint, map, trapdoor, door.getDoorLocation(), key.getKeyLocation(), map.getMapLocation(),
                    trapdoor.getTrapdoorLocation(), trapdoor.getTrapdoorWarningLocation()); // Here are all commands the player can use
            if (e == 0) {
                map.pickUpMap(player, hint.getHintLocation(), hint); // Player get the map
                key.pickUpKey(player, hint.getHintLocation(), hint); // Player get the key
                door.foundDoor(player, player.isPlayerHasKey(), hint.getHintLocation(), hint); // Player find the door
                trapdoor.playerOnTrapdoor(player.getPlayerLocation(), player); // Player find the trapdoor
                trapdoor.TrapdoorWarning(player.getPlayerLocation()); // Player get a Warning
                a = player.endGame(a, door.getDoorLocation()); // The game ends
                hint.showHint(player, player.getPlayerLocation(), door.getDoorLocation(), key.getKeyLocation(), map.getMapLocation()); // Player
                                                                                                                                       // find
                                                                                                                                       // a
                                                                                                                                       // hint
            }

        }

    }

}
