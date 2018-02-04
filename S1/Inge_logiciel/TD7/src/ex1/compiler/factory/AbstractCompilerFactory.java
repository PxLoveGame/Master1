package ex1.compiler.factory;

import ex1.compiler.component.generator.AbstractGenerator;
import ex1.compiler.component.lexer.AbstractLexer;
import ex1.compiler.component.parser.AbstractParser;

public abstract class AbstractCompilerFactory
{
    public abstract AbstractLexer createLexer();
    public abstract AbstractParser createParser();
    public abstract AbstractGenerator createGenerator();

    public static AbstractCompilerFactory getFactory(String p_language) throws Exception
    {
        switch(p_language.toLowerCase())
        {
            case "java":
                return new CompilerFactoryJava();

            case "cpp":
            case "c++":
                return new CompilerFactoryCpp();

            default:
                throw new Exception("Non supported Language : "+ p_language +", Extend the framework to support it");
        }

    }
}
