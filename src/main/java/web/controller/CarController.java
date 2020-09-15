package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import model.Car;

import java.util.List;


@Controller
public class CarController {
    @Autowired
    List<Car> CarList;

    @GetMapping(value = "/cars")
    public String printCar(@RequestParam(defaultValue = "test") String locale, ModelMap model) {
        model.addAttribute("carslist", CarList);
        return "cars";
    }

}

