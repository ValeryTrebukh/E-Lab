package elab.parser.json;

import java.util.List;
import java.util.ResourceBundle;

import elab.parser.json.model.Currency;
import elab.parser.json.model.CurrencyHolder;
import elab.parser.json.model.MyJSONParser;
import elab.parser.json.utils.Helper;

public class Main {
    public static void main(String[] args) {
        ResourceBundle appRB = ResourceBundle.getBundle("app");
        String fileName = appRB.getString("file");
        String urlName = appRB.getString("url");

        MyJSONParser parser = new MyJSONParser();
        CurrencyHolder.setCurrencies(parser.parse(urlName));

        List<Currency> actual = CurrencyHolder.getActual("EUR", "USD", "RUB");

        Helper.saveToFile(fileName, actual);

        List<Currency> curs = Helper.loadFromFile(fileName);
    }

}
