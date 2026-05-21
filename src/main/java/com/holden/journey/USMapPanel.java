package com.holden.journey;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Panel displaying a US map with Holden's complete journey from PA to CA.
 */
public class USMapPanel extends JPanel {
    private List<JourneyLocation> locations;
    private int hoveredPin = -1;
    private int selectedPin = -1;
    private MapSelectionListener listener;
    private BufferedImage mapImage;
    private static final int MAP_PADDING = 40;
    private static final int PIN_RADIUS = 10;

    public interface MapSelectionListener {
        void locationSelected(JourneyLocation location);
    }

    public USMapPanel(List<JourneyLocation> locations) {
        this.locations = locations;
        loadMapImage();
        setupUSCoordinates();
        setBackground(new Color(240, 245, 250));
        setPreferredSize(new Dimension(mapImage != null ? mapImage.getWidth() : 1000,
                mapImage != null ? mapImage.getHeight() : 600));

        MouseAdapter mouseHandler = new MouseAdapter() {
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
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    private void loadMapImage() {
        URL imageUrl = getClass().getResource("/us-map.png");
        if (imageUrl != null) {
            try {
                mapImage = ImageIO.read(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupUSCoordinates() {
        double[][] usCoords = {
            {40.44, -75.33},   // Pencey Prep (Pennsylvania) - Start
            {40.75, -73.98},   // NYC - Grand Central Terminal
            {40.75, -73.98},   // Hotel Edmont (same area)
            {40.75, -73.98},   // Sally Hayes Date (same area)
            {40.75, -73.98},   // Central Park (same area)
            {40.75, -73.98},   // Museum (same area)
            {40.75, -73.98},   // Ducks (same area)
            {40.75, -73.98},   // Antolini's Apartment (same area)
            {40.75, -73.98},   // Grand Central Terminal (same area)
            {37.77, -122.42}   // California - Hospital (San Francisco area)
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
            Point pinPoint = geoToPixel(locations.get(i).getLatitude(), locations.get(i).getLongitude());
            if (Math.sqrt(Math.pow(x - pinPoint.x, 2) + Math.pow(y - pinPoint.y, 2)) <= PIN_RADIUS) {
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

        if (mapImage != null) {
            Rectangle mapArea = getMapArea();
            g2d.drawImage(mapImage, mapArea.x, mapArea.y, mapArea.width, mapArea.height, null);
        } else {
            drawMapBackground(g2d);
            drawUSOutline(g2d);
        }

        drawStateLabels(g2d);
        drawJourneyPath(g2d);
        drawPins(g2d);
        drawLegend(g2d);
    }

    private Rectangle getMapArea() {
        return new Rectangle(MAP_PADDING, MAP_PADDING,
                Math.max(100, getWidth() - 2 * MAP_PADDING), Math.max(100, getHeight() - 2 * MAP_PADDING));
    }

    private Point geoToPixel(double lat, double lon) {
        Rectangle mapArea = getMapArea();
        double minLat = 24.5;
        double maxLat = 49.5;
        double minLon = -125.0;
        double maxLon = -66.5;

        double xNorm = (lon - minLon) / (maxLon - minLon);
        double yNorm = (maxLat - lat) / (maxLat - minLat);
        xNorm = Math.max(0, Math.min(1, xNorm));
        yNorm = Math.max(0, Math.min(1, yNorm));

        int x = mapArea.x + (int) (xNorm * mapArea.width);
        int y = mapArea.y + (int) (yNorm * mapArea.height);
        return new Point(x, y);
    }

    private void drawMapBackground(Graphics2D g2d) {
        g2d.setColor(new Color(170, 200, 240));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(new Color(220, 235, 200));
        g2d.fillRect(MAP_PADDING, MAP_PADDING, getWidth() - 2 * MAP_PADDING, getHeight() - 2 * MAP_PADDING);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(MAP_PADDING, MAP_PADDING, getWidth() - 2 * MAP_PADDING, getHeight() - 2 * MAP_PADDING);

        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Holden's Journey: From Pennsylvania to California", 10, 25);
    }

    private void drawUSOutline(Graphics2D g2d) {
        g2d.setColor(new Color(100, 100, 100, 50));
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawRect(MAP_PADDING, MAP_PADDING, getWidth() - 2 * MAP_PADDING, getHeight() - 2 * MAP_PADDING);
    }

    private void drawStateLabels(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        g2d.setColor(new Color(100, 100, 100));

        Point paPoint = geoToPixel(40.44, -75.33);
        g2d.drawString("Pennsylvania", paPoint.x + 10, paPoint.y - 10);

        Point nyPoint = geoToPixel(40.75, -73.98);
        g2d.drawString("New York", nyPoint.x + 10, nyPoint.y + 15);

        Point caPoint = geoToPixel(37.77, -122.42);
        g2d.drawString("California", caPoint.x - 70, caPoint.y + 20);
    }

    private void drawJourneyPath(Graphics2D g2d) {
        if (locations.size() < 2) return;

        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(new Color(220, 100, 100, 180));

        Point start = geoToPixel(locations.get(0).getLatitude(), locations.get(0).getLongitude());
        Point end = geoToPixel(locations.get(locations.size() - 1).getLatitude(), locations.get(locations.size() - 1).getLongitude());
        g2d.drawLine(start.x, start.y, end.x, end.y);

        if (locations.size() > 2) {
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setColor(new Color(100, 150, 200, 150));

            for (int i = 1; i < locations.size() - 1; i++) {
                Point p1 = geoToPixel(locations.get(i).getLatitude(), locations.get(i).getLongitude());
                Point p2 = geoToPixel(locations.get(i + 1).getLatitude(), locations.get(i + 1).getLongitude());
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    private void drawPins(Graphics2D g2d) {
        for (int i = 0; i < locations.size(); i++) {
            Point pinPoint = geoToPixel(locations.get(i).getLatitude(), locations.get(i).getLongitude());
            int pinX = pinPoint.x;
            int pinY = pinPoint.y;

            if (i == 0) {
                g2d.setColor(new Color(100, 200, 100));
                g2d.fillOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, 2 * PIN_RADIUS, 2 * PIN_RADIUS);
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, 2 * PIN_RADIUS, 2 * PIN_RADIUS);
                g2d.setFont(new Font("Arial", Font.BOLD, 9));
                g2d.drawString("START", pinX + PIN_RADIUS + 5, pinY - 5);
            } else if (i == locations.size() - 1) {
                g2d.setColor(new Color(200, 100, 100));
                g2d.fillOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, 2 * PIN_RADIUS, 2 * PIN_RADIUS);
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, 2 * PIN_RADIUS, 2 * PIN_RADIUS);
                g2d.setFont(new Font("Arial", Font.BOLD, 9));
                g2d.drawString("END", pinX - 20, pinY - 15);
            } else {
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

        g2d.setColor(new Color(100, 200, 100));
        g2d.fillOval(legendX, legendY - 8, 12, 12);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(legendX, legendY - 8, 12, 12);
        g2d.drawString("Start (PA)", legendX + 18, legendY + 2);

        legendY += 18;

        g2d.setColor(new Color(100, 150, 220));
        g2d.fillOval(legendX, legendY - 8, 12, 12);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(legendX, legendY - 8, 12, 12);
        g2d.drawString("NYC Locations", legendX + 18, legendY + 2);

        legendY += 18;

        g2d.setColor(new Color(200, 100, 100));
        g2d.fillOval(legendX, legendY - 8, 12, 12);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(legendX, legendY - 8, 12, 12);
        g2d.drawString("End (CA)", legendX + 18, legendY + 2);
    }
}
