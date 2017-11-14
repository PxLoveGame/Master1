package ex2.factory;

import java.util.Random;

public class HardFactory extends AbstractFactory {


    @Override
    public String createCryptedWord(String p_text) {
        StringBuilder crypted_text = new StringBuilder();

        int percent = (p_text.length() * 90) / 100;

        Random rand = new Random();

        for(int index = 0; index < percent; index++)
        {
            int random_index = rand.nextInt((122 - 97) + 1) + 97;
            int count = 0;
            int i;
            for (i=0x2500;i<=0x257F;i++)
            {
                if(count == random_index)
                    break;
                count++;
            }

            crypted_text.append((char) i);
        }

        crypted_text.append(p_text.substring(percent, p_text.length()));

        return crypted_text.toString();
    }
}
