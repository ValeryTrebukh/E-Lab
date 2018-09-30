package elab.parser.xml.model.parsers;

import elab.parser.xml.model.MyParser;
import elab.parser.xml.model.Person;
import elab.parser.xml.util.PersonsHolder;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MySAXParser implements MyParser {

    private List<Person> persons = new ArrayList<>();

    public void parseXMLFile(String fileName) throws SAXException, IOException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        MyHandler handler = new MyHandler();
        reader.setContentHandler(handler);
        reader.parse(fileName);
        PersonsHolder.setPersons(persons);
    }

    private class MyHandler extends DefaultHandler {
        private Person person;
        private int code;

        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

            switch (qName) {
                case "person":
                    person = new Person();
                    person.setId(Integer.valueOf(atts.getValue("id")));
                    code = 0;
                    break;
                case "name":
                    code = 1;
                    break;
                case "address":
                    code = 2;
                    break;
                case "cash":
                    code = 3;
                    break;
                case "education":
                    code = 4;
                    break;
                default:
                    code = 0;
            }
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            if(qName.equals("person")) {
                persons.add(person);
                code = 0;
            }
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            switch (code) {
                case 1:
                    person.setName(new String(ch, start, length));
                    break;
                case 2:
                    person.setAddress(new String(ch, start, length));
                    break;
                case 3:
                    person.setCash(Double.valueOf(new String(ch, start, length)));
                    break;
                case 4:
                    person.setEducation(new String(ch, start, length));
                    break;
                default:
                    break;
            }
        }
    }
}
