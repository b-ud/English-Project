package com.holden.journey;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a location in Holden's journey with emotional and narrative data.
 */
public class JourneyLocation {
    private String name;
    private String day;
    private String timeOfDay;
    private double emotionalState; // -10 (despair) to 10 (contentment)
    private String location;
    private String description;
    private List<String> peopleInvolved;
    private List<String> quotes;
    private List<String> quoteAnalysis;
    private double latitude;
    private double longitude;
    private String themeticElements; // phoniness, satire, menial tasks, etc.
    private java.awt.Color themeColor;

    public JourneyLocation(String name, String day, String timeOfDay, 
                          double emotionalState, String location, String description) {
        this.name = name;
        this.day = day;
        this.timeOfDay = timeOfDay;
        this.emotionalState = emotionalState;
        this.location = location;
        this.description = description;
        this.peopleInvolved = new ArrayList<>();
        this.quotes = new ArrayList<>();
        this.quoteAnalysis = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public String getTimeOfDay() { return timeOfDay; }
    public void setTimeOfDay(String timeOfDay) { this.timeOfDay = timeOfDay; }

    public double getEmotionalState() { return emotionalState; }
    public void setEmotionalState(double emotionalState) { 
        this.emotionalState = Math.max(-10, Math.min(10, emotionalState)); 
    }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getPeopleInvolved() { return peopleInvolved; }
    public void addPerson(String person) { this.peopleInvolved.add(person); }

    public List<String> getQuotes() { return quotes; }
    public void addQuote(String quote) { this.quotes.add(quote); }

    public List<String> getQuoteAnalysis() { return quoteAnalysis; }
    public void addAnalysis(String analysis) { this.quoteAnalysis.add(analysis); }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public void setCoordinates(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    public String getThemeticElements() { return themeticElements; }
    public void setThemeticElements(String elements) { this.themeticElements = elements; }

    public java.awt.Color getThemeColor() {
        if (themeColor != null) return themeColor;
        // Map themes to colors
        if (themeticElements != null) {
            if (themeticElements.contains("Phoniness")) return new java.awt.Color(218, 165, 32); // Goldenrod
            if (themeticElements.contains("Despair") || themeticElements.contains("Suicidal")) return new java.awt.Color(139, 0, 0); // Dark red
            if (themeticElements.contains("Innocence")) return new java.awt.Color(255, 182, 193); // Light pink
            if (themeticElements.contains("Urban decay")) return new java.awt.Color(128, 128, 128); // Gray
            if (themeticElements.contains("Nature") || themeticElements.contains("Ducks")) return new java.awt.Color(34, 139, 34); // Forest green
            if (themeticElements.contains("Betrayal") || themeticElements.contains("predation")) return new java.awt.Color(255, 69, 0); // Orange-red
        }
        return new java.awt.Color(100, 149, 237); // Cornflower blue default
    }
    public void setThemeColor(java.awt.Color color) { this.themeColor = color; }

    @Override
    public String toString() {
        return String.format("[%s - %s] %s (Emotional State: %.1f)\n" +
                "Location: %s\n" +
                "Description: %s\n" +
                "People: %s\n" +
                "Themes: %s",
                day, timeOfDay, name, emotionalState, location, description, 
                peopleInvolved, themeticElements);
    }
}
