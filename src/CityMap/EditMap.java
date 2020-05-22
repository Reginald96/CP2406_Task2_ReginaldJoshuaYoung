package CityMap;

import Objects.Road;
import Objects.TrafficLight;
import Objects.Road.Orientation;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EditMap extends JPanel {
    private ArrayList<Road> roads;
    private ArrayList<TrafficLight> lights;
    private int scale;

    public EditMap() {
    }

    public void newMap() {
        this.roads = new ArrayList();
        this.lights = new ArrayList();
        MouseAdapter mouseLis = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int xValue = e.getX() / EditMap.this.scale;
                int yValue = e.getY() / EditMap.this.scale;
                System.out.println("X: " + xValue);
                System.out.println("Y: " + yValue);
                if (EditMap.this.roads.size() == 0) {
                    if (e.getY() < 10) {
                        EditMap.this.roads.add(new Road(Integer.toString(EditMap.this.roads.size()), 1, 50, new int[]{xValue, 0}, Orientation.VERTICAL));
                    } else if (e.getX() < 10) {
                        EditMap.this.roads.add(new Road(Integer.toString(EditMap.this.roads.size()), 1, 50, new int[]{0, yValue}, Orientation.HORIZONTAL));
                    }
                } else {
                    String[] orientationOptions = new String[]{"Horizontal", "Vertical"};
                    int orientationSelection = JOptionPane.showOptionDialog((Component)null, "Choose Orientation:", "Orientation Selection", -1, 1, (Icon)null, orientationOptions, EditMap.this.roads);
                    switch(orientationSelection) {
                        case 0:
                            EditMap.this.roads.add(new Road(Integer.toString(EditMap.this.roads.size()), 1, 50, new int[]{xValue, yValue}, Orientation.HORIZONTAL));
                            break;
                        case 1:
                            EditMap.this.roads.add(new Road(Integer.toString(EditMap.this.roads.size()), 1, 50, new int[]{xValue, yValue}, Orientation.VERTICAL));
                    }

                    String[] connectionOptions = new String[30];

                    int connectionSelection;
                    for(connectionSelection = 0; connectionSelection < connectionOptions.length; ++connectionSelection) {
                        connectionOptions[connectionSelection] = Integer.toString(connectionSelection);
                    }

                    connectionSelection = JOptionPane.showOptionDialog((Component)null, "Choose the Connecting Road you would like to connect the road with. (Note: The first road is 0):", "Connections Selection", -1, 1, (Icon)null, connectionOptions, connectionOptions[0]);
                    ((Road)EditMap.this.roads.get(connectionSelection)).getConnectedRoads().add((Road)EditMap.this.roads.get(EditMap.this.roads.size() - 1));
                }

                Iterator var8 = EditMap.this.roads.iterator();

                while(var8.hasNext()) {
                    Road road = (Road)var8.next();
                    EditMap.this.lights.add(new TrafficLight("1", road));
                }

                EditMap.this.repaint();
            }
        };
        this.addMouseListener(mouseLis);
    }

    public ArrayList<Road> getRoads() {
        return this.roads;
    }

    public ArrayList<TrafficLight> getLights() {
        return this.lights;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.roads.size() == 0) {
            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, this.getWidth(), 10);
            g.fillRect(0, 0, 10, this.getHeight());
        }

        Iterator var2;
        if (!this.roads.isEmpty()) {
            var2 = this.roads.iterator();

            while(var2.hasNext()) {
                Road road = (Road)var2.next();
                road.draw(g, this.scale);
            }
        }

        if (!this.lights.isEmpty()) {
            var2 = this.lights.iterator();

            while(var2.hasNext()) {
                TrafficLight light = (TrafficLight)var2.next();
                light.draw(g, this.scale);
            }
        }

    }
}