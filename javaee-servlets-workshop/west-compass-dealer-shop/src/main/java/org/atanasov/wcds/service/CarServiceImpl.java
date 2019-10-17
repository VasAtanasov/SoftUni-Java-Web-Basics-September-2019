package org.atanasov.wcds.service;

import org.atanasov.wcds.domain.entities.Car;
import org.atanasov.wcds.domain.entities.User;
import org.atanasov.wcds.domain.enums.Engine;
import org.atanasov.wcds.domain.models.service.CarCreateServiceModel;
import org.atanasov.wcds.domain.models.service.CarServiceModel;
import org.atanasov.wcds.repository.CarRepository;
import org.atanasov.wcds.util.ModelMapperWrapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {

    private final ModelMapperWrapper modelMapper;
    private final CarRepository carRepository;

    @Inject
    public CarServiceImpl(ModelMapperWrapper modelMapper, CarRepository carRepository) {
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
    }

    @Override
    public List<CarServiceModel> findAll() {
        return carRepository
                .findAll()
                .stream()
                .map(car -> modelMapper.map(car, CarServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public String create(CarCreateServiceModel serviceModel) {
        Car car = modelMapper.map(serviceModel, Car.class);

        User user = new User();
        user.setId(serviceModel.getUser());

        car.setUser(user);
        car.setEngine(Engine.fromString(serviceModel.getEngine()));

        this.carRepository.save(car);
        return car.getId();
    }
}
