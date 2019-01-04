package deti.ua.pt.solapp.Repositories;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

import deti.ua.pt.solapp.Parsers.WeatherByLocalParser;
import deti.ua.pt.solapp.database.MyDatabase;
import deti.ua.pt.solapp.database.dao.WeatherByLocalDao;
import deti.ua.pt.solapp.database.entity.WeatherByLocal;

public class WeatherRepository {

    private static int FRESH_TIMEOUT_IN_MINUTES = 15;
    private static String url_weather = "http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/";

    private WeatherByLocalDao weatherDao;
    private Application application;


    public WeatherRepository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        this.application = application;
        weatherDao = db.weatherByLocalDao();
    }

    public LiveData<WeatherByLocal> getWeather(final int globalIdLocal, final String forecastDate) {
        refreshWeather(globalIdLocal, forecastDate);
        return weatherDao.load(globalIdLocal, forecastDate);
    }

    private void refreshWeather(final int globalIdLocal, final String date) {
        String url_request = url_weather + globalIdLocal + ".json";

        RequestQueue queue = Volley.newRequestQueue(application.getApplicationContext());

        boolean weatherExists = (weatherDao.hasWeather(globalIdLocal, date, getMaxRefreshTime(new Date())).getValue() != null);

        if(!weatherExists) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_request, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        WeatherByLocal[] weatherByLocalsArray = WeatherByLocalParser.parse(response.toString());
                        for(int i = 0; i < weatherByLocalsArray.length; i++) {
                            WeatherByLocal weather = weatherByLocalsArray[i];
                            weather.setLastRefresh(new Date());
                            insert(weather);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volley error", error.getMessage());

                }
            });

            queue.add(jsonObjectRequest);
        }

    }

    private Date getMaxRefreshTime(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }

    private void insert(WeatherByLocal weather) {
        new insertAsyncTask(weatherDao).execute(weather);
    }

    private static class insertAsyncTask extends AsyncTask<WeatherByLocal, Void, Void> {

        private WeatherByLocalDao mAsyncTaskDao;

        insertAsyncTask(WeatherByLocalDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final WeatherByLocal... params) {
            mAsyncTaskDao.save(params[0]);
            return null;
        }
    }

}
