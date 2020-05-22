package com.company;

import CityMap.EditMap;
import CityMap.CitySimulation;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Main {
    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 1024;
    private static CitySimulation citySimulation = new CitySimulation();
    private static EditMap editMap = new EditMap();
    private static final int SCALE = 8;

    public Main() {
    }



    public static void main(String[] args) {
        //name of project
        final JFrame mainWindow = new JFrame("Traffic Simulator");
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(3);
        mainWindow.setSize(1600, 1024);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 0));
        bottomPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        final JLabel modeLabel = new JLabel("Mode: ");
        bottomPanel.add(modeLabel);
        JLabel statusLabel = new JLabel("Status: ");
        bottomPanel.add(statusLabel);
        mainWindow.add(bottomPanel, "South");
        JMenuBar menuBar = new JMenuBar();
        mainWindow.add(menuBar, "North");

        //Edit city button
        JMenu editMenu = new JMenu("Edit City");
        MenuListener cityLis = new MenuListener() {
            public void menuSelected(MenuEvent e) {
                modeLabel.setText("Mode: Editor");
                mainWindow.repaint();
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuCanceled(MenuEvent e) {
            }
        };

        //create button
        editMenu.addMenuListener(cityLis);
        menuBar.add(editMenu);
        JMenuItem newMapItem = new JMenuItem("Create");
        newMapItem.addActionListener((e) -> {
            citySimulation.setVisible(false);
            mainWindow.remove(editMap);
            editMap = new EditMap();
            editMap.newMap();
            editMap.setScale(8);
            mainWindow.add(editMap);
            editMap.setVisible(true);
            statusLabel.setText("Status: New Map");
            mainWindow.validate();
            mainWindow.repaint();
        });

        //edit button
        editMenu.add(newMapItem);
        JMenuItem editMapItem = new JMenuItem("Edit");
        editMapItem.addActionListener((e) -> {
        });

        //open button
        editMenu.add(editMapItem);
        JMenuItem openMapItem = new JMenuItem("Open");
        openMapItem.addActionListener((e) -> {
        });

        //save button
        editMenu.add(openMapItem);
        JMenuItem saveMapItem = new JMenuItem("Save");
        saveMapItem.addActionListener((e) -> {
        });

        //exit button
        editMenu.add(saveMapItem);
        JMenuItem exitProgramItem = new JMenuItem("Exit");
        exitProgramItem.addActionListener((e) -> {
            System.exit(0);
        });

        //Simulation button
        editMenu.add(exitProgramItem);
        JMenu simMenu = new JMenu("Simulation");
        MenuListener simLis = new MenuListener() {
            public void menuSelected(MenuEvent e) {
                modeLabel.setText("Mode: Simulation");
                mainWindow.repaint();
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuCanceled(MenuEvent e) {
            }
        };
        simMenu.addMenuListener(simLis);
        JMenuItem loadSimItem = new JMenuItem("Load Map");
        simMenu.add(loadSimItem);
        JMenuItem spawnItem = new JMenuItem("Add Vehicles");
        spawnItem.setEnabled(false);
        simMenu.add(spawnItem);
        JMenuItem runSimItem = new JMenuItem("Run");
        runSimItem.setEnabled(false);
        runSimItem.addActionListener((e) -> {
            citySimulation.simulate();
            statusLabel.setText("Status: Simulation Started");
            citySimulation.setStopSim(false);
            mainWindow.validate();
            mainWindow.repaint();
        });
        simMenu.add(runSimItem);
        spawnItem.addActionListener((e) -> {
            String spawnInput = JOptionPane.showInputDialog("Total number of Vehicles to spawn:");
            int spawns = Integer.parseInt(spawnInput);
            citySimulation.setVehicleSpawn(spawns);
            String spawnRateInput = JOptionPane.showInputDialog("The time duration between each vehicle spawn:");
            int spawnRate = Integer.parseInt(spawnRateInput);
            citySimulation.setVehicleSpawnRate(spawnRate);
        });
        JMenuItem stopSimItem = new JMenuItem("Stop");
        stopSimItem.setEnabled(false);
        stopSimItem.addActionListener((e) -> {
            citySimulation.setStopSim(true);
            statusLabel.setText("Status: Simulation Stopped");
            mainWindow.validate();
            mainWindow.repaint();
        });
        simMenu.add(stopSimItem);
        loadSimItem.addActionListener((e) -> {
            statusLabel.setText("Status: Map Loaded!");
            editMap.setVisible(false);
            citySimulation = new CitySimulation();
            citySimulation.setScale(8);
            citySimulation.loadMap(editMap.getRoads(), editMap.getLights());
            mainWindow.add(citySimulation);
            runSimItem.setEnabled(true);
            spawnItem.setEnabled(true);
            stopSimItem.setEnabled(true);
            mainWindow.repaint();
        });
        JMenuItem setUpdateRateItem = new JMenuItem("Vehicle Spawn Rate");
        setUpdateRateItem.addActionListener((e) -> {
            String updateRateInput = JOptionPane.showInputDialog("Enter the vehicle spawn rate of the Simulation");
            int updateRate = Integer.parseInt(updateRateInput);
            citySimulation.setUpdateRate(updateRate);
            statusLabel.setText("Status: Update Rate set to " + updateRate);
            mainWindow.validate();
            mainWindow.repaint();
        });
        simMenu.add(setUpdateRateItem);
        menuBar.add(simMenu);
        mainWindow.setLocationRelativeTo((Component)null);
        mainWindow.setVisible(true);
    }


}