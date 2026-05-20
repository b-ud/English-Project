package com.holden.journey;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel displaying detailed information about a selected location.
 */
public class LocationDetailsPanel extends JPanel {
    private JourneyLocation selectedLocation;
    private JTextArea detailsText;
    private static final Color DETAIL_BG = new Color(245, 245, 255);

    public LocationDetailsPanel() {
        setLayout(new BorderLayout());
        setBackground(DETAIL_BG);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        detailsText = new JTextArea();
        detailsText.setEditable(false);
        detailsText.setLineWrap(true);
        detailsText.setWrapStyleWord(true);
        detailsText.setFont(new Font("Arial", Font.PLAIN, 11));
        detailsText.setBackground(DETAIL_BG);
        detailsText.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(detailsText);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        showDefaultMessage();
    }

    public void displayLocation(JourneyLocation location) {
        this.selectedLocation = location;
        StringBuilder text = new StringBuilder();

        text.append("═══════════════════════════════════════\n");
        text.append(location.getName()).append("\n");
        text.append("═══════════════════════════════════════\n\n");

        text.append("📍 Location: ").append(location.getLocation()).append("\n");
        text.append("📅 Time: ").append(location.getDay()).append(" - ").append(location.getTimeOfDay()).append("\n");
        text.append("💔 Emotional State: ").append(String.format("%.1f/10", location.getEmotionalState())).append("\n\n");

        text.append("Description:\n");
        text.append(location.getDescription()).append("\n\n");

        if (!location.getPeopleInvolved().isEmpty()) {
            text.append("People Involved:\n");
            for (String person : location.getPeopleInvolved()) {
                text.append("  • ").append(person).append("\n");
            }
            text.append("\n");
        }

        if (!location.getQuotes().isEmpty()) {
            text.append("Key Quotes:\n");
            for (String quote : location.getQuotes()) {
                text.append("  \"").append(quote).append("\"\n\n");
            }
        }

        if (!location.getQuoteAnalysis().isEmpty()) {
            text.append("Analysis:\n");
            for (String analysis : location.getQuoteAnalysis()) {
                text.append("  ").append(analysis).append("\n\n");
            }
        }

        if (location.getThemeticElements() != null) {
            text.append("Thematic Elements:\n");
            text.append("  ").append(location.getThemeticElements()).append("\n");
        }

        detailsText.setText(text.toString());
        detailsText.setCaretPosition(0);
    }

    private void showDefaultMessage() {
        detailsText.setText("Welcome to Holden's Journey\n\n" +
                "Click on a location in the timeline to view details.\n\n" +
                "This interactive visualization chronicles Holden Caulfield's\n" +
                "3-day descent through New York City, tracking his emotional\n" +
                "state, encounters with phoniness, and the events leading to\n" +
                "his psychological breakdown.\n\n" +
                "Key themes:\n" +
                "• Phoniness and social hypocrisy\n" +
                "• Loss of innocence\n" +
                "• Existential alienation\n" +
                "• Suicidal ideation\n" +
                "• Inability to connect authentically");
    }
}
