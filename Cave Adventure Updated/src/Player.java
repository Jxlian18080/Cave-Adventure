import java.util.Scanner;

public class Player {

    Scanner scan = new Scanner(System.in);

    private boolean playerHasMap = false;

    private boolean playerHasKey = false;

    private boolean playerFoundDoor = false;

    private boolean playerLose = false;

    private boolean playerFoundHint = false;

    private boolean playerIsHint = false;

    private boolean playerGoHint = false;

    private boolean playerIsTrapdoor = false;

    private boolean playerIsTrapdoorWarning = false;

    private int playerLocation = 13;

    private boolean playerWasThere[] = new boolean[26];

    private int outputCounter = 0;

    private int c = 0;

    public int playerAction(Door door, Key key, Hint hint, Map map, Trapdoor trapdoor, int doorLocation, int keyLocation, int[] mapLocation,
            int trapdoorLocation, int[] trapdoorWarningLocation) {

        int e = 0;

        String userInput = scan.nextLine();
        String[] halfUserInput = userInput.toLowerCase().split(" "); // Player can write a command
        boolean[] newPlayerPositionWasHintPosition = {

        };
        newPlayerPositionWasHintPosition = hint.getPlayerPositionWasHintPosition();

        // rooms:
        // 01 02 03 04 05
        // 06 07 08 09 10
        // 11 12 13 14 15
        // 16 17 18 19 20
        // 21 22 23 24 25

        switch (userInput) {
        case "go north":

            checkPlayerFoundHint(hint); // Check if player was last move on an hint

            if (playerLocation >= 1 && playerLocation <= 5) { // Player move
                playerLocation += 20;
            } else {
                playerLocation -= 5;
            }
            checkPlayerIsTrapdoor(trapdoor); // Check if player is on the trapdoor
            checkPlayerIsTrapdoorWarning(trapdoor); // Check if player is on a trapdoor warning
            checkPlayerIsHint(hint, hint.getHintLocation(), door, key, map); // Check if player is on a hint
            checkUserInput(newPlayerPositionWasHintPosition, hint, halfUserInput, doorLocation, keyLocation, mapLocation, key, door, map);
            // check what has to be output
            break;

        case "go east":

            checkPlayerFoundHint(hint); // Check if player was last move on an hint

            if (playerLocation == 5 || playerLocation == 10 || playerLocation == 15 || playerLocation == 20 || playerLocation == 25) { // Player
                                                                                                                                       // move
                playerLocation -= 4;
            } else {
                playerLocation += 1;
            }
            checkPlayerIsTrapdoor(trapdoor); // Check if player is on the trapdoor
            checkPlayerIsTrapdoorWarning(trapdoor); // Check if player is on a trapdoor warning
            checkPlayerIsHint(hint, hint.getHintLocation(), door, key, map); // Check if player is on a hint
            checkUserInput(newPlayerPositionWasHintPosition, hint, halfUserInput, doorLocation, keyLocation, mapLocation, key, door, map);
            // check what has to be output
            break;
        case "go south":

            checkPlayerFoundHint(hint); // Check if player was last move on an hint

            if (playerLocation >= 21 && playerLocation <= 25) { // Player move
                playerLocation -= 20;
            } else {
                playerLocation += 5;
            }
            checkPlayerIsTrapdoor(trapdoor); // Check if player is on the trapdoor
            checkPlayerIsTrapdoorWarning(trapdoor); // Check if player is on a trapdoor warning
            checkPlayerIsHint(hint, hint.getHintLocation(), door, key, map); // Check if player is on a hint
            checkUserInput(newPlayerPositionWasHintPosition, hint, halfUserInput, doorLocation, keyLocation, mapLocation, key, door, map);
            // check what has to be output
            break;
        case "go west":

            checkPlayerFoundHint(hint); // Check if player was last move on an hint

            if (playerLocation == 1 || playerLocation == 6 || playerLocation == 11 || playerLocation == 16 || playerLocation == 21) { // Player
                                                                                                                                      // move
                playerLocation += 4;
            } else {
                playerLocation -= 1;
            }
            checkPlayerIsTrapdoor(trapdoor); // Check if player is on the trapdoor
            checkPlayerIsTrapdoorWarning(trapdoor); // Check if player is on a trapdoor warning
            checkPlayerIsHint(hint, hint.getHintLocation(), door, key, map); // Check if player is on a hint
            checkUserInput(newPlayerPositionWasHintPosition, hint, halfUserInput, doorLocation, keyLocation, mapLocation, key, door, map);
            // check what has to be output
            break;
        case "show map":

            if (playerHasMap == true) { // check if player has the map
                map.showMap(playerWasThere, playerLocation, door.getDoorLocation(), playerFoundDoor); // print the map
                System.out.println("\nP = Player\nD = Door\nX = Discoverd rooms\nO = Undiscoverd rooms");
            } else {
                System.out.println("You can't use this command if you don't have the map");
            }
            e = 1;

            break;
        case "show inventory":
            if (!playerHasMap && !playerHasKey) {
                System.out.println("Your inventory is empty");
            } else {
                System.out.println("You have in your inventory:");
            }

            if (playerHasMap) {
                System.out.println("Map");
            }
            if (playerHasKey) {
                System.out.println("Key");
            }
            e = 1;

            break;

        case "help":

            System.out.println(
                    "Here are all commands you can use:\ngo north: You move to the room over you\ngo east: You move to the room right to you\ngo south: You move to the room under you\ngo west: You move to the room left to you\nshow map: If you have the map you can see the map\nshow inventory: You see your inventory");
            e = 1;

            break;
        case "show everything":
            System.out.println("Here is the map with the position of everything: ");
            map.showEverything(playerWasThere, playerLocation, doorLocation, playerFoundDoor, hint.getHintLocation(), hint
                    .getHintDirection(), keyLocation, mapLocation, trapdoorLocation, trapdoorWarningLocation); /*
                                                                                                                * Print a map with the
                                                                                                                * position of everything
                                                                                                                */
            System.out.println(
                    "\nP = Player\nD = Door\nK = Key\nM = Map\nT = Trapdoor\n! = Trapdoor Warning\nX = Discoverd rooms\nO = Undiscoverd rooms\n↑ → ↓ ← = Hints");
            break;

        default:

            System.out.println("This isn't a command. Write help if you need help");
        }

        return e;

    }

    public void checkPlayerFoundHint(Hint hint) { // check if the player was last move on a hint

        c = 0;

        for (int i = 4; i <= 13; i++) {

            c = i;
            if (hint.getPlayerPositionWasHintPosition()[i] == true) {
                playerFoundHint = true;

                break;

            }
        }
    }

    public void checkPlayerIsHint(Hint hint, int[] hintLocation, Door door, Key key, Map map) {

        loop:
        for (int i = 4; i <= 13; i++) {

            if (playerLocation == hintLocation[i]) {
                switch (i) {
                case 4:
                case 9:
                    if (hint.getPositionIsBlocked()[door.getDoorLocation()] == true) {
                        playerIsHint = true;
                    }
                    break loop;
                case 5:
                case 10:
                    if (hint.getPositionIsBlocked()[key.getKeyLocation()] == true) {
                        playerIsHint = true;
                    }
                    break loop;
                case 6:
                case 7:
                case 8:
                case 11:
                case 12:
                case 13:
                    if (hint.getPositionIsBlocked()[map.getMapLocation()[2]] == true) {
                        playerIsHint = true;
                    }
                    break loop;
                }
                break;

            }
        }

    }

    public void checkPlayerIsTrapdoor(Trapdoor trapdoor) {

        if (playerLocation == trapdoor.getTrapdoorLocation()) {
            playerIsTrapdoor = true;
        }
    }

    public void checkPlayerIsTrapdoorWarning(Trapdoor trapdoor) {

        for (int a = 0; a <= 3; a++) {
            if (playerLocation == trapdoor.getTrapdoorWarningLocation()[a]) {
                playerIsTrapdoorWarning = true;
            }

        }
    }

    public void checkUserInput(boolean[] newPlayerPositionIsHintPosition, Hint hint, String[] halfUserInput, int doorLocation,
            int keyLocation, int[] mapLocation, Key key, Door door, Map map) {

        if (halfUserInput[1].equals(hint.getHintDirection()[c]) && playerFoundHint == true) {

            if (!(playerLocation == doorLocation || playerLocation == keyLocation || playerLocation == mapLocation[2]
                    || playerLocation == mapLocation[3] || playerLocation == mapLocation[4])) {
                System.out.println("The sound you hear didn't came from this room. Go one room more to the " + hint.getHintDirection()[c]);
                playerGoHint = true;
                playerFoundHint = false;

            } else {
                playerFoundHint = false;

            }

        } else {
            playerFoundHint = false;

        }

        if (playerGoHint == false && playerIsTrapdoor == false && playerIsHint == false && playerIsTrapdoorWarning == false) {
            roomIsEmpty(key.getKeyLocation(), map.getMapLocation(), door.getDoorLocation(), hint.getHintLocation1(), hint
                    .getPositionIsBlocked());
        }

        playerIsHint = false;
        playerGoHint = false;
        playerIsTrapdoorWarning = false;

        for (int h = 0; h <= 13; h++) {
            newPlayerPositionIsHintPosition[h] = false;

        }
        hint.setPlayerPositionWasHintPosition(newPlayerPositionIsHintPosition);

    }

    public void roomIsEmpty(int keyLocation, int[] mapLocation, int doorLocation, int[] hintLocation1, boolean[] positionIsBlocked) {

        if (!(playerLocation == mapLocation[2] || playerLocation == mapLocation[3] || playerLocation == mapLocation[4]
                || playerLocation == doorLocation || playerLocation == keyLocation)) {
            if (playerWasThere[playerLocation] == true) {
                System.out.println("You already explored this room. Go to another room");
                outputCounter = 0;

            } else {
                switch (outputCounter) {
                case 0:
                    System.out.println("This room is empty. Go to another room to find something");
                    outputCounter++;
                    break;

                case 1:
                    System.out.println("This room is also empty. Maybe you find something in the next room");
                    outputCounter++;
                    break;

                case 2:
                    System.out.println("This room is also empty. Go to another room to find something");
                    outputCounter = 0;
                    break;

                }

            }

        }

    }

    public int endGame(int a, int doorLocation) {

        if (playerHasKey == true && playerLocation == doorLocation) {
            System.out.println("You win the game!");
            return ++a;
        }
        if (playerLose) {
            System.out.println("You fall into a trapdoor\nYou lose the game!");
            return ++a;
        }
        return a;

    }

    public boolean[] getPlayerWasThere() {
        return playerWasThere;
    }

    public void setPlayerWasThere(boolean[] playerWasThere) {
        this.playerWasThere = playerWasThere;
    }

    public int getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(int playerLocation) {
        this.playerLocation = playerLocation;
    }

    public boolean isPlayerHasMap() {
        return playerHasMap;
    }

    public void setPlayerHasMap(boolean playerHasMap) {
        this.playerHasMap = playerHasMap;
    }

    public boolean isPlayerHasKey() {
        return playerHasKey;
    }

    public void setPlayerHasKey(boolean playerHasKey) {
        this.playerHasKey = playerHasKey;
    }

    public boolean isPlayerFoundDoor() {
        return playerFoundDoor;
    }

    public void setPlayerFoundDoor(boolean playerFoundDoor) {
        this.playerFoundDoor = playerFoundDoor;
    }

    public int getOutputCounter() {
        return outputCounter;
    }

    public void setOutputCounter(int outputCounter) {
        this.outputCounter = outputCounter;
    }

    public boolean isPlayerFoundHint() {
        return playerFoundHint;
    }

    public void setPlayerFoundHint(boolean playerFoundHint) {
        this.playerFoundHint = playerFoundHint;
    }

    public boolean isPlayerLose() {
        return playerLose;
    }

    public void setPlayerLose(boolean playerLose) {
        this.playerLose = playerLose;
    }

}
