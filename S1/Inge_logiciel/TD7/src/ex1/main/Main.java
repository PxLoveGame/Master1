package ex1.main;
import ex1.compiler.Compiler;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("-----------------------");
            Compiler c1 = new Compiler("Java");
            c1.compil();
            System.out.println("-----------------------");
            Compiler c2 = new Compiler("C++");
            c2.compil();
            System.out.println("-----------------------");
            Compiler c3 = new Compiler("ADA");
            c3.compil();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}