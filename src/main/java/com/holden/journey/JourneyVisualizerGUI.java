package com.holden.journey;

import java.awt.*;
import javax.swing.*;

/**
 * Main GUI application for visualizing Holden's 3-day journey.
 */
public class JourneyVisualizerGUI extends JFrame {
    private final HoldenJourney journey;
    private final LocationListPanel locationList;
    private final EmotionalStateGraphPanel graph;
    private final LocationDetailsPanel details;
    private final NYCMapPanel map;
    private final USMapPanel usMap;

    public JourneyVisualizerGUI() {
        setTitle("Holden Caulfield's Journey - Interactive Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLocationRelativeTo(null);

        // Initialize journey from JourneyBuilder
        journey = JourneyBuilder.buildJourney();

        // Create main panels
        locationList = new LocationListPanel(journey.getLocations());
        graph = new EmotionalStateGraphPanel(journey.getLocations());
        details = new LocationDetailsPanel();
        map = new NYCMapPanel(journey.getLocations());
        usMap = new USMapPanel(journey.getLocations());
        JTabbedPane centerTabs = new JTabbedPane();

        // Set up location selection listener
        locationList.setSelectionListener(location -> {
            details.displayLocation(location);
            centerTabs.setSelectedComponent(details);
            repaint();
        });

        map.setSelectionListener(location -> {
            details.displayLocation(location);
            centerTabs.setSelectedComponent(details);
            repaint();
        });

        usMap.setSelectionListener(location -> {
            details.displayLocation(location);
            centerTabs.setSelectedComponent(details);
            repaint();
        });

        // Create layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Left sidebar: location list
        JPanel leftPanel = new JPanel(new BorderLayout());
        JScrollPane listScroll = new JScrollPane(locationList);
        listScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        leftPanel.add(listScroll, BorderLayout.CENTER);
        leftPanel.setPreferredSize(new Dimension(250, 900));
        
        // Center: map and graph in tabs
        
        JScrollPane mapScroll = new JScrollPane(map);
        JScrollPane usMapScroll = new JScrollPane(usMap);
        JPanel nycTab = new JPanel(new BorderLayout());
        nycTab.add(mapScroll, BorderLayout.CENTER);

        centerTabs.addTab("US Journey Map (PA to CA)", usMapScroll);
        centerTabs.addTab("NYC Map & Journey Path", nycTab);
        centerTabs.addTab("Emotional State Graph", graph);
        centerTabs.addTab("Location Details", details);

        // Add panels to main
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(centerTabs, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JourneyVisualizerGUI());
    }
}
