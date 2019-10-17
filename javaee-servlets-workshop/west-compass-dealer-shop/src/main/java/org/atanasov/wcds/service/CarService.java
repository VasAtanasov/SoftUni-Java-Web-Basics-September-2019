package org.atanasov.wcds.service;

import org.atanasov.wcds.domain.models.service.CarCreateServiceModel;
import org.atanasov.wcds.domain.models.service.CarServiceModel;

import java.util.List;

public interface CarService {
    List<CarServiceModel> findAll();

    String create(CarCreateServiceModel car);
}
