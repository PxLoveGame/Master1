package ex1.compiler;


import com.sun.org.apache.xpath.internal.SourceTree;
import ex1.compiler.component.generator.AbstractGenerator;
import ex1.compiler.component.lexer.AbstractLexer;
import ex1.compiler.component.parser.AbstractParser;
import ex1.compiler.factory.AbstractCompilerFactory;

public class Compiler
{
    private String a_language;
    private AbstractLexer a_lexer;
    private AbstractParser a_parser;
    private AbstractGenerator a_generator;

    public Compiler(String p_language) throws Exception
    {
        a_language = p_language;

        AbstractCompilerFactory t_factory = AbstractCompilerFactory.getFactory(a_language);

        a_lexer = t_factory.createLexer();
        a_parser = t_factory.createParser();
        a_generator = t_factory.createGenerator();
    }

    public void compil()
    {
        System.out.println(toString_begin());
        a_lexer.lexe();
        a_parser.parse();
        a_generator.generate();
        System.out.println(toString_end());
    }

    public String toString_begin(){
        return "Compiling a: "+ a_language + " program.";
    }

    public String toString_end(){
        return "Compilation complete";
    }
}