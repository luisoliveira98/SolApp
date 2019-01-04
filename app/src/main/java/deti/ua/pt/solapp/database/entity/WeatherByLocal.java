package deti.ua.pt.solapp.database.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "weather", indices = {@Index(value = {"lastRefresh"}, unique = true)})
public class WeatherByLocal {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int globalIdLocal;
    private String precipitaProb;
    private String tMin;
    private String tMax;
    private String predWindDir;
    private int idWeatherType;
    private String forecastDate;
    private Date lastRefresh;


    @Ignore
    public WeatherByLocal(int globalIdLocal, String precipitaProb, String tMin, String tMax, String predWindDir, int idWeatherType, String forecastDate) {
        this.globalIdLocal = globalIdLocal;
        this.precipitaProb = precipitaProb;
        this.tMin = tMin;
        this.tMax = tMax;
        this.predWindDir = predWindDir;
        this.idWeatherType = idWeatherType;
        this.forecastDate = forecastDate;
    }

    /**
     * Construtor usado pelo room
     */
    public WeatherByLocal(int id, int globalIdLocal, String precipitaProb, String tMin, String tMax, String predWindDir, int idWeatherType, String forecastDate) {
        this.id = id;
        this.globalIdLocal = globalIdLocal;
        this.precipitaProb = precipitaProb;
        this.tMin = tMin;
        this.tMax = tMax;
        this.predWindDir = predWindDir;
        this.idWeatherType = idWeatherType;
        this.forecastDate = forecastDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGlobalIdLocal() {
        return globalIdLocal;
    }

    public void setGlobalIdLocal(int globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }

    public String getPrecipitaProb() {
        return precipitaProb;
    }

    public void setPrecipitaProb(String precipitaProb) {
        this.precipitaProb = precipitaProb;
    }

    public String getTMin() {
        return tMin;
    }

    public void settMin(String tMin) {
        this.tMin = tMin;
    }

    public String getTMax() {
        return tMax;
    }

    public void settMax(String tMax) {
        this.tMax = tMax;
    }

    public String getPredWindDir() {
        return predWindDir;
    }

    public void setPredWindDir(String predWindDir) {
        this.predWindDir = predWindDir;
    }

    public int getIdWeatherType() {
        return idWeatherType;
    }

    public void setIdWeatherType(int idWeatherType) {
        this.idWeatherType = idWeatherType;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public Date getLastRefresh() {
        return this.lastRefresh;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    @Override
    public String toString() {
        return "WeatherByLocal{" +
                "id=" + id +
                ", globalIdLocal=" + globalIdLocal +
                ", precipitaProb='" + precipitaProb + '\'' +
                ", tMin='" + tMin + '\'' +
                ", tMax='" + tMax + '\'' +
                ", predWindDir='" + predWindDir + '\'' +
                ", idWeatherType=" + idWeatherType +
                ", forecastDate='" + forecastDate + '\'' +
                ", lastRefresh=" + lastRefresh +
                '}';
    }
}
