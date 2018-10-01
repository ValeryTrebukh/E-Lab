package elab.parser.xml.model.parsers;

import elab.parser.xml.util.PersonsHolder;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class MySAXParserTest {

    @Before
    public void setUp() throws Exception {
        PersonsHolder.setPersons(Collections.emptyList());
    }

    @Test
    public void parseXMLFileDOM() {
        MyDOMParser parser = new MyDOMParser();
        parser.parseXMLFile("C:\\Users\\Valerii_Trebukh\\java\\parser_xml\\test\\input.xml");
        assertEquals(3, PersonsHolder.getPersons().size());
    }

    @Test
    public void parseXMLFileSAX() {
        MySAXParser parser = new MySAXParser();
        parser.parseXMLFile("C:\\Users\\Valerii_Trebukh\\java\\parser_xml\\test\\input.xml");
        assertEquals(3, PersonsHolder.getPersons().size());
    }
}