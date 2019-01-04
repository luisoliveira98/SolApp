package deti.ua.pt.solapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import deti.ua.pt.solapp.database.entity.City;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CityDao {

    @Insert(onConflict = REPLACE)
    void save(City city);

    @Query("SELECT * FROM city")
    LiveData<City> load();

    @Query("SELECT * FROM city WHERE globalIdLocal = :globalIdLocal")
    LiveData<City> loadById(int globalIdLocal);
}
