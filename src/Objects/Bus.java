package Objects;

public class Bus extends Vehicle{

    int distance;

    public Bus(String id, Road currentRoad) {
        super(currentRoad);
        this.id = "bus_" + id;
        this.length = super.getLength() * 3;
        this.breadth = super.getLength() / 4;
        this.position = -this.length;

        this.newPosition = super.getNewPosition(distance);
    }

}
