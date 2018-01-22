package ex1.compiler.factory;



import ex1.compiler.component.generator.AbstractGenerator;
import ex1.compiler.component.generator.GeneratorCpp;
import ex1.compiler.component.lexer.AbstractLexer;
import ex1.compiler.component.lexer.LexerCpp;
import ex1.compiler.component.parser.AbstractParser;
import ex1.compiler.component.parser.ParserCpp;

public class CompilerFactoryCpp extends AbstractCompilerFactory
{

    @Override
    public AbstractGenerator createGenerator()
    {
        return new GeneratorCpp();
    }

    @Override
    public AbstractLexer createLexer()
    {
        return new LexerCpp();
    }

    @Override
    public AbstractParser createParser()
    {
        return new ParserCpp();
    }

}