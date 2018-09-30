package elab.parser.xml.model;

import elab.parser.xml.model.parsers.MyDOMParser;
import elab.parser.xml.model.parsers.MySAXParser;

public class ParserFactory {
    public static MyParser getParser(String name) {
        MyParser parser = null;
        switch (name) {
            case "dom":
                parser = new MyDOMParser();
                break;
            case "sax":
                parser = new MySAXParser();
                break;
        }
        return parser;
    }
}
