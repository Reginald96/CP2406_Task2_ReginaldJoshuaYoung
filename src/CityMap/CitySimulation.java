package CityMap;


import Objects.Bus;
import Objects.Car;
import Objects.Motorbike;
import Objects.Road;
import Objects.TrafficLight;
import Objects.Vehicle;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CitySimulation extends JPanel {
    private CitySimulation.State state;
    private int scale;
    private ArrayList<Road> roads;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<TrafficLight> lights;
    private Timer timer;
    private Boolean stop;
    private Random random;
    private static int cycle = 0;
    private int vehiclesToSpawn;
    private int vehiclesSpawned;
    private int vehiclesRemoved;
    private int numberOfCycles;
    private int updateRate;

    public CitySimulation() {
        this.state = CitySimulation.State.STOPPED;
        this.vehicles = new ArrayList();
        this.stop = true;
        this.random = new Random();
        this.vehiclesToSpawn = 2;
        this.vehiclesSpawned = 0;
        this.vehiclesRemoved = 0;
        this.numberOfCycles = 20;
        this.updateRate = 1000;
    }

    public void loadMap(ArrayList<Road> roads, ArrayList<TrafficLight> lights) {
        this.roads = roads;
        this.lights = lights;
    }

    public void setVehicleSpawn(int spawns) {
        this.vehiclesToSpawn = spawns - 1;
        this.createVehicle();
    }

    public void setVehicleSpawnRate(int rate) {
        this.numberOfCycles = rate;
    }

    public void createVehicle() {
        int randomVehicle = this.random.nextInt(3);
        switch(randomVehicle) {
            case 0:
                this.vehicles.add(new Car(Integer.toString(cycle), (Road)this.roads.get(0)));
                break;
            case 1:
                this.vehicles.add(new Bus(Integer.toString(cycle), (Road)this.roads.get(0)));
                break;
            case 2:
                this.vehicles.add(new Motorbike(Integer.toString(cycle), (Road)this.roads.get(0)));
        }

    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void simulate() {
        this.setLayout(new BorderLayout());
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 0));
        infoPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        JLabel vehicleLabel = new JLabel("Vehicles: ");
        infoPanel.add(vehicleLabel);
        JLabel averageSpeedLabel = new JLabel("Average Speed: ");
        infoPanel.add(averageSpeedLabel);
        JLabel stateLabel = new JLabel("State: " + this.state);
        infoPanel.add(stateLabel);
        this.add(infoPanel, "South");
        if (this.timer != null) {
            this.timer.stop();
        }

        this.timer = new Timer(this.updateRate / 60, (e) -> {
            ++cycle;
            if (this.vehicles.size() == 0) {
                this.state = CitySimulation.State.FINISHED;
            } else if (this.stop) {
                this.state = CitySimulation.State.STOPPED;
            } else {
                this.state = CitySimulation.State.RUNNING;
            }

            stateLabel.setText("State: " + this.state);
            vehicleLabel.setText("Vehicles: " + this.getTotalVehicles());
            averageSpeedLabel.setText("Average Speed:" + this.getAverageSpeed());
            if (this.vehicles.size() == 0 || this.stop) {
                this.timer.stop();
            }

            Iterator iterator;
            if (cycle % 30 == 0) {
                iterator = this.lights.iterator();

                while(iterator.hasNext()) {
                    TrafficLight light = (TrafficLight)iterator.next();
                    light.operate(this.random.nextInt());
                    light.printLightStatus();
                }
            }

            iterator = this.vehicles.iterator();

            while(iterator.hasNext()) {
                Vehicle vehicle = (Vehicle)iterator.next();
                vehicle.move();
                vehicle.printStatus();
                if (vehicle.getPosition() + vehicle.getLength() + vehicle.getSpeed() >= vehicle.getCurrentRoad().getLength() && vehicle.getCurrentRoad().getConnectedRoads().isEmpty() && vehicle.getSpeed() == 0) {
                    vehicle.getCurrentRoad().getVehiclesOnRoad().remove(vehicle);
                    iterator.remove();
                    ++this.vehiclesRemoved;
                }
            }

            if (cycle % this.numberOfCycles == 0 && this.vehiclesSpawned < this.vehiclesToSpawn) {
                this.createVehicle();
                ++this.vehiclesSpawned;
            }

            this.repaint();
        });
        this.timer.start();
    }

    public Boolean getStopSim() {
        return this.stop;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setLights(ArrayList<TrafficLight> lights) {
        this.lights = lights;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Iterator var2 = this.roads.iterator();

        while(var2.hasNext()) {
            Road road = (Road)var2.next();
            road.draw(g, this.scale);
        }

        if (!this.vehicles.isEmpty()) {
            var2 = this.vehicles.iterator();

            while(var2.hasNext()) {
                Vehicle vehicle = (Vehicle)var2.next();
                vehicle.draw(g, this.scale);
            }
        }

        var2 = this.lights.iterator();

        while(var2.hasNext()) {
            TrafficLight light = (TrafficLight)var2.next();
            light.draw(g, this.scale);
        }

    }

    public int getTotalVehicles() {
        return this.vehiclesSpawned + 1 - this.vehiclesRemoved;
    }

    private String getAverageSpeed() {
        int totalSpeed = 0;

        Vehicle vehicle;
        for(Iterator var2 = this.vehicles.iterator(); var2.hasNext(); totalSpeed += vehicle.getSpeed()) {
            vehicle = (Vehicle)var2.next();
        }

        if (!this.vehicles.isEmpty()) {
            int average = totalSpeed / this.vehicles.size();
            return (double)average * 3.6D + "km/h";
        } else {
            return "0";
        }
    }

    public int getUpdateRate() {
        return this.updateRate;
    }

    public void setUpdateRate(int updateRate) {
        this.updateRate = updateRate;
    }

    public void setStopSim(Boolean stop) {
        this.stop = stop;
    }

    public static enum State {
        STOPPED,
        RUNNING,
        FINISHED;

        private State() {
        }
    }
}
