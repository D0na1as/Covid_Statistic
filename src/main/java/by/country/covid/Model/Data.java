package by.country.covid.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//Object for own API
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    @JsonProperty("country")
    private String country;
    @JsonProperty("indicator")
    private String indicator;
    @JsonProperty("year_week")
    private String week;
    @JsonProperty("weekly_count")
    private int count;

    public Data() {
    }

    public Data(String country, String indicator, String week, int count) {
        this.country = country;
        this.indicator = indicator;
        this.week = week;
        this.count = count;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
