package deti.ua.pt.solapp.ViewModel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import deti.ua.pt.solapp.Repositories.WeatherRepository;
import deti.ua.pt.solapp.database.entity.WeatherByLocal;

public class WeatherViewModel extends AndroidViewModel {

    private WeatherRepository weatherRepo;
    private LiveData<WeatherByLocal> weather;

    public WeatherViewModel (Application application) {
        super(application);
        this.weatherRepo = new WeatherRepository(application);
    }

    public void init(int globalIdLocal, String date) {
        weather = weatherRepo.getWeather(globalIdLocal, date);
    }

    public LiveData<WeatherByLocal> getWeather() {
        return this.weather;
    }

}
