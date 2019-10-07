package app.service;

import app.domain.entities.Car;
import app.domain.models.binding.CarCreateBindingModel;
import app.domain.models.view.CarDetailsViewModel;
import app.repository.CarRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {

    private final ModelMapper modelMapper;
    private final CarRepository carRepository;

    @Inject
    public CarServiceImpl(ModelMapper modelMapper, CarRepository carRepository) {
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDetailsViewModel> findAll() {
        return carRepository
                .stream()
                .map(car -> this.modelMapper.map(car, CarDetailsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public String create(CarCreateBindingModel carCreateBindingModel) {
        Car car = this.modelMapper.map(carCreateBindingModel, Car.class);
        this.carRepository.save(car);
        return car.getId();
    }
}
