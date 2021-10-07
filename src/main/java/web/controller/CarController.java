package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.List;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String carPage(@RequestParam(defaultValue = "5") int amount, Model model) {
        CarService cs = new CarService();
        List<Car> list = cs.getCars(amount);
        model.addAttribute("cars", list);
        return "cars";
    }
}
