package service;

import model.Car;

import java.util.ArrayList;
import java.util.List;

public class ServiceCarImpl implements ServiceCar{

    @Override
    public List<Car> findSome() {
        return findThreeCar();
    }

    public List<Car> findThreeCar() {
        List<Car> cars=new ArrayList<>();
        String[] models={"BMW","Audi","Mercedes"};
        String[] colors={"black","White","black"};
        for(int i=0;i<3;i++){
            cars.add(new Car(i, models[i], colors[i]));
        }
        return cars;
    }
}
