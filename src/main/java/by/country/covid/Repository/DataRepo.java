package by.country.covid.Repository;

import by.country.covid.Model.Data;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class DataRepo {

    @Value("${data_endpoint}")
    private  String dataUrl;

    //Getting data from API and extracting desirable data
    public List<Data> getList() throws JSONException, JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(dataUrl, String.class);

        JSONArray array =new JSONArray(response);
        String dataArray = array.toString();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.reader().forType(new TypeReference<List<Data>>(){});
        return objectReader.readValue(dataArray);
    }
}
