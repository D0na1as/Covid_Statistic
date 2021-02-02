package by.country.covid.Controller;

import by.country.covid.Model.Country;
import by.country.covid.Service.DataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//API controller
@RestController
@RequestMapping("/statistic")
public class ApiController {

    @Autowired
    private DataService service;

    //Get list
    @RequestMapping (value = "/{country}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStatistic(@PathVariable("country") String country ) throws JsonProcessingException {
        country = country.replace("+", "/");
        Country data = service.getStatistic(country);
        if (data!=null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Get list of places that data is provided for
    @RequestMapping (value = "/places", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPlaces() throws JsonProcessingException {
        List<String> data = service.getPlaces();
        if (data!=null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
