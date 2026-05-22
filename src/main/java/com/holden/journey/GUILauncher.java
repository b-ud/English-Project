package com.holden.journey;

import javax.swing.SwingUtilities;

/**
 * Launcher for the Holden's Journey Visualizer GUI.
 * Run this to see the interactive journey visualization with the US map.
 */
public class GUILauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JourneyVisualizerGUI gui = new JourneyVisualizerGUI();
            gui.setVisible(true);
        });
    }
}
