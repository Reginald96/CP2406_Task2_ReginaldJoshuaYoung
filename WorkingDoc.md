# Traffic Simulator

### Specification
The client needs to find a way to simulate traffic by using different vehicle types, controlling the movement of each 
vehicle, control how traffic lights change colors, and manage the different types or roads and intersections. This 
iteration will be console based. In the simulator, certain inputs would be required by the user and the statuses of
all objects in the simulator would be monitored to ensure a smooth program workflow. This simulator mainly focuses on
how the vehicle moves, interacts with the various traffic lights and travels across multiple roads.

### Decomposition
As mentioned above, vehicles, roads and traffic lights would be used in the traffic simulator. These objects would 
interact with one another to produce the intended actions. The following showcases how they would be able to interact
with one another.

#### Objects.Vehicle (abstract)
The vehicle class will be abstract, having the Objects.Car Objects.Bus and Objects.Motorbike as its subclasses. Having the following attributes;
- *id* – an identification number that uniquely identifies each vehicle.
- *Length* – the length of each vehicle. different types of vehicles have different lengths
- *Breadth* – the breadth of each vehicle. different types of vehicles have different breadths
- *Speed* – the speed of each vehicle. 
- *Position* – where the vehicle is located on a road.
- *newPosition* - The new position after a vehicle has travelled a certain distance
- *currentRoad.Road* – The specific road a vehicle is currently driving on.
- *Colour* - The color is used to represent each vehicle. Each vehicle has its own random color. 

All vehicle would move by using the move() method. All roads have a speed limit. However, for simplicity, all vehicles 
have the same constant speed of 1. At the end of each road, there will be a traffic light. If the traffic light is red, 
the vehicles would have to come to a stop. If the traffic light is green, the vehicles would be able to pass through the
traffic light and enter the next road. This process would be constantly repeated until the vehicle reaches the very last
road in the simulator. Once it reaches the end of the last road, it would be removed from the simulator. The simulation
would end when all vehicles have reached the end of the last road and gets removed from the simulator.

##### Objects.Car
The car class will be a subclass of the vehicle class. It will describe an average road vehicle with an average length
and a breadth half its length.  

##### Objects.Bus
The bus class will be a subclass of the vehicle class. It will describe a large road vehicle with a length three times that
of an average road vehicle and a breadth one fourth its length.

##### Objects.Motorbike
The motorbike class will be a subclass of the vehicle class. It will describe a small road vehicle with a length half 
of an average road vehicle and a breadth half its length.


#### Objects.Road
The road class will be an object that describes a one lane road. This class has the following attributes:
- *id* - an identification number that uniquely identifies each vehicle.
- *Speed limit* – the maximum speed that vehicles on that road may travel at.
-	*Length* – the length of each road.
-	*Start location* – the point in which a road begins
-	*End location* – the point in which a road ends
-	*Connected roads* – all roads that a specific road is connected to.
-	*Lights on the road* – the traffic lights that are on a specific road
-	*Cars on the road* – all vehicles that are currently traveling on this road.
- *Orientation* - the direction in which a road is being placed.

As mentioned above, the speed of all vehicles would remain as 1 throughout the simulator. The length of each road are
ensured to be long enough to fit a few vehicles in each road. Roads can only interact with one another if they are connected
to each other. Vehicles would move from the start of the road to the end of the road where the traffic lights are placed.
They would then interact with the next road that is connected to the current road and the vehicles would proceed to travel
on that next connected road.


#### Objects.TrafficLight
The traffic light class consists of two colors. Green light and red light. It will have the following attributes.
- *id* - an identification number that uniquely identifies each vehicle.
- *State* - the colour in which the traffic light is displaying at that certain period of time.
- *Position* - the position in which the traffic light is located on the road.
- *Objects.Road attached to* - the specific road in which the light is attached to.

The traffic light will function by using the operate() method. The traffic light would change colors randomly throughout the 
simulation. Vehicles would have to stop moving if the traffic light is red and would only be able to travel to the next
road when the traffic light turns green.

### CityMap.CitySimulation
The CitySimulation class would have the following attributes:
- *vehiclesToSpawn* - number of vehicles that would spawn in the simulator,
- *vehiclesSpawned* - how many vehicles have spawned in the simulator so far.
- *vehiclesRemoved* - vehicles that have reached the end of the simulator and thus, removed from the simulator.
- *numberOfCycles* - the number of cycles between spawns.
- *scale* - the scale of Objects drawn graphically.

The CitySimulation class showcases a graphical representation of the simulator. The type of each newly created vehicle
would be completely random. Here, the user is able to create a specific number of vehicles as well as the ability to 
update the rate in which vehicles spawn.


### CityMap.EditMap
- *scale* - the scale of objects drawn graphically.
- *create* - create a new traffic simulator.
- *edit* - edit the current traffic simulator.
- *open* - open a specific traffic simulator.
- *save* - save the state of a traffic simulator.
- *exit* - exit the program.

The EditMap class will generate the graphical display for the city editor within the main frame. Users will be able to 
click to place roads, define the orientation of each road (either vertical or horizontal) and the connection with 
dialog boxes within the menu options. Furthermore, users can create, open edit or save a traffic simulator. Lastly, the 
user will have an option to exit the traffic simulator when the user is done with the program. 

### Objects.SaveFile
This class will handel the saving of simulation files. 

### Main
This class will have the main() method that will Call the simulation and editor panel. It will also Have the 
menus used for user navigation. User input for simulation parameters will also be handled here; number of vehicles 
spawned 
