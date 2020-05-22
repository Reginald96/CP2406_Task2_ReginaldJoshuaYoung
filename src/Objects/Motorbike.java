package Objects;

public class Motorbike extends Vehicle {

    int distance;

    public Motorbike(String id, Road currentRoad) {
        super(currentRoad);
        this.id = "bike_" + id;
        this.length = super.getLength() / 2;
        this.breadth = super.getBreadth() / 2;
        this.position = -this.length;

        this.newPosition = super.getNewPosition(distance);
    }
}
