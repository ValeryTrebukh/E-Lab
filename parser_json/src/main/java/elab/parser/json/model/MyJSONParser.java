package elab.parser.json.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MyJSONParser {

    public List<Currency> parse(String url) {
        ObjectMapper mapper = new ObjectMapper();
        List<Currency> currencies = null;
        try {
            currencies = mapper.readValue(new URL(url), new TypeReference<List<Currency>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencies;
    }
}
