package app.cardealer.services;

import app.cardealer.models.binding.CarCreateBindingModel;
import app.cardealer.models.view.CarDetailsPartsViewModel;
import app.cardealer.models.view.CarDetailsViewModel;

import java.util.List;

public interface CarService {

    List<String> extractCarMakes();

    List<CarDetailsViewModel> extractAllCarsByMake(String make);

    CarDetailsPartsViewModel extractCarWithParts(Long id);

    List<CarDetailsViewModel> extractCars();

    void importCar(CarCreateBindingModel carBindingModel);
}
