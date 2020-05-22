package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Road {
    private Road.Orientation orientation;
    private String id;
    private int speedLimit;
    private int length;
    private int width;
    private int[] startLocation;
    private int[] endLocation;
    private ArrayList<Vehicle> vehiclesOnRoad = new ArrayList();
    private ArrayList<TrafficLight> lightsOnRoad = new ArrayList();
    private ArrayList<Road> connectedRoads = new ArrayList();

    public Road(String id, int speedLimit, int length, int[] startLocation, Road.Orientation orientation) {
        this.id = "road_" + id;
        this.speedLimit = speedLimit;
        this.length = 48;
        this.width = 8;
        this.orientation = orientation;
        this.startLocation = startLocation;
        this.setEndLocation();
    }

    public void draw(Graphics g, int scale) {
        int[] startLocation;
        int x;
        int y;
        int width;
        int height;
        if (this.orientation == Road.Orientation.HORIZONTAL) {
            startLocation = this.startLocation;
            x = startLocation[0] * scale;
            y = startLocation[1] * scale;
            width = this.length * scale;
            height = this.width * scale;
            g.setColor(Color.darkGray);
            g.fillRect(x, y, width, height);
            g.setColor(Color.white);
            g.fillRect(x, y + height / 2 - scale / 6, width, scale / 6);
            g.fillRect(x, y + height / 2 + scale / 6, width, scale / 6);
        } else if (this.orientation == Road.Orientation.VERTICAL) {
            startLocation = this.startLocation;
            x = startLocation[0] * scale;
            y = startLocation[1] * scale;
            width = this.width * scale;
            height = this.length * scale;
            g.setColor(Color.darkGray);
            g.fillRect(x, y, width, height);
            g.setColor(Color.white);
            g.fillRect(x + width / 2 - scale / 6, y, scale / 6, height);
            g.fillRect(x + width / 2 + scale / 6, y, scale / 6, height);
        }

    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSpeedLimit() {
        speedLimit = 1;
        return this.speedLimit;
    }

    public int getLength() {
        return this.length;
    }

    public int getWidth() {
        return this.width;
    }

    public String printStartLocation() {
        int var10000 = this.startLocation[0];
        return var10000 + "," + this.startLocation[1];
    }

    public void getStartLocation(int[] startLocation) {
        this.startLocation = startLocation;
    }

    public String printEndLocation() {
        int var10000 = this.endLocation[0];
        return var10000 + "," + this.endLocation[1];
    }

    //attributes of particular road
    public void printRoadInfo() {
        System.out.printf("%s limit of:%dm/s is %dm long at location:%s to %s%n",
                this.getId(),
                this.getSpeedLimit(),
                this.getLength(),
                this.printStartLocation(),
                this.printEndLocation());
    }

    public void setEndLocation() {
        if (this.orientation == Road.Orientation.HORIZONTAL) {
            this.endLocation = new int[]{this.length + this.startLocation[0], this.startLocation[1]};
        } else if (this.orientation == Road.Orientation.VERTICAL) {
            this.endLocation = new int[]{this.startLocation[1], this.length + this.startLocation[0]};
        }

    }

    public int[] getStartLocation() {
        return this.startLocation;
    }

    public int[] getEndLocation() {
        return this.endLocation;
    }

    public ArrayList<Vehicle> getVehiclesOnRoad() {
        return this.vehiclesOnRoad;
    }

    public void setVehiclesOnRoad(ArrayList<Vehicle> carsOnRoad) {
        this.vehiclesOnRoad = carsOnRoad;
    }

    public ArrayList<TrafficLight> getLightsOnRoad() {
        return this.lightsOnRoad;
    }

    public void setLightsOnRoad(ArrayList<TrafficLight> lightsOnRoad) {
        this.lightsOnRoad = lightsOnRoad;
    }

    public ArrayList<Road> getConnectedRoads() {
        return this.connectedRoads;
    }

    public void setConnectedRoads(ArrayList<Road> connectedRoads) {
        this.connectedRoads = connectedRoads;
    }

    public Road.Orientation getOrientation() {
        return this.orientation;
    }

    public void setOrientation(Road.Orientation orientation) {
        this.orientation = orientation;
    }

    public static enum Orientation {
        HORIZONTAL,
        VERTICAL;

        private Orientation() {
        }
    }
}
