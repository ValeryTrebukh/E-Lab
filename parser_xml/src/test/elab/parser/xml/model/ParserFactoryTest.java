package elab.parser.xml.model;

import elab.parser.xml.model.parsers.MyDOMParser;
import elab.parser.xml.model.parsers.MySAXParser;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserFactoryTest {

    @Test
    public void getParser() {
        assertEquals(MyDOMParser.class, ParserFactory.getParser("dom").getClass());
        assertEquals(MySAXParser.class, ParserFactory.getParser("sax").getClass());
    }

    @Test
    public void getParserNotFound() {
        assertNull(ParserFactory.getParser("dom2"));
    }
}