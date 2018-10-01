package elab.parser.json.utils;

import elab.parser.json.model.Currency;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static void saveToFile(String fileName, List<Currency> currencies) {

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for(Currency c : currencies) {
                oos.writeObject(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Currency> loadFromFile(String fileName) {
        List<Currency> currencies = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while(true) {
                Currency c;
                try{
                    c = (Currency)ois.readObject();
                    currencies.add(c);
                } catch (EOFException ignore) {
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("cnf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencies;
    }
}
