import java.util.Random;

public class Hint {

    private int[] hintLocation = new int[14];

    private int[] hintLocation1 = new int[14];

    private String[] hintDirection = new String[14];

    private boolean[] PlayerPositionWasHintPosition = new boolean[14];

    private boolean[] positionIsBlocked = new boolean[26];

    private int calculation;

    private int calculation1;

    private int a = 0;

    Random randomNumber = new Random();

    public int hintGetLocation(int doorLocation, int keyLocation, int[] mapLocation, int c) {
        positionIsBlocked[0] = true;
        positionIsBlocked[13] = true;
        positionIsBlocked[doorLocation] = true;
        positionIsBlocked[keyLocation] = true;
        positionIsBlocked[mapLocation[2]] = true;
        positionIsBlocked[mapLocation[3]] = true;
        positionIsBlocked[mapLocation[4]] = true;

        int j = 0;
        c = 1;

        outer:
        for (int i = 4; i <= 13; i++) {
            a = 0;
            while (a == 0) {
                j++;
                if (j == 10000) { // anti crash condition
                    j = 0;
                    i = 3;
                    a = 1;
                    for (int b = 0; b <= 25; b++) {
                        positionIsBlocked[b] = false;
                    }

                    c = 0;
                    break outer;

                }

                hintLocation[i] = 1 + randomNumber.nextInt(4); // 1 = North, 2 = East, 3 = South, 4 = West

                switch (hintLocation[i]) {
                case 1:
                    hintDirection[i] = "north";

                    switch (i) {
                    case 4:
                    case 9:
                        calculateDistanceNorth(doorLocation, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 5:
                    case 10:
                        calculateDistanceNorth(keyLocation, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 6:
                    case 11:
                        calculateDistanceMapNorth(2, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 7:
                    case 12:
                        calculateDistanceMapNorth(3, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 8:
                    case 13:
                        calculateDistanceMapNorth(4, doorLocation, keyLocation, mapLocation, i);

                        break;
                    }
                    break;
                case 2:
                    hintDirection[i] = "east";

                    switch (i) {
                    case 4:
                    case 9:
                        calculateDistanceEast(doorLocation, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 5:
                    case 10:
                        calculateDistanceEast(keyLocation, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 6:
                    case 11:
                        calculateDistanceMapEast(2, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 7:
                    case 12:
                        calculateDistanceMapEast(3, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 8:
                    case 13:
                        calculateDistanceMapEast(4, doorLocation, keyLocation, mapLocation, i);

                        break;
                    }
                    break;

                case 3:
                    hintDirection[i] = "south";

                    switch (i) {
                    case 4:
                    case 9:
                        calculateDistanceSouth(doorLocation, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 5:
                    case 10:
                        calculateDistanceSouth(keyLocation, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 6:
                    case 11:
                        calculateDistanceMapSouth(2, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 7:
                    case 12:
                        calculateDistanceMapSouth(3, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 8:
                    case 13:
                        calculateDistanceMapSouth(4, doorLocation, keyLocation, mapLocation, i);

                        break;
                    }
                    break;

                case 4:
                    hintDirection[i] = "west";

                    switch (i) {
                    case 4:
                    case 9:
                        calculateDistanceWest(doorLocation, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 5:
                    case 10:
                        calculateDistanceWest(keyLocation, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 6:
                    case 11:
                        calculateDistanceMapWest(2, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 7:
                    case 12:
                        calculateDistanceMapWest(3, doorLocation, keyLocation, mapLocation, i);

                        break;
                    case 8:
                    case 13:
                        calculateDistanceMapWest(4, doorLocation, keyLocation, mapLocation, i);

                        break;
                    }
                    break;
                }

            }

        }
        positionIsBlocked[13] = false;
        hintLocation1 = hintLocation;
        return c;

    }

    public void calculateDistanceNorth(int Location, int doorLocation, int keyLocation, int[] mapLocation, int i) {
        hintLocation[i] = 1 + randomNumber.nextInt(25);
        calculation = Location - hintLocation[i];
        if (calculation == -5 && hintLocation[i] >= 1 && hintLocation[i] <= 5 || calculation == -10 && hintLocation[i] >= 11
                && hintLocation[i] <= 25 && positionIsBlocked[hintLocation[i] - 5] == false || calculation == 20 && hintLocation[i] >= 1
                        && hintLocation[i] <= 5 || calculation == 15 && hintLocation[i] >= 6 && hintLocation[i] <= 10
                                && positionIsBlocked[hintLocation[i] - 5] == false || calculation == 15 && hintLocation[i] >= 1
                                        && hintLocation[i] <= 5 && positionIsBlocked[hintLocation[i] + 20] == false) {

            if (positionIsBlocked[hintLocation[i]] == false) {
                a++;
                positionIsBlocked[hintLocation[i]] = true;
                if (calculation == -10 || calculation == 15 && hintLocation[i] >= 6 && hintLocation[i] <= 10) {
                    positionIsBlocked[hintLocation[i] - 5] = true;
                }
                if (calculation == 15 && hintLocation[i] >= 1 && hintLocation[i] <= 5) {
                    positionIsBlocked[hintLocation[i] + 20] = true;
                }

            }
        }
    }

    public void calculateDistanceMapNorth(int index, int doorLocation, int keyLocation, int[] mapLocation, int i) {
        hintLocation[i] = 1 + randomNumber.nextInt(25);
        calculation = mapLocation[index] - hintLocation[i];
        if (calculation == -5 && hintLocation[i] >= 1 && hintLocation[i] <= 5 || calculation == -10 && hintLocation[i] >= 11
                && hintLocation[i] <= 25 && positionIsBlocked[hintLocation[i] - 5] == false || calculation == 20 && hintLocation[i] >= 1
                        && hintLocation[i] <= 5 || calculation == 15 && hintLocation[i] >= 6 && hintLocation[i] <= 10
                                && positionIsBlocked[hintLocation[i] - 5] == false || calculation == 15 && hintLocation[i] >= 1
                                        && hintLocation[i] <= 5 && positionIsBlocked[hintLocation[i] + 20] == false) {

            if (positionIsBlocked[hintLocation[i]] == false) {
                a++;
                positionIsBlocked[hintLocation[i]] = true;
                if (calculation == -10 || calculation == 15 && hintLocation[i] >= 6 && hintLocation[i] <= 10) {
                    positionIsBlocked[hintLocation[i] - 5] = true;
                }
                if (calculation == 15 && hintLocation[i] >= 1 && hintLocation[i] <= 5) {
                    positionIsBlocked[hintLocation[i] + 20] = true;
                }

            }
        }
    }

    public void calculateDistanceEast(int Location, int doorLocation, int keyLocation, int[] mapLocation, int i) {
        hintLocation[i] = 1 + randomNumber.nextInt(25);
        calculation = Location - hintLocation[i];
        calculation1 = hintLocation[i] % 5;
        if (calculation == 1 && calculation1 != 0 || calculation == 2 && calculation1 != 0 && calculation1 != 4
                && positionIsBlocked[hintLocation[i] + 1] == false || calculation == -3 && calculation1 == 4
                        && positionIsBlocked[hintLocation[i] + 1] == false || calculation == -3 && calculation1 == 0
                                && positionIsBlocked[hintLocation[i] - 4] == false || calculation == -4 && calculation1 == 0) {
            if (positionIsBlocked[hintLocation[i]] == false) {
                a++;
                positionIsBlocked[hintLocation[i]] = true;
                if (calculation == 2 || calculation == -3 && calculation1 == 4) {
                    positionIsBlocked[hintLocation[i] + 1] = true;
                }
                if (calculation == -3 && calculation1 == 0) {
                    positionIsBlocked[hintLocation[i] - 4] = true;
                }
            }
        }
    }

    public void calculateDistanceMapEast(int index, int doorLocation, int keyLocation, int[] mapLocation, int i) {
        hintLocation[i] = 1 + randomNumber.nextInt(25);
        calculation = mapLocation[index] - hintLocation[i];
        calculation1 = hintLocation[i] % 5;
        if (calculation == 1 && calculation1 != 0 || calculation == 2 && calculation1 != 0 && calculation1 != 4
                && positionIsBlocked[hintLocation[i] + 1] == false || calculation == -3 && calculation1 == 4
                        && positionIsBlocked[hintLocation[i] + 1] == false || calculation == -3 && calculation1 == 0
                                && positionIsBlocked[hintLocation[i] - 4] == false || calculation == -4 && calculation1 == 0) {
            if (positionIsBlocked[hintLocation[i]] == false) {
                a++;
                positionIsBlocked[hintLocation[i]] = true;
                if (calculation == 2 || calculation == -3 && calculation1 == 4) {
                    positionIsBlocked[hintLocation[i] + 1] = true;
                }
                if (calculation == -3 && calculation1 == 0) {
                    positionIsBlocked[hintLocation[i] - 4] = true;
                }
            }
        }
    }

    public void calculateDistanceSouth(int Location, int doorLocation, int keyLocation, int[] mapLocation, int i) {
        hintLocation[i] = 1 + randomNumber.nextInt(25);
        calculation = Location - hintLocation[i];
        if (calculation == 5 && hintLocation[i] >= 21 && hintLocation[i] <= 25 || calculation == 10 && hintLocation[i] >= 1
                && hintLocation[i] <= 15 && positionIsBlocked[hintLocation[i] + 5] == false || calculation == -20 && hintLocation[i] >= 21
                        && hintLocation[i] <= 25 || calculation == 15 && hintLocation[i] >= 16 && hintLocation[i] <= 20
                                && positionIsBlocked[hintLocation[i] + 5] == false || calculation == 15 && hintLocation[i] >= 21
                                        && hintLocation[i] <= 25 && positionIsBlocked[hintLocation[i] - 20] == false) {
            if (positionIsBlocked[hintLocation[i]] == false) {
                a++;
                positionIsBlocked[hintLocation[i]] = true;
                if (calculation == 10 || calculation == -15 && hintLocation[i] >= 16 && hintLocation[i] <= 20) {
                    positionIsBlocked[hintLocation[i] + 5] = true;
                }
                if (calculation == -15 && hintLocation[i] >= 21 && hintLocation[i] <= 25) {
                    positionIsBlocked[hintLocation[i] - 20] = true;
                }

            }
        }
    }

    public void calculateDistanceMapSouth(int index, int doorLocation, int keyLocation, int[] mapLocation, int i) {
        hintLocation[i] = 1 + randomNumber.nextInt(25);
        calculation = mapLocation[index] - hintLocation[i];
        if (calculation == 5 && hintLocation[i] >= 21 && hintLocation[i] <= 25 || calculation == 10 && positionIsBlocked[hintLocation[i]
                + 5] == false || calculation == -20 && hintLocation[i] >= 21 && hintLocation[i] <= 25 || calculation == 15
                        && hintLocation[i] >= 16 && hintLocation[i] <= 20 && positionIsBlocked[hintLocation[i] + 5] == false
                || calculation == 15 && hintLocation[i] >= 21 && hintLocation[i] <= 25 && positionIsBlocked[hintLocation[i]
                        - 20] == false) {
            if (positionIsBlocked[hintLocation[i]] == false) {
                a++;
                positionIsBlocked[hintLocation[i]] = true;
                if (calculation == 10 || calculation == -15 && hintLocation[i] >= 16 && hintLocation[i] <= 20) {
                    positionIsBlocked[hintLocation[i] + 5] = true;
                }
                if (calculation == -15 && hintLocation[i] >= 21 && hintLocation[i] <= 25) {
                    positionIsBlocked[hintLocation[i] - 20] = true;
                }

            }
        }
    }

    public void calculateDistanceWest(int Location, int doorLocation, int keyLocation, int[] mapLocation, int i) {
        hintLocation[i] = 1 + randomNumber.nextInt(25);
        calculation = Location - hintLocation[i];
        calculation1 = hintLocation[i] % 5;
        if (calculation == -1 && calculation1 != 1 || calculation == -2 && calculation1 != 1 && calculation1 != 2
                && positionIsBlocked[hintLocation[i] - 1] == false || calculation == 3 && calculation1 == 2
                        && positionIsBlocked[hintLocation[i] - 1] == false || calculation == 3 && calculation1 == 1
                                && positionIsBlocked[hintLocation[i] + 4] == false || calculation == 4 && calculation1 == 1) {
            if (positionIsBlocked[hintLocation[i]] == false) {
                a++;
                positionIsBlocked[hintLocation[i]] = true;
                if (calculation == -2 || calculation == 3 && calculation1 == 2) {
                    positionIsBlocked[hintLocation[i] - 1] = true;
                }
                if (calculation == 3 && calculation1 == 1) {
                    positionIsBlocked[hintLocation[i] + 4] = true;
                }
            }
        }
    }

    public void calculateDistanceMapWest(int index, int doorLocation, int keyLocation, int[] mapLocation, int i) {
        hintLocation[i] = 1 + randomNumber.nextInt(25);
        calculation = mapLocation[index] - hintLocation[i];
        calculation1 = hintLocation[i] % 5;
        if (calculation == -1 && calculation1 != 1 || calculation == -2 && calculation1 != 1 && calculation1 != 2
                && positionIsBlocked[hintLocation[i] - 1] == false || calculation == 3 && calculation1 == 2
                        && positionIsBlocked[hintLocation[i] - 1] == false || calculation == 3 && calculation1 == 1
                                && positionIsBlocked[hintLocation[i] + 4] == false || calculation == 4 && calculation1 == 1) {
            if (positionIsBlocked[hintLocation[i]] == false) {
                a++;
                positionIsBlocked[hintLocation[i]] = true;
                if (calculation == -2 || calculation == 3 && calculation1 == 2) {
                    positionIsBlocked[hintLocation[i] - 1] = true;
                }
                if (calculation == 3 && calculation1 == 1) {
                    positionIsBlocked[hintLocation[i] + 4] = true;
                }
            }
        }
    }

    public void showHint(Player player, int playerLocation, int doorLocation, int keyLocation, int[] mapLocation) {

        outer:
        for (int x = 4; x <= 13; x++) {

            if (hintLocation[x] == playerLocation) {
                for (int b = 0; b <= 25; b++) {
                    if (positionIsBlocked[b] == true) {

                        for (int c = 2; c <= 4; c++) {

                            if (x >= 6 && x <= 8 && positionIsBlocked[mapLocation[c]] == true || x >= 11 && x <= 13
                                    && positionIsBlocked[mapLocation[c]] == true) {
                                for (int i = 6; i <= 8; i++) {
                                    hintLocation1[i] = 0;
                                    positionIsBlocked[i] = false;
                                }
                                for (int i = 11; i <= 13; i++) {
                                    hintLocation1[i] = 0;
                                    positionIsBlocked[i] = false;
                                }

                                System.out.println("You entered an other room and heard something in the " + hintDirection[x]);
                                PlayerPositionWasHintPosition[x] = true;
                                player.setOutputCounter(0);
                                break outer;

                            } else if (x == 4 && positionIsBlocked[doorLocation] == true || x == 9
                                    && positionIsBlocked[doorLocation] == true) {
                                hintLocation1[4] = 0;
                                hintLocation1[9] = 0;
                                positionIsBlocked[4] = false;
                                positionIsBlocked[9] = false;
                                System.out.println("You entered an other room and heard something in the " + hintDirection[x]);

                                PlayerPositionWasHintPosition[x] = true;
                                player.setOutputCounter(0);
                                break outer;
                            } else if (x == 5 && positionIsBlocked[keyLocation] == true || x == 10
                                    && positionIsBlocked[keyLocation] == true) {
                                hintLocation1[5] = 0;
                                hintLocation1[10] = 0;
                                positionIsBlocked[5] = false;
                                positionIsBlocked[10] = false;
                                System.out.println("You entered an other room and heard something in the " + hintDirection[x]);

                                PlayerPositionWasHintPosition[x] = true;
                                player.setOutputCounter(0);
                                break outer;
                            }
                        }
                        break outer;
                    }
                }
            }
        }
    }

    public String[] getHintDirection() {
        return hintDirection;
    }

    public void setHintDirection(String[] hintDirection) {
        this.hintDirection = hintDirection;
    }

    public boolean[] getPlayerPositionWasHintPosition() {
        return PlayerPositionWasHintPosition;
    }

    public void setPlayerPositionWasHintPosition(boolean[] PlayerPositionWasHintPosition) {
        this.PlayerPositionWasHintPosition = PlayerPositionWasHintPosition;
    }

    public int[] getHintLocation() {
        return hintLocation;
    }

    public void setHintLocation(int[] hintLocation) {
        this.hintLocation = hintLocation;
    }

    public boolean[] getPositionIsBlocked() {
        return positionIsBlocked;
    }

    public void setPositionIsBlocked(boolean[] positionIsBlocked) {
        this.positionIsBlocked = positionIsBlocked;
    }

    public int[] getHintLocation1() {
        return hintLocation1;
    }

    public void setHintLocation1(int[] hintLocation1) {
        this.hintLocation1 = hintLocation1;
    }
}
