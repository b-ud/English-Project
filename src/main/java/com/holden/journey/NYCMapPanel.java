package com.holden.journey;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Panel displaying a visual NYC map with Holden's journey path and location pins.
 */
public class NYCMapPanel extends JPanel {
    private final List<JourneyLocation> locations;
    private double[][] nycCoordinates; // Local storage for NYC-specific coordinates
    private int hoveredPin = -1;
    private int selectedPin = -1;
    private MapSelectionListener listener;
    private BufferedImage mapImage;
    private static final int MAP_PADDING = 30;
    private static final int PIN_RADIUS = 12;
    private static final String MAP_RESOURCE = "/New-York-City-Map-New-York-1265x964.jpg";

    public interface MapSelectionListener {
        void locationSelected(JourneyLocation location);
    }

    public NYCMapPanel(List<JourneyLocation> locations) {
        this.locations = locations;
        loadMapImage();
        setupCoordinates();
        setBackground(new Color(245, 250, 255));
        setPreferredSize(new Dimension(mapImage != null ? mapImage.getWidth() : 600,
                mapImage != null ? mapImage.getHeight() : 500));

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

    private void setupCoordinates() {
        // Normalized NYC coordinates (0-100 scale) - stored locally in this panel
        nycCoordinates = new double[][] {
            {10, 45},   // Pencey (outside NYC - PA)
            {55, 50},   // Grand Central (Midtown)
            {54, 52},   // Hotel room
            {59, 55},   // Theater/Restaurant (Midtown)
            {52, 30},   // Central Park
            {50, 27},   // Museum (Upper West Side)
            {52, 32},   // Central Park (ducks)
            {67, 52},   // Antolini's (East Side)
            {55, 50},   // Grand Central (again)
            {8, 92}     // Hospital (California - represented far left/bottom)
        };
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
        for (int i = 0; i < locations.size() && i < nycCoordinates.length; i++) {
            Point pinPoint = getPinPoint(i);
            int pinX = pinPoint.x;
            int pinY = pinPoint.y;

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

        if (mapImage != null) {
            Rectangle imageArea = getImageDrawArea();
            g2d.drawImage(mapImage, imageArea.x, imageArea.y, imageArea.width, imageArea.height, null);
        } else {
            drawMapBackground(g2d);
        }
        drawJourneyPath(g2d);
        drawPins(g2d);
        drawLegend(g2d);
    }

    private Rectangle getImageDrawArea() {
        Rectangle panelArea = new Rectangle(MAP_PADDING, MAP_PADDING,
                getWidth() - 2 * MAP_PADDING, getHeight() - 2 * MAP_PADDING);
        double imageAspect = mapImage.getWidth() / (double) mapImage.getHeight();
        double areaAspect = panelArea.getWidth() / (double) panelArea.getHeight();

        if (imageAspect > areaAspect) {
            int width = panelArea.width;
            int height = (int) (width / imageAspect);
            int y = panelArea.y + (panelArea.height - height) / 2;
            return new Rectangle(panelArea.x, y, width, height);
        } else {
            int height = panelArea.height;
            int width = (int) (height * imageAspect);
            int x = panelArea.x + (panelArea.width - width) / 2;
            return new Rectangle(x, panelArea.y, width, height);
        }
    }

    private void loadMapImage() {
        URL imageUrl = getClass().getResource(MAP_RESOURCE);
        if (imageUrl == null) {
            imageUrl = getClass().getClassLoader().getResource(MAP_RESOURCE.substring(1));
        }
        if (imageUrl == null) {
            File fallback = new File("src/main/resources" + MAP_RESOURCE);
            if (fallback.exists()) {
                try {
                    imageUrl = fallback.toURI().toURL();
                } catch (IOException e) {
                    System.err.println("Unable to resolve NYC map fallback path: " + e.getMessage());
                }
            }
        }
        if (imageUrl != null) {
            try {
                mapImage = ImageIO.read(imageUrl);
            } catch (IOException e) {
                System.err.println("Unable to load NYC map image: " + e.getMessage());
            }
        }
    }

    private Point getPinPoint(int index) {
        Rectangle drawArea = mapImage != null ? getImageDrawArea() : new Rectangle(MAP_PADDING, MAP_PADDING,
                getWidth() - 2 * MAP_PADDING, getHeight() - 2 * MAP_PADDING);
        int pinX = drawArea.x + (int) (nycCoordinates[index][0] * drawArea.width / 100);
        int pinY = drawArea.y + (int) (nycCoordinates[index][1] * drawArea.height / 100);
        return new Point(pinX, pinY);
    }

    private void drawMapBackground(Graphics2D g2d) {
        // Draw NYC map boundary
        g2d.setColor(new Color(200, 220, 240));
        g2d.fillRect(MAP_PADDING, MAP_PADDING, getWidth() - 2 * MAP_PADDING, getHeight() - 2 * MAP_PADDING);

        // Draw grid
        g2d.setColor(new Color(220, 230, 245));
        g2d.setStroke(new BasicStroke(0.5f));
        for (int i = 0; i <= 10; i++) {
            int x = MAP_PADDING + i * (getWidth() - 2 * MAP_PADDING) / 10;
            int y = MAP_PADDING + i * (getHeight() - 2 * MAP_PADDING) / 10;
            g2d.drawLine(x, MAP_PADDING, x, getHeight() - MAP_PADDING);
            g2d.drawLine(MAP_PADDING, y, getWidth() - MAP_PADDING, y);
        }

        // Draw border
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(MAP_PADDING, MAP_PADDING, getWidth() - 2 * MAP_PADDING, getHeight() - 2 * MAP_PADDING);

        // Title
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Holden's NYC Journey Map", 10, 20);
    }

    private void drawJourneyPath(Graphics2D g2d) {
        if (locations.size() < 2 || nycCoordinates.length < 2) return;

        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(new Color(100, 100, 150, 150));

        for (int i = 0; i < locations.size() - 1 && i < nycCoordinates.length - 1; i++) {
            Point p1 = getPinPoint(i);
            Point p2 = getPinPoint(i + 1);

            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);

            // Draw arrow
            drawArrow(g2d, p1.x, p1.y, p2.x, p2.y);
        }
    }

    private void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int arrowLen = 15;
        int arrowWidth = 8;

        int tipX = (int) (x2 - arrowLen * Math.cos(angle));
        int tipY = (int) (y2 - arrowLen * Math.sin(angle));

        int[] xPoints = {x2, (int) (tipX - arrowWidth * Math.sin(angle)), (int) (tipX + arrowWidth * Math.sin(angle))};
        int[] yPoints = {y2, (int) (tipY + arrowWidth * Math.cos(angle)), (int) (tipY - arrowWidth * Math.cos(angle))};

        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    private void drawPins(Graphics2D g2d) {
        for (int i = 0; i < locations.size() && i < nycCoordinates.length; i++) {
            JourneyLocation loc = locations.get(i);
            Point pinPoint = getPinPoint(i);
            int pinX = pinPoint.x;
            int pinY = pinPoint.y;

            // Draw pin circle
            g2d.setColor(loc.getThemeColor());
            g2d.fillOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, PIN_RADIUS * 2, PIN_RADIUS * 2);

            // Draw border
            if (i == selectedPin) {
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(3));
            } else if (i == hoveredPin) {
                g2d.setColor(Color.DARK_GRAY);
                g2d.setStroke(new BasicStroke(2.5f));
            } else {
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(1.5f));
            }
            g2d.drawOval(pinX - PIN_RADIUS, pinY - PIN_RADIUS, PIN_RADIUS * 2, PIN_RADIUS * 2);

            // Draw pin number
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 10));
            String num = String.valueOf(i + 1);
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(num, pinX - fm.stringWidth(num) / 2, pinY + fm.getAscent() / 2);

            // Show tooltip on hover
            if (i == hoveredPin || i == selectedPin) {
                drawTooltip(g2d, pinX, pinY, loc.getName());
            }
        }
    }

    private void drawTooltip(Graphics2D g2d, int x, int y, String text) {
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text) + 8;
        int height = fm.getHeight() + 4;

        int boxX = x - width / 2;
        int boxY = y - PIN_RADIUS - height - 5;

        g2d.setColor(new Color(50, 50, 50, 200));
        g2d.fillRoundRect(boxX, boxY, width, height, 5, 5);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(boxX, boxY, width, height, 5, 5);
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, boxX + 4, boxY + fm.getAscent() + 2);
    }

    private void drawLegend(Graphics2D g2d) {
        int legendX = getWidth() - 180;
        int legendY = getHeight() - 120;

        g2d.setColor(new Color(255, 255, 255, 240));
        g2d.fillRoundRect(legendX - 5, legendY - 5, 175, 115, 5, 5);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawRoundRect(legendX - 5, legendY - 5, 175, 115, 5, 5);

        g2d.setFont(new Font("Arial", Font.BOLD, 11));
        g2d.drawString("Theme Colors:", legendX, legendY + 15);

        g2d.setFont(new Font("Arial", Font.PLAIN, 9));
        String[] themes = {"🟨 Phoniness", "🔴 Despair", "💗 Innocence", "⬜ Urban Decay", "🟢 Nature", "🟠 Betrayal"};
        java.awt.Color[] colors = {
            new java.awt.Color(218, 165, 32), new java.awt.Color(139, 0, 0),
            new java.awt.Color(255, 182, 193), new java.awt.Color(128, 128, 128),
            new java.awt.Color(34, 139, 34), new java.awt.Color(255, 69, 0)
        };

        for (int i = 0; i < themes.length; i++) {
            int row = i / 2;
            int col = i % 2;
            g2d.setColor(colors[i]);
            g2d.fillRect(legendX + col * 85, legendY + 25 + row * 15, 8, 8);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(legendX + col * 85, legendY + 25 + row * 15, 8, 8);
            g2d.drawString(themes[i], legendX + col * 85 + 12, legendY + 31 + row * 15);
        }
    }

    public void setSelectionListener(MapSelectionListener listener) {
        this.listener = listener;
    }
}
