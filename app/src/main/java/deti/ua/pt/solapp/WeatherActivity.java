package deti.ua.pt.solapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import deti.ua.pt.solapp.Parsers.WeatherByLocalParser;
import deti.ua.pt.solapp.ViewModel.WeatherViewModel;
import deti.ua.pt.solapp.database.entity.WeatherByLocal;

public class WeatherActivity extends AppCompatActivity {

    //attributes needed to search in database
    private String city;
    private String date;
    private int globalIdLocal;

    //UI components
    private TextView local,
                    forecastDate,
                    temperature,
                    windDirection,
                    weatherDescription;

    //ViewModel
    private WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        city = (String) getIntent().getExtras().getSerializable("city");
        date = (String) getIntent().getExtras().getSerializable("date");
        globalIdLocal = (int) getIntent().getExtras().getSerializable("cityId");

        //Initialize UI components
        local = (TextView) findViewById(R.id.textCity);
        forecastDate = (TextView) findViewById(R.id.textDate);
        temperature = (TextView)findViewById(R.id.textTemperature);
        windDirection = (TextView) findViewById(R.id.textWindDirection);
        weatherDescription = (TextView) findViewById(R.id.textWeatherDescription);

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.init(globalIdLocal, date);

        weatherViewModel.getWeather().observe(this, weather -> {
            local.setText(city);
            forecastDate.setText(weather.getForecastDate());
            temperature.setText("TMin: " + weather.getTMin() + " - TMax: " + weather.getTMax());
            windDirection.setText(weather.getPredWindDir());
        });

    }


}
