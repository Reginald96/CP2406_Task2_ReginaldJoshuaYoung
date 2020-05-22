package Objects;

public class Car extends Vehicle {

    int distance;


    public Car(String id, Road currentRoad) {
        super(currentRoad);

        this.id = "car_" + id;
        this.length = super.getLength();
        this.breadth = this.length / 2;
        this.position = -this.length;
        this.speed = super.getSpeed();

        this.newPosition = super.getNewPosition(distance);



    }
}

