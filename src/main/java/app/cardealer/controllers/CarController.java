package app.cardealer.controllers;

import app.cardealer.models.view.CarDetailsViewModel;
import app.cardealer.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController extends BaseController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public ModelAndView allCarMakesIndex(ModelAndView modelAndView) {
        List<String> allCarMakes = this.carService.extractCarMakes();
        modelAndView.addObject("carMakes", allCarMakes);

        return view("cars/all", modelAndView);
    }

    @GetMapping("/{make}")
    public ModelAndView carDetails(@PathVariable String make, ModelAndView modelAndView) {
        List<CarDetailsViewModel> cars = this.carService.extractAllCarsByMake(make);

        modelAndView.addObject("cars", cars);

        return super.view("cars/cars-make-models", modelAndView);
    }
}