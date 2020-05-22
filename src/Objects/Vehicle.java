package Objects;

import Objects.Road.Orientation;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.Random;
import java.awt.Color;

public abstract class Vehicle {

    int length;
    int breadth;
    int speed;
    int position;
    int newPosition;

    String id = "000";

    //static final int STOPPED = 0;
    //private static final int NEXT_ROAD_INDEX = 0;
    //private static final int START_POSITION = 0;

    private Random random = new Random();
    private Color colour;
    private Vehicle.Lane lane;

    Road currentRoad;


    public Vehicle(Road currentRoad) {

        this.length = 4;
        this.breadth = 2;
        this.speed = 0;
        this.currentRoad = currentRoad;
        currentRoad.getVehiclesOnRoad().add(this);
        this.colour = this.randomColour();
    }

    public void move() {
        Random random = new Random();
        int nextPosition = this.position + this.length + this.speed;

        for(Iterator var3 = this.currentRoad.getVehiclesOnRoad().iterator(); var3.hasNext(); this.speed = this.currentRoad.getSpeedLimit()) {
            Vehicle nextVehicle = (Vehicle)var3.next();
            if (nextVehicle.position > this.position && nextVehicle.position <= nextPosition + 4) {
                this.speed = 0;
                break;
            }
        }

        if (this.speed != 0) {
            if (!this.currentRoad.getLightsOnRoad().isEmpty() && nextPosition + 1 >=
                    ((TrafficLight)this.currentRoad.getLightsOnRoad().get(0)).getPosition() &&
                    ((TrafficLight)this.currentRoad.getLightsOnRoad().get(0)).getState().equals("red")) {
                this.speed = 0;
            } else {
                this.speed = this.currentRoad.getSpeedLimit();
                if (this.currentRoad.getLength() <= nextPosition && !this.currentRoad.getConnectedRoads().isEmpty()) {
                    this.currentRoad.getVehiclesOnRoad().remove(this);
                    int nextRoadIndex = random.nextInt(2);
                    this.currentRoad = (Road)this.currentRoad.getConnectedRoads().get(nextRoadIndex);
                    this.currentRoad.getVehiclesOnRoad().add(this);
                    this.position = 0;
                } else if (this.currentRoad.getLength() >= nextPosition) {
                    this.position += this.speed;
                } else {
                    this.speed = 0;
                }
            }
        }

    }

    public void draw(Graphics g, int scale) {
        int xValue = 0;
        int yValue = 1;
        int[] startLocation;
        int width;
        int height;
        int x;
        int y;
        if (this.currentRoad.getOrientation() == Orientation.HORIZONTAL) {
            startLocation = this.getCurrentRoad().getStartLocation();
            width = this.getLength() * scale;
            height = this.getBreadth() * scale;
            x = (this.getPosition() + startLocation[xValue]) * scale;
            y = startLocation[yValue] * scale + scale;
            g.setColor(this.colour);
            g.fillRect(x, y, width, height);
        } else if (this.currentRoad.getOrientation() == Orientation.VERTICAL) {
            startLocation = this.getCurrentRoad().getStartLocation();
            width = this.getBreadth() * scale;
            height = this.getLength() * scale;
            x = startLocation[xValue] * scale + (this.currentRoad.getWidth() * scale - (width + scale));
            y = (this.getPosition() + startLocation[yValue]) * scale;
            g.setColor(this.colour);
            g.fillRect(x, y, width, height);
        }

    }

    //assign a random color to each vehicle
    public Color randomColour() {
        int r = this.random.nextInt(246) + 10;
        int g = this.random.nextInt(246) + 10;
        int b = this.random.nextInt(246) + 10;
        return new Color(r, g, b);
    }

    //exact details of vehicle when running
    public void printStatus() {
        System.out.printf("%s going:%dm/s on %s at position:%s%n",
                this.getId(),
                this.getSpeed(),
                this.getCurrentRoad().getId(),
                this.getPosition());
    }

    public Vehicle.Lane getLane() {
        return this.lane;
    }

    public void setLane(Vehicle.Lane lane) {
        this.lane = lane;
    }

    public static int getNextRoadIndex() {
        return 0;
    }

    public int getLength() {
        return this.length;
    }

    public int getBreadth() {
        return this.breadth;
    }

    public int getSpeed() {
        speed = 1;
        return this.speed;
    }

    public int getPosition() {
        return this.position;
    }

    public Road getCurrentRoad() {
        return this.currentRoad;
    }

    public String getId() {
        return this.id;
    }

    //calculate new position of vehicle
    public int getNewPosition(int distance){
        this.newPosition = position + distance;
        return this.newPosition;
    }

    static enum Lane {
        LEFT,
        RIGHT;

        private Lane() {
        }
    }
}
