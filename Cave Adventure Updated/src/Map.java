import java.util.Random;

public class Map {

    private int[] mapLocation = new int[5];

    public Map() {

    }

    public void mapGetLocation(boolean[] positionIsBlocked) {
        int a = 0;
        for (int i = 2; i < 5; i++) {
            a = 0;

            while (a == 0) {

                Random randomNumber = new Random();
                mapLocation[i] = 1 + randomNumber.nextInt(25);

                if (!(mapLocation[i] == 8 || mapLocation[i] >= 12 && mapLocation[i] <= 14 || mapLocation[i] == 18
                        || positionIsBlocked[mapLocation[i]] == true)) {
                    a++;
                    positionIsBlocked[mapLocation[i]] = true;
                }
            }
        }

    }

    public void pickUpMap(Player player, int[] hintLocation, Hint hint) {

        for (int i = 2; i < 5; i++) {

            if (player.getPlayerLocation() == mapLocation[i]) {
                System.out.println("You found the Map in this room and picked it up");

                boolean[] newPositionIsBlocked = {

                };
                newPositionIsBlocked = hint.getPositionIsBlocked();
                for (int j = 2; j < 5; j++) {
                    mapLocation[j] = 0;
                    newPositionIsBlocked[mapLocation[j]] = false;
                }
                hint.setPositionIsBlocked(newPositionIsBlocked);
                player.setPlayerHasMap(true);
                player.setOutputCounter(0);

            }
        }
    }

    public void mapUpdate(boolean[] playerWasThere, int playerLocation) {

        playerWasThere[playerLocation] = true;

    }

    public void showMap(boolean[] playerWasThere, int playerLocation, int doorLocation, boolean playerFoundDoor) {

        // print map
        // P = playerposition
        // D = Door
        // X = explored room
        // O = unexplored room

        for (int a = 1; a <= 25; a++) {

            int calculation = 0;
            if (playerLocation == a) {
                calculation = a % 5;
                if (calculation == 0) {
                    System.out.println("P");

                } else {
                    System.out.print("P ");

                }
            } else if (playerFoundDoor == true) {
                if (doorLocation == a) {
                    calculation = a % 5;
                    if (calculation == 0) {
                        System.out.println("D");

                    } else {
                        System.out.print("D ");

                    }
                }
            }

            if (playerWasThere[a] == true && playerLocation != a && !(playerFoundDoor == true && doorLocation == a)) {
                calculation = a % 5;
                if (calculation == 0) {
                    System.out.println("X");

                } else {
                    System.out.print("X ");

                }

            } else if (playerLocation != a && !(playerFoundDoor == true && doorLocation == a)) {
                calculation = a % 5;
                if (calculation == 0) {
                    System.out.println("O");

                } else {
                    System.out.print("O ");

                }

            }

        }

    }

    public void showEverything(boolean[] playerWasThere, int playerLocation, int doorLocation, boolean playerFoundDoor, int[] hintLocation,
            String[] hintDirection, int keyLocation, int[] mapLocation, int trapdoorLocation, int[] trapdoorWarningLocation) {

        // print map
        // P = playerposition
        // D = Door
        // K = Key
        // M = Map
        // T = Trapdoor
        // ! = Trapdoor Warning
        // X = explored room
        // O = unexplored room
        // ↑ → ↓ ← = Hints

        for (int a = 1; a <= 25; a++) {
            inner:
            for (int b = 0; b == 0; b++) {

                int calculation = 0;
                if (playerLocation == a) {
                    calculation = a % 5;
                    if (calculation == 0) {
                        System.out.println("P");
                        break inner;

                    } else {
                        System.out.print("P ");
                        break inner;
                    }
                }
                if (doorLocation == a) {
                    calculation = a % 5;
                    if (calculation == 0) {
                        System.out.println("D");
                        break inner;
                    } else {
                        System.out.print("D ");
                        break inner;
                    }
                }
                if (keyLocation == a) {
                    calculation = a % 5;
                    if (calculation == 0) {
                        System.out.println("K");
                        break inner;
                    } else {
                        System.out.print("K ");
                        break inner;
                    }
                }
                for (int d = 2; d <= 4; d++) {

                    if (mapLocation[d] == a) {
                        calculation = a % 5;
                        if (calculation == 0) {
                            System.out.println("M");
                            break inner;
                        } else {
                            System.out.print("M ");
                            break inner;
                        }
                    }
                }

                for (int c = 4; c <= 13; c++) {
                    if (hintLocation[c] == a) {
                        switch (hintDirection[c]) {

                        case "north":
                            calculation = a % 5;
                            if (calculation == 0) {
                                System.out.println("↑");
                                break inner;
                            } else {
                                System.out.print("↑ ");
                                break inner;
                            }
                        case "east":
                            calculation = a % 5;
                            if (calculation == 0) {
                                System.out.println("→");
                                break inner;
                            } else {
                                System.out.print("→ ");
                                break inner;
                            }

                        case "south":
                            calculation = a % 5;
                            if (calculation == 0) {
                                System.out.println("↓");
                                break inner;
                            } else {
                                System.out.print("↓ ");
                                break inner;
                            }

                        case "west":
                            calculation = a % 5;
                            if (calculation == 0) {
                                System.out.println("←");
                                break inner;
                            } else {
                                System.out.print("← ");
                                break inner;
                            }

                        }
                    }

                }

                if (trapdoorLocation == a) {
                    calculation = a % 5;
                    if (calculation == 0) {
                        System.out.println("T");
                        break inner;
                    } else {
                        System.out.print("T ");
                        break inner;
                    }
                }

                for (int e = 0; e <= 3; e++) {

                    if (trapdoorWarningLocation[e] == a) {
                        calculation = a % 5;
                        if (calculation == 0) {
                            System.out.println("!");
                            break inner;
                        } else {
                            System.out.print("! ");
                            break inner;
                        }
                    }
                }

                calculation = a % 5;
                if (calculation == 0) {
                    System.out.println("O");

                } else {
                    System.out.print("O ");

                }

            }
        }
    }

    public int[] getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(int[] mapLocation) {
        this.mapLocation = mapLocation;
    }

}
