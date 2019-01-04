package deti.ua.pt.solapp.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class City {

    @PrimaryKey
    private int globalIdLocal;
    private String city;

    public City(int globalIdLocal, String city) {
        this.globalIdLocal = globalIdLocal;
        this.city = city;
    }

    public int getGlobalIdLocal() {
        return globalIdLocal;
    }

    public void setGlobalIdLocal(int globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
