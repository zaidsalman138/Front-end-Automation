package Utilities;
import java.util.Random;

public class RandomValue {
    
    public String generateRandomName(int length) {
       String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
       StringBuilder randomName = new StringBuilder();

       Random random = new Random();
       for (int i = 0; i < length; i++) {
        randomName.append(characters.charAt(random.nextInt(characters.length())));
       }

    return randomName.toString();
    }
}