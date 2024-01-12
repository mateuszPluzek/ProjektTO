package RandomNum;

import java.util.Random;

public class RandomEvent {

/*  This method takes an int between 1 and 10 representing the percentage chance of event happening
*   (2 = 20%) and returns true if it passed, or false if not. Inputting wrong chance always return false*/
    public static boolean eventChance(int chance) {
        if(chance < 0 || chance > 10) {
            return false;
        }
        Random random = new Random();
        return random.nextInt(10) + 1 <= chance;
    }

}


