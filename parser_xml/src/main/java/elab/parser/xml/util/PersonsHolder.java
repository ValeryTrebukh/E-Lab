package elab.parser.xml.util;

import elab.parser.xml.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsHolder {

    private static List<Person> persons;

    public static void init(){
        persons = new ArrayList<>();
        persons.add(new Person(1, "Semen", "Kiev", 5000, "Bachelor"));
        persons.add(new Person(2, "Ivan", "Kiev", 14001, "Master"));
        persons.add(new Person(3, "Taras", "Kharkiv", 8900, "Ph.D"));
    }

    public static List<Person> getPersons() {
        return persons;
    }

    public static void setPersons(List<Person> persons) {
        PersonsHolder.persons = persons;
    }
}
