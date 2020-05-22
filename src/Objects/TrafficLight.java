package Objects;

import Objects.Road.Orientation;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class TrafficLight {
    private static final double CHANGE_COLOR = 0.5D;
    private static final String GREEN = "green";
    private static final String RED = "red";
    private String id;
    private String state;
    private int position;
    private Road roadAttachedTo;

    public TrafficLight(String id, Road road) {
        this.id = "light_" + id;
        this.state = "red";
        this.roadAttachedTo = road;
        this.position = this.roadAttachedTo.getLength();
        this.roadAttachedTo.getLightsOnRoad().add(this);
    }

    public void operate(int seed) {
        Random random = new Random((long)seed);
        double probability = random.nextDouble();
        if (probability > CHANGE_COLOR && !this.getRoadAttachedTo().getVehiclesOnRoad().isEmpty()) {
            this.setState("red");
        } else {
            this.setState("green");
        }

    }

    public void printLightStatus() {
        System.out.printf("%s is:%s on %s at position:%s%n",
                this.getId(),
                this.getState(),
                this.getRoadAttachedTo().getId(),
                this.getPosition());
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Road getRoadAttachedTo() {
        return this.roadAttachedTo;
    }

    public void setRoadAttachedTo(Road roadAttachedTo) {
        this.roadAttachedTo = roadAttachedTo;
    }

    public int getPosition() {
        return this.position;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void draw(Graphics g, int scale) {
        String var3;
        byte var4;
        int y;
        int[] startLocation;
        int x;
        if (this.roadAttachedTo.getOrientation() == Orientation.HORIZONTAL) {
            var3 = this.state;
            var4 = -1;
            switch(var3.hashCode()) {
                case 112785:
                    if (var3.equals("red")) {
                        var4 = 0;
                    }
                    break;
                case 98619139:
                    if (var3.equals("green")) {
                        var4 = 1;
                    }
            }

            switch(var4) {
                case 0:
                    g.setColor(Color.red);
                    break;
                case 1:
                    g.setColor(Color.green);
            }

            startLocation = this.getRoadAttachedTo().getStartLocation();
            x = (this.getPosition() + startLocation[0]) * scale;
            y = startLocation[1] * scale;
            int height = this.getRoadAttachedTo().getWidth() / 2 * scale;
            g.fillRect(x, y, scale, height);
        }

        if (this.roadAttachedTo.getOrientation() == Orientation.VERTICAL) {
            var3 = this.state;
            var4 = -1;
            switch(var3.hashCode()) {
                case 112785:
                    if (var3.equals("red")) {
                        var4 = 0;
                    }
                    break;
                case 98619139:
                    if (var3.equals("green")) {
                        var4 = 1;
                    }
            }

            switch(var4) {
                case 0:
                    g.setColor(Color.red);
                    break;
                case 1:
                    g.setColor(Color.green);
            }

            startLocation = this.getRoadAttachedTo().getStartLocation();
            x = (startLocation[0] + this.getRoadAttachedTo().getWidth() / 2) * scale;
            y = (this.getPosition() + startLocation[1]) * scale;
            int width = this.getRoadAttachedTo().getWidth() / 2 * scale;
            g.fillRect(x, y, width, scale);
        }

    }
}
