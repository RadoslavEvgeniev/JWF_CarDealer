package app.cardealer.services;

import app.cardealer.models.view.CarDetailsViewModel;

import java.util.List;

public interface CarService {

    List<String> extractCarMakes();

    List<CarDetailsViewModel> extractAllCarsByMake(String make);
}