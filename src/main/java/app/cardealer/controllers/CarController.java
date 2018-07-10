package app.cardealer.controllers;

import app.cardealer.models.binding.CarCreateBindingModel;
import app.cardealer.models.view.CarDetailsPartsViewModel;
import app.cardealer.models.view.CarDetailsViewModel;
import app.cardealer.models.view.PartViewModel;
import app.cardealer.services.CarService;
import app.cardealer.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController extends BaseController {

    private CarService carService;
    private PartService partService;

    @Autowired
    public CarController(CarService carService, PartService partService) {
        this.carService = carService;
        this.partService = partService;
    }

    @GetMapping("")
    public ModelAndView allCarMakesIndex(ModelAndView modelAndView) {
        List<String> allCarMakes = this.carService.extractCarMakes();
        modelAndView.addObject("carMakes", allCarMakes);

        return view("cars/cars-index", modelAndView);
    }

    @GetMapping("/{make}")
    public ModelAndView carDetails(@PathVariable String make, ModelAndView modelAndView) {
        List<CarDetailsViewModel> cars = this.carService.extractAllCarsByMake(make);

        modelAndView.addObject("cars", cars);

        return super.view("cars/cars-makes-list", modelAndView);
    }

    @GetMapping("/{id}/parts")
    public ModelAndView carParts(@PathVariable Long id, ModelAndView modelAndView) {
        CarDetailsPartsViewModel car = this.carService.extractCarWithParts(id);

        modelAndView.addObject("car", car);

        return super.view("cars/cars-details", modelAndView);
    }

    @GetMapping("/add")
    public ModelAndView carCreate(ModelAndView modelAndView, HttpServletRequest request) {
        if (request.getSession().getAttribute("user-id") == null) {
            return super.redirect("/users/login");
        }

        List<CarDetailsViewModel> cars = this.carService.extractCars();
        List<PartViewModel> parts = this.partService.extractParts();

        modelAndView.addObject("cars", cars);
        modelAndView.addObject("parts", parts);

        return super.view("cars/cars-create", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView carCreateConfirm(@ModelAttribute CarCreateBindingModel carBindingModel) {
        this.carService.importCar(carBindingModel);

        return super.redirect("/");
    }
}
