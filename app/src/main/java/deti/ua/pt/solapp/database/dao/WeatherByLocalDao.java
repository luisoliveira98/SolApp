package deti.ua.pt.solapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.Date;

import deti.ua.pt.solapp.database.entity.WeatherByLocal;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface WeatherByLocalDao {

    @Insert(onConflict = REPLACE)
    void save(WeatherByLocal weather);

    @Query("SELECT * FROM weather WHERE globalIdLocal = :globalIdLocal AND forecastDate = :forecastDate LIMIT 1")
    LiveData<WeatherByLocal> load(int globalIdLocal, String forecastDate);

    @Query("SELECT * FROM weather WHERE globalIdLocal = :IdLocal AND forecastDate = :date AND lastRefresh > :lastRefreshMax LIMIT 1")
    LiveData<WeatherByLocal> hasWeather(int IdLocal, String date, Date lastRefreshMax);

}
