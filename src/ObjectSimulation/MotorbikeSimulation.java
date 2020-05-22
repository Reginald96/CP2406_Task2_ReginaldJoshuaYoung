package ObjectSimulation;

import Objects.Motorbike;
import Objects.Road;
import Objects.Road.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MotorbikeSimulation {
    Motorbike motorbike;

    MotorbikeSimulation() {
        this.motorbike = new Motorbike("0", new Road("0", 1, 5, new int[]{0, 0}, Orientation.VERTICAL));
    }

    //test length of motorbike
    @Test
    void getLength() {
        System.out.println("The length of each motorbike is: " + motorbike.getLength());
    }

    //test breadth of motorbike
    @Test
    void getBreadth(){
        System.out.println("The breadth of each motorbike is: " + motorbike.getBreadth());
    }

    //id of each motorbike
    @Test
    void getId() {
        System.out.println("The ID of each motorbike is " + motorbike.getId());
    }

    //test speed of bus
    @Test
    void getSpeed() {
        System.out.println("The speed of the motorbike will be: " + motorbike.getSpeed());
    }

    //test starting position of bus
    @Test
    void getPosition() {
        System.out.println("The starting position of the motorbike is: " + motorbike.getPosition());
    }

    //test new position when bus travels a certain distance
    @Test
    void getNewPosition(){
        int distance = 5;
        System.out.println("If a bus travelled a total distance of 30, its new position would be: " + motorbike.getNewPosition(distance));

    }
}
