package com.pethoalpar.controller;

import com.pethoalpar.entities.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDTOController extends CrudRepository<Car, Long> {
}
