package elab.parser.xml.controller;

import elab.parser.xml.model.*;
import elab.parser.xml.model.parsers.MyDOMParser;
import elab.parser.xml.model.MyParser;
import elab.parser.xml.model.ParserFactory;
import elab.parser.xml.util.Helper;
import elab.parser.xml.util.PersonsHolder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller {

    public void run() {
        ResourceBundle appRB = ResourceBundle.getBundle("app");
        String fileName = appRB.getString("file.input");
        String parserName = appRB.getString("parser");

        PersonsHolder.init();
        List<Person> list = PersonsHolder.getPersons();

        try {
            // write collection to xml file
            MyDOMParser domParser = new MyDOMParser();
            domParser.createXMLFile(list, fileName);

            // parse xml file
            MyParser parser = ParserFactory.getParser(parserName);
            parser.parseXMLFile(fileName);

            // print selected data
            Helper.printPersons(PersonsHolder.getPersons().stream().filter(p -> p.getCash()>10000).collect(Collectors.toList()));

        } catch (TransformerException | ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
