package ObjectSimulation;

import Objects.Car;
import Objects.Road;
import Objects.TrafficLight;
import Objects.Road.Orientation;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class RoadSimulation {
    Road road;

    RoadSimulation() {
        this.road = new Road("0", 1, 5, new int[]{0, 0}, Orientation.HORIZONTAL);
    }

    @Test
    void getId() {
        //Assert.assertEquals(5L, (long)this.road.getLength());
    }

    @Test
    void getSpeedLimit() {
        //Assert.assertEquals(1L, (long)this.road.getSpeedLimit());
    }

    //The length of each road
    @Test
    void getLength() {
        //Assert.assertEquals(5L, (long)this.road.getLength());
        System.out.println("The length of each road is: " + road.getLength());
    }

    @Test
    void getStartLocationTest() {
        int[] expected1 = new int[]{0, 0};
        int[] actual = this.road.getStartLocation();
        //Assert.assertArrayEquals(expected1, actual);
    }

    @Test
    void getEndLocation() {
        int[] expected = new int[]{5, 0};
        //Assert.assertArrayEquals(expected, this.road.getEndLocation());
    }

    @Test
    void getCars() {
        ArrayList<Car> expected = new ArrayList();
        //Assert.assertEquals(expected, this.road.getVehiclesOnRoad());
    }

    @Test
    void getLights() {
        ArrayList<TrafficLight> expected = new ArrayList();
        //Assert.assertEquals(expected, this.road.getLightsOnRoad());
    }

    @Test
    void getConnectedRoads() {
        ArrayList<Road> expected = new ArrayList();
        //Assert.assertEquals(expected, this.road.getConnectedRoads());
    }
}
