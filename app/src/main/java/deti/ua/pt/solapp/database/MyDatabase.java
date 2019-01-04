package deti.ua.pt.solapp.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import deti.ua.pt.solapp.database.converter.DateConverter;
import deti.ua.pt.solapp.database.dao.CityDao;
import deti.ua.pt.solapp.database.dao.WeatherByLocalDao;
import deti.ua.pt.solapp.database.entity.City;
import deti.ua.pt.solapp.database.entity.WeatherByLocal;

@Database(entities = {WeatherByLocal.class, City.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {

    //Singleton
    private static volatile  MyDatabase INSTANCE;

    //DAO
    public abstract WeatherByLocalDao weatherByLocalDao();
    public abstract CityDao cityDao();

    public static MyDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                MyDatabase.class, "weatherDatabase").build();
                }
            }
        }
        return INSTANCE;
    }
}
