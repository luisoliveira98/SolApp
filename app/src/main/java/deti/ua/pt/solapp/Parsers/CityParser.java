package deti.ua.pt.solapp.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import deti.ua.pt.solapp.database.entity.City;

public class CityParser {

    private static String IPMA_DATA = "data";

    private static String IPMA_GLOBALIDLOCAL = "globalIdLocal";
    private static String IPMA_LOCAL = "local";

    private City[] fromJson(JSONObject citiesJSON) throws JSONException {
        JSONArray citiesArray = citiesJSON.getJSONArray(IPMA_DATA);

        City[] cities = new City[citiesArray.length()];

        for(int i = 0; i < citiesArray.length(); i++) {
            JSONObject city = citiesArray.getJSONObject(i);
            City local = cityFromJson(city);
            cities[i] = local;
        }

        return cities;
    }

    private City cityFromJson(JSONObject city) throws JSONException {
        int globalIdLocal = city.getInt(IPMA_GLOBALIDLOCAL);
        String local = city.getString(IPMA_LOCAL);

        return new City(globalIdLocal, local);
    }
}
