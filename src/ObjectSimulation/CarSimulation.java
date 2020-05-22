package ObjectSimulation;



import CityMap.CitySimulation;
import Objects.Car;
import Objects.Road;
import Objects.Road.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CarSimulation {

    Road road;
    Car car;

    CarSimulation() {
        this.road = new Road("0", 1, 5, new int[]{0, 0}, Orientation.VERTICAL);
        this.car = new Car("0", this.road);
    }


    //test length of car
    @Test
    void getLength() {
        System.out.println("The length of the car is: " + car.getLength());
    }

    //test breadth of car
    @Test
    void getBreadth() {
        System.out.println("The breadth of the car is: " + car.getBreadth());
    }

    //test speed of car
    @Test
    void getSpeed() {
        System.out.println("The speed of the car will be: " + car.getSpeed());
    }

    //test starting position of car
    @Test
    void getPosition() {
        System.out.println("The starting position of the car is: " + car.getPosition());
    }

    //test new position when car travels a certain distance
    @Test
    void getNewPosition(){
        int distance = 15;
        System.out.println("If a car travelled a total distance of 15, its new position would be: " + car.getNewPosition(distance));

    }

    //test which road the car would be in
    @Test
    void getRoad() {
        System.out.println("The car is in " + road.getId());
    }

    @Test
    void getId() {
        System.out.println("The ID of each car is " + car.getId());
    }





}

