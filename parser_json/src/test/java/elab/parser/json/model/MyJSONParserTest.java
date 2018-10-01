package elab.parser.json.model;

import elab.parser.json.utils.Helper;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MyJSONParserTest {

    @Test
    public void parse() {
        MyJSONParser parser= new MyJSONParser();
        List<Currency> currencies = parser.parse("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
        assertTrue(currencies.size() > 3);

        CurrencyHolder.setCurrencies(currencies);
        assertTrue(CurrencyHolder.getActual("EUR", "USD", "RUB").size() == 3);

        Helper.saveToFile("C:\\Users\\Valerii_Trebukh\\java\\parser_json\\test\\stored.txt", CurrencyHolder.getActual("EUR", "USD", "RUB"));
        assertTrue(Helper.loadFromFile("C:\\Users\\Valerii_Trebukh\\java\\parser_json\\test\\stored.txt").size() == 3);
    }
}