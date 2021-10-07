package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarService {

    private List<Car> cars = new ArrayList<>();

    {
        cars.add(new Car("Toyota", "Land Cruiser", "White"));
        cars.add(new Car("Mazda", "6", "Red"));
        cars.add(new Car("Honda", "Civic", "Black"));
        cars.add(new Car("Volkswagen", "Touareg", "Blue"));
        cars.add(new Car("Mitsubishi", "Lanser", "Silver"));
    }

    public List<Car> getCars(int amount) {
        return cars.stream().limit(amount).toList();
    }
}