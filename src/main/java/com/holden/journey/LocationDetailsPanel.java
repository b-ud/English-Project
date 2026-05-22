package com.holden.journey;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel displaying detailed information about a selected location.
 */
public class LocationDetailsPanel extends JPanel {
    private final JEditorPane detailsPane;
    private static final Color DETAIL_BG = new Color(245, 245, 255);

    public LocationDetailsPanel() {
        setLayout(new BorderLayout());
        setBackground(DETAIL_BG);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        detailsPane = new JEditorPane("text/html", "");
        detailsPane.setEditable(false);
        detailsPane.setBackground(DETAIL_BG);
        detailsPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        detailsPane.setFont(new Font("Arial", Font.PLAIN, 11));
        detailsPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(detailsPane);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        showDefaultMessage();
    }

    public void displayLocation(JourneyLocation location) {
        StringBuilder html = new StringBuilder();

        html.append("<html><body style='font-family:Arial,sans-serif; font-size:12px; color:#111;'>");
        html.append("<h1 style='font-size:18px; margin-bottom:6px;'>").append(location.getName()).append("</h1>");
        html.append("<p style='margin:0 0 12px 0; font-weight:bold; color:#333;'>");
        html.append(location.getLocation()).append("<br>");
        html.append(location.getDay()).append(" - ").append(location.getTimeOfDay()).append("<br>");
        html.append("Emotional State: ").append(String.format("%.1f/10", location.getEmotionalState()));
        html.append("</p>");

        html.append(sectionHtml("Description", location.getDescription()));

        if (!location.getPeopleInvolved().isEmpty()) {
            StringBuilder people = new StringBuilder();
            for (String person : location.getPeopleInvolved()) {
                people.append("<li>").append(person).append("</li>");
            }
            html.append(sectionHtml("People Involved", "<ul style='margin-top:4px;margin-bottom:8px;padding-left:20px;'>" + people + "</ul>"));
        }

        if (!location.getQuotes().isEmpty()) {
            StringBuilder quotes = new StringBuilder();
            for (String quote : location.getQuotes()) {
                quotes.append("<blockquote style='margin:8px 0 12px 20px; color:#333; font-style:italic;'>").append(quote).append("</blockquote>");
            }
            html.append(sectionHtml("Key Quotes", quotes.toString()));
        }

        if (!location.getQuoteAnalysis().isEmpty()) {
            StringBuilder analysis = new StringBuilder();
            for (String analysisLine : location.getQuoteAnalysis()) {
                analysis.append("<p style='margin:6px 0;'>").append(analysisLine).append("</p>");
            }
            html.append(sectionHtml("Analysis", analysis.toString()));
        }

        if (location.getThemeticElements() != null) {
            html.append(sectionHtml("Thematic Elements", location.getThemeticElements()));
        }

        html.append("</body></html>");
        detailsPane.setText(html.toString());
        detailsPane.setCaretPosition(0);
    }

    private String sectionHtml(String title, String body) {
        return "<div style='margin-bottom:12px;'>" +
                "<div style='font-weight:bold; font-size:13px; margin-bottom:4px; color:#222;'>" + title + "</div>" +
                "<div style='line-height:1.4;'>" + body + "</div>" +
                "</div>";
    }

    private void showDefaultMessage() {
        String html = "<html><body style='font-family:Arial,sans-serif; font-size:12px; color:#111;'>" +
                "<h1 style='font-size:18px; margin-bottom:8px;'>Welcome to Holden's Journey</h1>" +
                "<p style='margin:0 0 12px 0;'>Click a location in the timeline or use the NYC buttons below to view a uniform detail page for each stage of Holden's trip.</p>" +
                "<p style='margin:0 0 8px 0; font-weight:bold;'>Key themes:</p>" +
                "<ul style='margin-top:4px; padding-left:20px;'>" +
                "<li>Phoniness and social hypocrisy</li>" +
                "<li>Loss of innocence</li>" +
                "<li>Existential alienation</li>" +
                "<li>Suicidal ideation</li>" +
                "<li>Inability to connect authentically</li>" +
                "</ul>" +
                "</body></html>";
        detailsPane.setText(html);
    }
}
