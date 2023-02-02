import java.util.Random;

public class Key {

    private int keyLocation = 0;

    public Key() {

    }

    public void keygetLocation(boolean[] positionIsBlocked) {
        int a = 0;
        while (a == 0) {

            Random randomNumber = new Random();
            keyLocation = 1 + randomNumber.nextInt(25);

            if (!(keyLocation == 8 || keyLocation >= 12 && keyLocation <= 14 || keyLocation == 18
                    || positionIsBlocked[keyLocation] == true)) {
                a++;
                positionIsBlocked[keyLocation] = true;
            }
        }

    }

    public void pickUpKey(Player player, int[] hintLocation, Hint hint) {

        if (player.getPlayerLocation() == keyLocation) {

            System.out.println("You found the key in this room and picked it up");
            keyLocation = 0;

            player.setPlayerHasKey(true);
            player.setOutputCounter(0);

            boolean[] newPositionIsBlocked = {

            };
            newPositionIsBlocked = hint.getPositionIsBlocked();
            newPositionIsBlocked[keyLocation] = false;
            hint.setPositionIsBlocked(newPositionIsBlocked);

        }

    }

    public int getKeyLocation() {
        return keyLocation;
    }

    public void setKeyLocation(int keyLocation) {
        this.keyLocation = keyLocation;
    }
}
