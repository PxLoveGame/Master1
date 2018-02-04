package ex2.game;

import ex2.factory.AbstractFactory;

import java.util.Scanner;

public class Game {

    String word = "Coucou";
    String crypted_word = "";

    public Game(String difficulty) throws Exception{

        AbstractFactory factory = AbstractFactory.getGameFactory(difficulty);
        crypted_word = factory.createCryptedWord(word);

    }

    public void go(){

        StringBuilder command = new StringBuilder();
        Scanner input = new Scanner(System.in);
        char[] crypted_array = crypted_word.toCharArray();

        while(true){
            System.out.println(String.valueOf(crypted_array));

            System.out.print("> ");
            command.append(input.nextLine());

            for(int index = 0; index < word.length(); index++)
            {
                if(crypted_array[index] == command.charAt(0))

                    if(command.charAt(2) == word.charAt(index)){
                        System.out.println("Le symbole : "+ crypted_array[index] + " Etait bien la lettre : "+ command.charAt(2));
                        crypted_array[index] = command.charAt(2);
                    }

            }
            command.setLength(0);

            if(String.valueOf(crypted_array).equals(word))
            {
                System.out.println(String.valueOf(crypted_array));

                System.out.println("Win !");
                input.close();
                return;
            }
        }
    }
}
