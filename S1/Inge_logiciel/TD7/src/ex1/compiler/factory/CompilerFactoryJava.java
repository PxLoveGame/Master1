package ex1.compiler.factory;

import ex1.compiler.component.generator.AbstractGenerator;
import ex1.compiler.component.generator.GeneratorJava;
import ex1.compiler.component.lexer.AbstractLexer;
import ex1.compiler.component.lexer.LexerJava;
import ex1.compiler.component.parser.AbstractParser;
import ex1.compiler.component.parser.ParserJava;

public class CompilerFactoryJava extends AbstractCompilerFactory
{

    @Override
    public AbstractGenerator createGenerator()
    {
        return new GeneratorJava();
    }

    @Override
    public AbstractLexer createLexer()
    {
        return new LexerJava();
    }

    @Override
    public AbstractParser createParser()
    {
        return new ParserJava();
    }

}