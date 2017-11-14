package ex1.compiler.component.generator;

public class GeneratorJava extends AbstractGenerator
{
    @Override
    public void generate()
    {
        System.out.println("I am generating a JVM program text from a Java AbstractSyntaxTree");
    }
}