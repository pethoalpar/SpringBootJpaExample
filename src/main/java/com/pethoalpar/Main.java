package com.pethoalpar;

import com.pethoalpar.controller.CarDTOController;
import com.pethoalpar.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner{

    @Autowired
    private CarDTOController carController;

    public static void main(String [] args){
        SpringApplication.run(Main.class, args);
    }

    public void run(String... strings) throws Exception {
        Car car = new Car();
        car.setColour("Grey");
        car.setHorsePower(105);
        car.setModel("VW golf 16");
        car.setYear(2048);

        carController.save(car);

        Iterable<Car> cars = carController.findAll();

        for(Car c : cars){
            System.out.println("Car:"+c.getModel()+" "+c.getYear()+" "+c.getHorsePower());
            c.setHorsePower(c.getHorsePower()+10);
        }

        carController.save(cars);

        cars = carController.findAll();

        for(Car c : cars){
            System.out.println("Car:"+c.getModel()+" "+c.getYear()+" "+c.getHorsePower());
        }

        //carController.delete(cars);

        cars = carController.findAll();

        for(Car c : cars){
            System.out.println("Car:"+c.getModel()+" "+c.getYear()+" "+c.getHorsePower());
        }
    }
}
