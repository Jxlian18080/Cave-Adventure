import java.util.Random;

public class Trapdoor {

    private int trapdoorLocation;

    private int[] trapdoorWarningLocation = new int[4];

    public void trapdoorGetLocation(boolean[] positionIsBlocked) {

        int calculation;
        int a = 0;

        while (a == 0) {

            Random randomNumber = new Random();
            trapdoorLocation = 1 + randomNumber.nextInt(25);

            if (!(trapdoorLocation == 8 || trapdoorLocation >= 12 && trapdoorLocation <= 14 || trapdoorLocation == 18)) {
                a++;
                positionIsBlocked[trapdoorLocation] = true;

                if (trapdoorLocation <= 5 && trapdoorLocation >= 1) {
                    positionIsBlocked[trapdoorLocation + 20] = true; // hint is in north
                    trapdoorWarningLocation[0] = trapdoorLocation + 20;
                } else {
                    positionIsBlocked[trapdoorLocation - 5] = true;
                    trapdoorWarningLocation[0] = trapdoorLocation - 5;
                }

                calculation = trapdoorLocation % 5;
                if (calculation == 0) {
                    positionIsBlocked[trapdoorLocation - 4] = true; // hint is in east
                    trapdoorWarningLocation[1] = trapdoorLocation - 4;
                } else {
                    positionIsBlocked[trapdoorLocation + 1] = true;
                    trapdoorWarningLocation[1] = trapdoorLocation + 1;
                }

                if (trapdoorLocation <= 25 && trapdoorLocation >= 21) {
                    positionIsBlocked[trapdoorLocation - 20] = true; // hint is in south
                    trapdoorWarningLocation[2] = trapdoorLocation - 20;
                } else {
                    positionIsBlocked[trapdoorLocation + 5] = true;
                    trapdoorWarningLocation[2] = trapdoorLocation + 5;
                }

                calculation = trapdoorLocation % 5;
                if (calculation == 1) {
                    positionIsBlocked[trapdoorLocation + 4] = true; // hint is in west
                    trapdoorWarningLocation[3] = trapdoorLocation + 4;
                } else {
                    positionIsBlocked[trapdoorLocation - 1] = true;
                    trapdoorWarningLocation[3] = trapdoorLocation - 1;
                }
            }
        }

    }

    public void playerOnTrapdoor(int playerLocation, Player player) {

        if (playerLocation == trapdoorLocation) {
            player.setPlayerLose(true);
        }
    }

    public void TrapdoorWarning(int playerLocation) {

        for (int a = 0; a <= 3; a++) {
            if (playerLocation == trapdoorWarningLocation[a]) {
                System.out.println("You entered an other room but WARNING! There is a trapdoor one room near to you");
                trapdoorWarningLocation[a] = 0;
            }
        }

    }

    public int getTrapdoorLocation() {
        return trapdoorLocation;
    }

    public void setTrapdoorLocation(int trapdoorLocation) {
        this.trapdoorLocation = trapdoorLocation;
    }

    public int[] getTrapdoorWarningLocation() {
        return trapdoorWarningLocation;
    }

    public void setTrapdoorWarningLocation(int[] trapdoorWarningLocation) {
        this.trapdoorWarningLocation = trapdoorWarningLocation;
    }

}
