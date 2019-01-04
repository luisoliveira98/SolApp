package deti.ua.pt.solapp.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

import deti.ua.pt.solapp.database.entity.WeatherByLocal;

public class WeatherByLocalParser {
    private static final String IPMA_DATA = "data";

    private static final String IPMA_PRECIPITAPROB = "precipitaProb";
    private static final String IPMA_TMIN = "tMin";
    private static final String IPMA_TMAX = "tMax";
    private static final String IPMA_FORECASTDATE = "forecastDate";
    private static final String IPMA_PREDWINDDIR = "predWindDir";
    private static final String IPMA_IDWEATHERTYPE = "idWeatherType";
    private static final String IPMA_GLOBALIDLOCAL = "globalIdLocal";

    private static WeatherByLocal[] fromJson(final JSONObject forecastJson) throws JSONException {
        JSONArray jsonWeatherArray = forecastJson.getJSONArray(IPMA_DATA);
        WeatherByLocal[] weatherEntries = new WeatherByLocal[jsonWeatherArray.length()];
        int globalIdLocal = forecastJson.getInt(IPMA_GLOBALIDLOCAL);

        for (int i = 0; i<jsonWeatherArray.length(); i++) {
            JSONObject dayForecast = jsonWeatherArray.getJSONObject(i);
            WeatherByLocal weather = fromJson(dayForecast, globalIdLocal);
            weatherEntries[i] = weather;
        }

        return weatherEntries;
    }

    private static WeatherByLocal fromJson(final JSONObject dayForcast, int globalIdLocal) throws JSONException {
        String precipitaProb = dayForcast.getString(IPMA_PRECIPITAPROB);
        String tMin = dayForcast.getString(IPMA_TMIN);
        String tMax = dayForcast.getString(IPMA_TMAX);
        String predWindDir = dayForcast.getString(IPMA_PREDWINDDIR);
        int idWeatherType = dayForcast.getInt(IPMA_IDWEATHERTYPE);
        String forecastDate = dayForcast.getString(IPMA_FORECASTDATE);
        return new WeatherByLocal(globalIdLocal, precipitaProb, tMin, tMax, predWindDir, idWeatherType, forecastDate);
    }

    public static WeatherByLocal[] parse(final String forecastJsonStr) throws JSONException {
        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        return fromJson(forecastJson);
    }
}
