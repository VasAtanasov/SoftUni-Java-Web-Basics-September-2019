package app.service;

import app.domain.models.binding.CarCreateBindingModel;
import app.domain.models.view.CarDetailsViewModel;

import java.util.List;

public interface CarService {
    List<CarDetailsViewModel> findAll();

    String create(CarCreateBindingModel car);
}
