package elab.parser.json.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyHolder {
    private static Map<String, Currency> currencies;

    public static Map<String, Currency> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(Map<String, Currency> currencies) {
        CurrencyHolder.currencies = currencies;
    }

    public static void setCurrencies(List<Currency> list) {
        currencies = new HashMap<>();
        for(Currency c : list) {
            currencies.put(c.getCc(), c);
        }
    }

    public static List<Currency> getActual(String... names) {
        List<Currency> actual = new ArrayList<>();
        for(String s : names) {
            actual.add(currencies.get(s));
        }
        return actual;
    }
}
