package ObjectSimulation;

import Objects.Bus;
import Objects.Road;
import Objects.Road.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BusSimulation {
    Road road;
    Bus bus;

    BusSimulation() {
        this.road = new Road("0", 1, 5, new int[]{0, 0}, Orientation.VERTICAL);
        this.bus = new Bus("0", this.road);
    }

    @Test
    void getLength() {
        //Assertions.assertEquals(12, this.bus.getLength());
        System.out.println("The length of each bus is: " + bus.getLength());
    }

    @Test
    void getBreadth(){
        System.out.println("The breadth of each bus is: " + bus.getBreadth());
    }

    @Test
    void getId() {
        System.out.println("The ID of each bus is " + bus.getId());
    }

    //test speed of bus
    @Test
    void getSpeed() {
        System.out.println("The speed of the bus will be: " + bus.getSpeed());
    }

    //test starting position of bus
    @Test
    void getPosition() {
        System.out.println("The starting position of the bus is: " + bus.getPosition());
    }

    //test new position when bus travels a certain distance
    @Test
    void getNewPosition(){
        int distance = 20;
        System.out.println("If a bus travelled a total distance of 20, its new position would be: " + bus.getNewPosition(distance));

    }
}
