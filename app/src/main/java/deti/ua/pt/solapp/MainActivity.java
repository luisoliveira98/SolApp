package deti.ua.pt.solapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import deti.ua.pt.solapp.Parsers.WeatherByLocalParser;
import deti.ua.pt.solapp.database.entity.WeatherByLocal;

public class MainActivity extends AppCompatActivity {

    private Button consultWeather;
    private Button getTodayDate;
    private TextView date;
    private Spinner city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize UI components
        consultWeather = (Button) findViewById(R.id.button_weather);
        getTodayDate = (Button) findViewById(R.id.button_getoday);
        date = (TextView) findViewById(R.id.editDate);
        city = (Spinner) findViewById(R.id.spinnerCity);


        //fill spinner
        ArrayAdapter<CharSequence> cities = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
        cities.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        city.setAdapter(cities);

    }

    public void getWeather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        String d = date.getText().toString();
        String c = city.getSelectedItem().toString();
        intent.putExtra("date", d);
        intent.putExtra("city", c);
        startActivity(intent);
    }

    public void getTodayDate(View view) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        date.setText(df.format(d));
    }


}
