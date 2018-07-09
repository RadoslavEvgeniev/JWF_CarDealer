package app.cardealer.services;

import app.cardealer.entites.Car;
import app.cardealer.entites.Part;
import app.cardealer.models.binding.CarCreateBindingModel;
import app.cardealer.models.view.CarDetailsPartsViewModel;
import app.cardealer.models.view.CarDetailsViewModel;
import app.cardealer.repositories.CarRepository;
import app.cardealer.repositories.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private PartRepository partRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
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

    @Override
    public CarDetailsPartsViewModel extractCarWithParts(Long id) {
        Car carFromDb = this.carRepository.findById(id).orElse(null);
        if (carFromDb == null) {
            return null;
        }

        CarDetailsPartsViewModel carViewModel = this.modelMapper.map(carFromDb, CarDetailsPartsViewModel.class);
        return carViewModel;
    }

    @Override
    public List<CarDetailsViewModel> extractCars() {
        List<Car> carsFromDb = this.carRepository.findAllCarsGroupedByModel();
        List<CarDetailsViewModel> cars = new ArrayList<>();

        for (Car car : carsFromDb) {
            CarDetailsViewModel carViewModel = this.modelMapper.map(car, CarDetailsViewModel.class);

            cars.add(carViewModel);
        }

        return cars;
    }

    @Override
    public void importCar(CarCreateBindingModel carBindingModel) {
        Car car = this.prepareCarForDb(carBindingModel);

        this.carRepository.save(car);
    }

    private Car prepareCarForDb(CarCreateBindingModel carBindingModel) {
        Car car = new Car();
        String[] carParams = carBindingModel.getCarMakeAndModel().split(" : ");
        car.setMake(carParams[0]);
        car.setModel(carParams[1]);
        car.setTravelledDistance(carBindingModel.getTravelledDistance());

        for (Long id : carBindingModel.getCarParts()) {
            Part partFromDb = this.partRepository.findById(id).orElse(null);
            partFromDb.getCars().add(car);
            car.getParts().add(partFromDb);
        }

        return car;
    }
}
