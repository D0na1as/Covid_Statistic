package by.country.covid.Controller;

import by.country.covid.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Manages Frontend page fetching
@Controller
public class MainController {

    @Autowired
    private DataService service;

    //Get main page
    @GetMapping("/")
    public String getMainPage()  {
        return "index";
    }
}
