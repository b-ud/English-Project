package com.holden.journey;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Interactive location list panel that allows clicking to view details.
 */
public class LocationListPanel extends JPanel {
    private List<JourneyLocation> locations;
    private int selectedIndex = -1;
    private LocationSelectionListener listener;
    private static final Color HOVER_COLOR = new Color(220, 220, 240);
    private static final Color SELECTED_COLOR = new Color(100, 100, 200);
    private int hoveredIndex = -1;

    public interface LocationSelectionListener {
        void locationSelected(JourneyLocation location);
    }

    public LocationListPanel(List<JourneyLocation> locations) {
        this.locations = locations;
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int newHovered = calculateIndex(e.getY());
                if (newHovered != hoveredIndex) {
                    hoveredIndex = newHovered;
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int newHovered = calculateIndex(e.getY());
                if (newHovered != hoveredIndex) {
                    hoveredIndex = newHovered;
                    repaint();
                }
            }
        });

        setPreferredSize(new Dimension(250, 500));
    }

    private void handleClick(int y) {
        int index = calculateIndex(y);
        if (index >= 0 && index < locations.size()) {
            selectedIndex = index;
            if (listener != null) {
                listener.locationSelected(locations.get(index));
            }
            repaint();
        }
    }

    private int calculateIndex(int y) {
        int itemHeight = 60;
        int index = (y - 10) / itemHeight;
        if (index >= 0 && index < locations.size()) {
            return index;
        }
        return -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString("Journey Timeline", 10, 25);

        int y = 50;
        int itemHeight = 60;

        for (int i = 0; i < locations.size(); i++) {
            JourneyLocation loc = locations.get(i);
            int x = 10;

            // Draw background for hovered/selected items
            if (i == selectedIndex) {
                g2d.setColor(SELECTED_COLOR);
                g2d.fillRect(0, y - 10, getWidth(), itemHeight);
            } else if (i == hoveredIndex) {
                g2d.setColor(HOVER_COLOR);
                g2d.fillRect(0, y - 10, getWidth(), itemHeight);
            }

            // Draw location info
            g2d.setColor(i == selectedIndex ? Color.WHITE : Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 11));
            g2d.drawString((i + 1) + ". " + loc.getName(), x, y);

            g2d.setFont(new Font("Arial", Font.PLAIN, 9));
            g2d.drawString(loc.getDay(), x, y + 15);

            // Draw theme color indicator (left)
            g2d.setColor(loc.getThemeColor());
            g2d.fillRect(x, y + 20, 15, 12);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawRect(x, y + 20, 15, 12);

            // Draw emotional state indicator (right)
            double emotion = loc.getEmotionalState();
            Color emotionColor = getEmotionColor(emotion);
            g2d.setColor(emotionColor);
            g2d.fillRect(x + 18, y + 20, 15, 12);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x + 18, y + 20, 15, 12);
            g2d.drawString(String.format("%.1f", emotion), x + 36, y + 28);

            y += itemHeight;
        }
    }

    private Color getEmotionColor(double emotion) {
        // Red for despair, yellow for neutral, green for happy
        if (emotion < -5) return new Color(200, 50, 50);
        if (emotion < 0) return new Color(255, 150, 50);
        return new Color(100, 200, 100);
    }

    public void setSelectionListener(LocationSelectionListener listener) {
        this.listener = listener;
    }
}
