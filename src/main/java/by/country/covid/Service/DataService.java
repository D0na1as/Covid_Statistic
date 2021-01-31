package by.country.covid.Service;

import by.country.covid.Model.Country;
import by.country.covid.Model.Data;
import by.country.covid.Repository.DataRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataService {

    @Autowired
    DataRepo dataRepo;

    //Get all date from public endpoint
    @Cacheable("list")
    public Map<String, Country> getList() throws JsonProcessingException {

        Map<String, Country> map = new HashMap<>();

        for (Data data : dataRepo.getList()) {

            if (map.get(data.getCountry())==null) {
                Country country = new Country();
                if (data.getIndicator().equals("cases")) {
                    country.addCaseValue(data.getWeek(), data.getCount());
                } else if (data.getIndicator().equals("deaths")) {
                    country.addDeathValue(data.getWeek(), data.getCount());
                }
                map.put(data.getCountry(), country);

            } else if (map.get(data.getCountry())!=null) {
                Country country = map.get(data.getCountry());
                if (data.getIndicator().equals("cases")) {
                    country.addCaseValue(data.getWeek(), data.getCount());
                } else if (data.getIndicator().equals("deaths")) {
                    country.addDeathValue(data.getWeek(), data.getCount());
                }
                map.put(data.getCountry(), country);
            }
        }
        return map;
    }

    //Return desired country statistic of cases and deaths by date

    public Country getStatistic (String country) throws JsonProcessingException {
        return getList().get(country);
    }

    public List<String> getPlaces() throws JsonProcessingException {
        List<String> keys = new ArrayList<>(getList().keySet());
        keys.sort(Collator.getInstance());
        return keys;
    }
}
