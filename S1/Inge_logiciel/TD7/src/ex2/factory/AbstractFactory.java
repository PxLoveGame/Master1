package ex2.factory;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractFactory {

    public abstract String createCryptedWord(String p_text);

    @NotNull
    public static AbstractFactory getGameFactory(String p_difficulty) throws Exception
    {
        switch(p_difficulty.toLowerCase())
        {
            case "easy":
                return new EasyFactory();

            case "medium":
                return new NormalFactory();

            case "hard":
                return new HardFactory();

            default:
                throw new Exception("Unknow difficulty");
        }
    }
}
