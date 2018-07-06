package app.cardealer.services;

import app.cardealer.entites.Car;
import app.cardealer.models.view.CarDetailsViewModel;
import app.cardealer.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> extractCarMakes() {
        return this.carRepository.extractCarMakes();
    }

    @Override
    public List<CarDetailsViewModel> extractAllCarsByMake(String make) {
        List<Car> carsFromDb = this.carRepository.findAllByMakeOrderByMakeAscTravelledDistanceDesc(make);
        List<CarDetailsViewModel> cars = new ArrayList<>();

        for (Car car : carsFromDb) {
            CarDetailsViewModel carViewModel = this.modelMapper.map(car, CarDetailsViewModel.class);
            cars.add(carViewModel);
        }

        return cars;
    }
}
