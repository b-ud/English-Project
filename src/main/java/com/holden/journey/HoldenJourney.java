package com.holden.journey;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the complete 3-day journey of Holden Caulfield through New York City.
 * Tracks physical locations, emotional trajectory, and thematic elements.
 */
public class HoldenJourney {
    private List<JourneyLocation> locations;
    private String title;
    private String journeyStart;
    private String journeyEnd;

    public HoldenJourney() {
        this.locations = new ArrayList<>();
        this.title = "Holden Caulfield's 3-Day Descent: A Journey Through Phoniness and Despair";
        this.journeyStart = "Pencey Prep, Pennsylvania";
        this.journeyEnd = "Psychiatric Hospital, New York";
    }

    public void addLocation(JourneyLocation location) {
        this.locations.add(location);
    }

    public List<JourneyLocation> getLocations() {
        return locations;
    }

    public JourneyLocation getLocationByName(String name) {
        return locations.stream()
                .filter(loc -> loc.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public double getAverageEmotionalState() {
        if (locations.isEmpty()) return 0;
        double sum = locations.stream()
                .mapToDouble(JourneyLocation::getEmotionalState)
                .sum();
        return sum / locations.size();
    }

    public void generateMarkdownReport(String filePath) {
        StringBuilder report = new StringBuilder();
        report.append("# ").append(title).append("\n\n");
        report.append("## Journey Overview\n");
        report.append("- **Start**: ").append(journeyStart).append("\n");
        report.append("- **End**: ").append(journeyEnd).append("\n");
        report.append("- **Duration**: 3 Days\n");
        report.append("- **Average Emotional State**: ").append(String.format("%.1f", getAverageEmotionalState())).append("\n\n");

        report.append("## Detailed Timeline\n\n");

        for (int i = 0; i < locations.size(); i++) {
            JourneyLocation loc = locations.get(i);
            report.append(String.format("### %d. %s\n", i + 1, loc.getName()));
            report.append("**Day/Time**: ").append(loc.getDay()).append(" - ").append(loc.getTimeOfDay()).append("\n\n");
            report.append("**Location**: ").append(loc.getLocation()).append("\n\n");
            report.append("**Description**: ").append(loc.getDescription()).append("\n\n");
            report.append("**Emotional State**: ").append(String.format("%.1f/10", loc.getEmotionalState())).append("\n\n");
            
            if (!loc.getPeopleInvolved().isEmpty()) {
                report.append("**People Involved**: ");
                report.append(String.join(", ", loc.getPeopleInvolved())).append("\n\n");
            }

            if (!loc.getQuotes().isEmpty()) {
                report.append("**Key Quote(s)**:\n");
                for (int j = 0; j < loc.getQuotes().size(); j++) {
                    report.append("- \"").append(loc.getQuotes().get(j)).append("\"\n");
                }
                report.append("\n");
            }

            if (!loc.getQuoteAnalysis().isEmpty()) {
                report.append("**Analysis**:\n");
                for (String analysis : loc.getQuoteAnalysis()) {
                    report.append("- ").append(analysis).append("\n");
                }
                report.append("\n");
            }

            if (loc.getThemeticElements() != null) {
                report.append("**Thematic Elements**: ").append(loc.getThemeticElements()).append("\n\n");
            }

            report.append("---\n\n");
        }

        try {
            java.nio.file.Files.write(
                java.nio.file.Paths.get(filePath),
                report.toString().getBytes()
            );
            System.out.println("Report generated: " + filePath);
        } catch (Exception e) {
            System.err.println("Error writing report: " + e.getMessage());
        }
    }

    public String getTitle() { return title; }
    public String getJourneyStart() { return journeyStart; }
    public String getJourneyEnd() { return journeyEnd; }
}
