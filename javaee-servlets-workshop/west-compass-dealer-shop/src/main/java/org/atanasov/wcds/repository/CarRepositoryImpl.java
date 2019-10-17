package org.atanasov.wcds.repository;

import org.atanasov.wcds.domain.entities.Car;

import javax.ejb.Stateless;

@Stateless
public class CarRepositoryImpl extends BaseCrudRepository<Car, String> implements CarRepository {

}
