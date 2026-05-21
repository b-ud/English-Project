package com.holden.journey;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;

/**
 * Panel displaying a US map with Holden's complete journey from PA to CA.
 */
public class USMapPanel extends JPanel {
    private List<JourneyLocation> locations;
    private int hoveredPin = -1;
    private int selectedPin = -1;
    private MapSelectionListener listener;
    private static final int MAP_PADDING = 40;
    private static final int PIN_RADIUS = 10;

    public interface MapSelectionListener {
        void locationSelected(JourneyLocation location);
    }

    public USMapPanel(List<JourneyLocation> locations) {
        this.locations = locations;
        setupUSCoordinates();
        setBackground(new Color(240, 245, 250));
        setPreferredSize(new Dimension(1000, 600));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getX(), e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int newHovered = getPin(e.getX(), e.getY());
                if (newHovered != hoveredPin) {
                    hoveredPin = newHovered;
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int newHovered = getPin(e.getX(), e.getY());
                if (newHovered != hoveredPin) {
                    hoveredPin = newHovered;
                    repaint();
                }
            }
        });
    }

    private void setupUSCoordinates() {
        // US Map coordinates (normalized 0-100 scale, left to right, top to bottom)
        // Approximate US state positions
        double[][] usCoords = {
            {25, 65},   // Pencey Prep (Pennsylvania) - Start
            {35, 70},   // NYC - Grand Central Terminal
            {35, 70},   // Hotel Edmont (same area)
            {35, 70},   // Sally Hayes Date (same area)
            {35, 70},   // Central Park (same area)
            {35, 70},   // Museum (same area)
            {35, 70},   // Ducks (same area)
            {35, 70},   // Antolini's Apartment (same area)
            {35, 70},   // Grand Central Terminal (same area)
            {90, 75}    // California - Hospital (Far West)
        };

        for (int i = 0; i < locations.size() && i < usCoords.length; i++) {
            locations.get(i).setCoordinates(usCoords[i][0], usCoords[i][1]);
        }
    }

    public void setSelectionListener(MapSelectionListener listener) {
        this.listener = listener;
    }

    private void handleClick(int x, int y) {
        int pin = getPin(x, y);
        if (pin >= 0) {
            selectedPin = pin;
            if (listener != null) {
                listener.locationSelected(locations.get(pin));
            }
            repaint();
        }
    }

    private int getPin(int x, int y) {
        for (int i = 0; i < locations.size(); i++) {
            int pinX = (int) (MAP_PADDING + locations.get(i).getLatitude() * (getWidth() - 2 * MAP_PADDING) / 100);
            int pinY = (int) (MAP_PADDING + locations.get(i).getLongitude() * (getHeight() - 2 * MAP_PADDING) / 100);

            if (Math.sqrt(Math.pow(x - pinX, 2) + Math.pow(y - pinY, 2)) <= PIN_RADIUS) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawMapBackground(g2d);
        drawUSOutline(g2d);
        drawStateLabels(g2d);
        drawJourneyPath(g2d);
        drawPins(g2d);
        drawLegend(g2d);
    }

    private void drawMapBackground(Graphics2D g2d) {
        // Water background
        g2d.setColor(new Color(170, 200, 240));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // US map area (land)
        g2d.setColor(new Color(220, 235, 200));
        g2d.fillRect(MAP_PADDING, MAP_PADDING, getWidth() - 2 * MAP_PADDING, getHeight() - 2 * MAP_PADDING);

        // Border
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(MAP_PADDING, MAP_PADDING, getWidth() - 2 * MAP_PADDING, getHeight() - 2 * MAP_PADDING);

        // Title
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Holden's Journey: From Pennsylvania to California", 10, 25);
    }

    private void drawUSOutline(Graphics2D g2d) {
        // Simple representation of US boundary (just for visual context)
        g2d.setColor(new Color(100, 100, 100, 50));
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        // Grid for reference
        g2d.setColor(new Color(200, 220, 210));
        g2d.setStroke(new BasicStroke(0.5f));
        for (int i = 0; i <= 10; i++) {
            int x = MAP_PADDING + i * (getWidth() - 2 * MAP_PADDING) / 10;
            int y = MAP_PADDING + i * (getHeight() - 2 * MAP_PADDING) / 10;
            g2d.drawLine(x, MAP_PADDING, x, getHeight() - MAP_PADDING);
            g2d.drawLine(MAP_PADDING, y, getWidth() - MAP_PADDING, y);
        }
    }

    private void drawStateLabels(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        g2d.setColor(new Color(100, 100, 100));

        // Label key regions
        int labelX1 = MAP_PADDING + 10;
        int labelY1 = MAP_PADDING + 40;
        g2d.drawString("Pennsylvania", labelX1, labelY1);

        int labelX2 = MAP_PADDING + 15;
        int labelY2 = MAP_PADDING + 60;
        g2d.drawString("New York", labelX2, labelY2);

        int labelX3 = (int) (MAP_PADDING + 90 * (getWidth() - 2 * MAP_PADDING) / 100) - 40;
        int labelY3 = MAP_PADDING + 40;
        g2d.drawString("California", labelX3, labelY3);
    }

    private void drawJourneyPath(Graphics2D g2d) {
        if (locations.size() < 2) return;

        // Main journey line from PA to CA
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(new Color(220, 100, 100, 180)); // Red path

        // Draw line from first location (Pencey) to last location (Hospital in CA)
        int startX = (int) (MAP_PADDING + locations.get(0).getLatitude() * (getWidth() - 2 * MAP_PADDING) / 100);
        int startY = (int) (MAP_PADDING + locations.get(0).getLongitude() * (getHeight() - 2 * MAP_PADDING) / 100);

        int endX = (int) (MAP_PADDING + locations.get(locations.size() - 1).getLatitude() * (getWidth() - 2 * MAP_PADDING) / 100);
        int endY = (int) (MAP_PADDING + locations.get(locations.size() - 1).getLongitude() * (getHeight() - 2 * MAP_PADDING) / 100);

        g2d.drawLine(startX, startY, endX, endY);

        // Draw sub-path for NYC cluster
        if (locations.size() > 2) {
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setColor(new Color(100, 150, 200, 150)); // Blue for NYC detail

            for (int i = 1; i < locations.size() - 1; i++) {
                int x1 = (int) (MAP_PADDING + locations.get(i).getLatitude() * (getWidth() - 2 * MAP_PADDING) / 100);
                int y1 = (int) (MAP_PADDING + locations.get(i).getLongitude() * (getHeight() - 2 * MAP_PADDING) / 100);
                
                if (i < locations.size() - 1) {
                    int x2 = (int) (MAP_PADDING + locations.get(i + 1).getLatitude() * (getWidth() - 2 * MAP_PADDING) / 100);
                    int y2 = (int) (MAP_PADDING + locations.get(i + 1).getLongitude() * (getHeight() - 2 * MAP_PADDING) / 100);
                    g2d.drawLine(x1, y1, x2, y2);
                }
            }
        }
    }

    private void drawPins(Graphics2D g2d) {
        for (int i = 0; i < locations.size(); i++) {
            int pinX = (int) (MAP_PADDING + locations.get(i).getLatitude() * (getWidth() - 2 * MAP_PADDING) / 100);
            int pinY = (int) (MAP_PADDING + locations.get(i).getLongitude() * (getHeight() - 2 * MAP_PADDING) / 100);

            // Highlight special locations
            if (i == 0) {
                // Start: Pencey Prep
                g2d.setColor(new Color(100, 200, 100));
                g2d.fillOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, 2 * PIN_RADIUS, 2 * PIN_RADIUS);
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, 2 * PIN_RADIUS, 2 * PIN_RADIUS);

                g2d.setFont(new Font("Arial", Font.BOLD, 9));
                g2d.drawString("START", pinX + PIN_RADIUS + 5, pinY - 5);
            } else if (i == locations.size() - 1) {
                // End: California Hospital
                g2d.setColor(new Color(200, 100, 100));
                g2d.fillOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, 2 * PIN_RADIUS, 2 * PIN_RADIUS);
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, 2 * PIN_RADIUS, 2 * PIN_RADIUS);

                g2d.setFont(new Font("Arial", Font.BOLD, 9));
                g2d.drawString("END", pinX - 20, pinY - 15);
            } else {
                // NYC locations
                if (hoveredPin == i || selectedPin == i) {
                    g2d.setColor(new Color(255, 200, 0));
                    g2d.fillOval(pinX - PIN_RADIUS - 2, pinY - PIN_RADIUS - 2, 2 * PIN_RADIUS + 4, 2 * PIN_RADIUS + 4);
                }

                g2d.setColor(new Color(100, 150, 220));
                g2d.fillOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, 2 * PIN_RADIUS, 2 * PIN_RADIUS);
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(1));
                g2d.drawOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, 2 * PIN_RADIUS, 2 * PIN_RADIUS);
            }

            // Show tooltip on hover
            if (hoveredPin == i) {
                g2d.setFont(new Font("Arial", Font.PLAIN, 10));
                g2d.setColor(new Color(50, 50, 50));
                String locName = locations.get(i).getName();
                if (locName.length() > 20) {
                    locName = locName.substring(0, 17) + "...";
                }
                g2d.drawString(locName, pinX + PIN_RADIUS + 5, pinY + 5);
            }
        }
    }

    private void drawLegend(Graphics2D g2d) {
        int legendX = getWidth() - 200;
        int legendY = getHeight() - 120;

        g2d.setFont(new Font("Arial", Font.BOLD, 11));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Journey Legend:", legendX, legendY);

        legendY += 20;
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));

        // Start marker
        g2d.setColor(new Color(100, 200, 100));
        g2d.fillOval(legendX, legendY - 8, 12, 12);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(legendX, legendY - 8, 12, 12);
        g2d.drawString("Start (PA)", legendX + 18, legendY + 2);

        legendY += 18;

        // NYC locations
        g2d.setColor(new Color(100, 150, 220));
        g2d.fillOval(legendX, legendY - 8, 12, 12);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(legendX, legendY - 8, 12, 12);
        g2d.drawString("NYC Locations", legendX + 18, legendY + 2);

        legendY += 18;

        // End marker
        g2d.setColor(new Color(200, 100, 100));
        g2d.fillOval(legendX, legendY - 8, 12, 12);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(legendX, legendY - 8, 12, 12);
        g2d.drawString("End (CA)", legendX + 18, legendY + 2);
    }
}
