package com.holden.journey;

import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 * Panel for displaying emotional state as a line graph.
 */
public class EmotionalStateGraphPanel extends JPanel {
    private List<JourneyLocation> locations;
    private static final int PADDING = 50;
    private static final int POINT_WIDTH = 8;
    private static final Color GRAPH_COLOR = new Color(200, 50, 50);
    private static final Color GRID_COLOR = new Color(200, 200, 200);

    public EmotionalStateGraphPanel(List<JourneyLocation> locations) {
        this.locations = locations;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int graphWidth = width - 2 * PADDING;
        int graphHeight = height - 2 * PADDING;

        // Draw axes
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(PADDING, height - PADDING, width - PADDING, height - PADDING); // x-axis
        g2d.drawLine(PADDING, PADDING, PADDING, height - PADDING); // y-axis

        // Draw grid lines and labels
        g2d.setColor(GRID_COLOR);
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        for (int i = -10; i <= 10; i += 5) {
            int y = (int) (height - PADDING - (i + 10) * graphHeight / 20.0);
            g2d.drawLine(PADDING - 5, y, width - PADDING, y);
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(i), PADDING - 35, y + 3);
            g2d.setColor(GRID_COLOR);
        }

        if (locations.isEmpty()) return;

        // Draw emotional state line with gradient coloring
        g2d.setStroke(new BasicStroke(3));
        for (int i = 0; i < locations.size() - 1; i++) {
            int x1 = (int) (PADDING + (i * graphWidth / (double) (locations.size() - 1)));
            int y1 = (int) (height - PADDING - (locations.get(i).getEmotionalState() + 10) * graphHeight / 20.0);
            
            int x2 = (int) (PADDING + ((i + 1) * graphWidth / (double) (locations.size() - 1)));
            int y2 = (int) (height - PADDING - (locations.get(i + 1).getEmotionalState() + 10) * graphHeight / 20.0);
            
            // Use theme color from location
            g2d.setColor(locations.get(i).getThemeColor());
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Draw points with theme colors
        for (int i = 0; i < locations.size(); i++) {
            int x = (int) (PADDING + (i * graphWidth / (double) (locations.size() - 1)));
            int y = (int) (height - PADDING - (locations.get(i).getEmotionalState() + 10) * graphHeight / 20.0);
            g2d.setColor(locations.get(i).getThemeColor());
            g2d.fillOval(x - POINT_WIDTH / 2, y - POINT_WIDTH / 2, POINT_WIDTH, POINT_WIDTH);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawOval(x - POINT_WIDTH / 2, y - POINT_WIDTH / 2, POINT_WIDTH, POINT_WIDTH);
        }

        // Draw labels
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString("Emotional State Over 3-Day Journey", PADDING, 20);
        g2d.setFont(new Font("Arial", Font.PLAIN, 11));
        g2d.drawString("Emotional State (↓ despair, ↑ contentment)", 10, height - 5);
        g2d.drawString("Timeline →", width - 120, height - 5);
    }
}
