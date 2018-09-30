package elab.parser.xml.util;

import elab.parser.xml.model.Person;

import java.io.*;
import java.util.List;
import java.util.ResourceBundle;

public class Helper {
    public static void printPersons(List<Person> persons) {
        printToConsole(persons);
        printToFile(persons);
    }

    private static void printToConsole(List<Person> persons) {
        for(Person p : persons) {
            System.out.println(p);
        }
    }

    private static void printToFile(List<Person> persons) {
        ResourceBundle appRB = ResourceBundle.getBundle("app");
        String fileName = appRB.getString("file.output");

        try(PrintWriter pw = new PrintWriter(new FileWriter(fileName, false))) {
            pw.write(persons.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
