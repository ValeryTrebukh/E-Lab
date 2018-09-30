package elab.parser.xml.model;

import org.xml.sax.SAXException;

import java.io.IOException;

public interface MyParser {
    void parseXMLFile(String fileName) throws SAXException, IOException;
}
