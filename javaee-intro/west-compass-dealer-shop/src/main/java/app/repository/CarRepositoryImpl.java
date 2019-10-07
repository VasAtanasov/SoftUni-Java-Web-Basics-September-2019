package app.repository;

import app.domain.entities.Car;

import javax.ejb.Stateless;

@Stateless
public class CarRepositoryImpl extends BaseCrudRepository<Car, String> implements CarRepository {

}
