package by.country.covid.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

//Model for external API
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Country {

    @JsonProperty("country")
    private String name;
    //Key is day, cases value
    @JsonProperty("cases")
    private Map<String, Integer> cases;
    //Key is day, cases value
    @JsonProperty("deaths")
    private Map<String, Integer> deaths;

    public Country() {
        this.cases = new LinkedHashMap<>();
        this.deaths = new LinkedHashMap<>();
    }

    public Country(String name, Map<String, Integer> cases, Map<String, Integer> deaths) {
        this.name = name;
        this.cases = cases;
        this.deaths = deaths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getCases() {
        return cases;
    }

    public void setCases(Map<String, Integer> cases) {
        this.cases = cases;
    }

    public Map<String, Integer> getDeaths() {
        return deaths;
    }

    public void setDeaths(Map<String, Integer> deaths) {
        this.deaths = deaths;
    }

    public void addCaseValue (String date, int cases) {
        this.cases.put(date, cases);
    }

    public void addDeathValue (String date, int cases) {
        this.deaths.put(date, cases);
    }

    public void getCaseValue (String date) {
        this.cases.get(date);
    }

    public void getDeathValue (String date) {
        this.deaths.get(date);
    }
}
