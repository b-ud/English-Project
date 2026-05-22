package com.holden.journey;

import java.awt.*;
import javax.swing.*;

/**
 * Main GUI application for visualizing Holden's 3-day journey.
 */
public class JourneyVisualizerGUI extends JFrame {
    private final HoldenJourney journey;
    private final LocationListPanel locationList;
    private final EmotionalStateGraphPanel graph;
    private final LocationDetailsPanel details;
    private final NYCMapPanel map;
    private final USMapPanel usMap;

    public JourneyVisualizerGUI() {
        setTitle("Holden Caulfield's Journey - Interactive Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLocationRelativeTo(null);

        // Initialize journey
        journey = buildJourney();

        // Create main panels
        locationList = new LocationListPanel(journey.getLocations());
        graph = new EmotionalStateGraphPanel(journey.getLocations());
        details = new LocationDetailsPanel();
        map = new NYCMapPanel(journey.getLocations());
        usMap = new USMapPanel(journey.getLocations());
        JTabbedPane centerTabs = new JTabbedPane();

        // Set up location selection listener
        locationList.setSelectionListener(location -> {
            details.displayLocation(location);
            centerTabs.setSelectedComponent(details);
            repaint();
        });

        map.setSelectionListener(location -> {
            details.displayLocation(location);
            centerTabs.setSelectedComponent(details);
            repaint();
        });

        usMap.setSelectionListener(location -> {
            details.displayLocation(location);
            centerTabs.setSelectedComponent(details);
            repaint();
        });

        // Create layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Left sidebar: location list
        JPanel leftPanel = new JPanel(new BorderLayout());
        JScrollPane listScroll = new JScrollPane(locationList);
        listScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        leftPanel.add(listScroll, BorderLayout.CENTER);
        leftPanel.setPreferredSize(new Dimension(250, 900));
        
        // Center: map and graph in tabs
        
        JScrollPane mapScroll = new JScrollPane(map);
        JScrollPane usMapScroll = new JScrollPane(usMap);
        JPanel nycTab = new JPanel(new BorderLayout());
        nycTab.add(mapScroll, BorderLayout.CENTER);

        centerTabs.addTab("US Journey Map (PA to CA)", usMapScroll);
        centerTabs.addTab("NYC Map & Journey Path", nycTab);
        centerTabs.addTab("Emotional State Graph", graph);
        centerTabs.addTab("Location Details", details);

        // Add panels to main
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(centerTabs, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private HoldenJourney buildJourney() {
        HoldenJourney journey = new HoldenJourney();

        JourneyLocation penceyDeparture = new JourneyLocation(
            "Pencey Prep Departure", "Day 1 - Saturday", "Late afternoon", -8.0,
            "Pencey Prep, Agerstown, Pennsylvania",
            "Holden is physically expelled from Pencey, lingering around the city to say goodbye yet feeling emotionally detached."
        );
        penceyDeparture.addPerson("Mr. Spencer (ex-teacher)");
        penceyDeparture.addQuote("What I was really hanging around for, I was trying to feel some kind of a good-by(4)");
        penceyDeparture.addAnalysis("Holden's departure from Pencey has a deeper meaning than simply being kicked out of school. It represents his departure from the academic life his parents wanted for him, while also marking the beginning of his unique worldview. His desire for a 'good-by' shows he is searching for a good ending to his childhood, even as he feels numb and emotionally detached from others.");
        penceyDeparture.setThemeticElements("Phoniness, Social decay, Adolescent alienation");
        journey.addLocation(penceyDeparture);

        JourneyLocation hotelArrival = new JourneyLocation(
            "Hotel Edmont", "Day 1 - Night", "Evening/Night", -6.5,
            "Manhattan, NYC",
            "Holden arrives in Manhattan after departing from Pencey. The city represents both freedom and entrapment for him. He checks into the Hotel Edmont, surrounded by phonies and perverts."
        );
        hotelArrival.addPerson("Phonies inside the Hotel");
        hotelArrival.addQuote("We got to the Edmont Hotel, and I checked in. I'd put on my red hunting cap when I was in the cab, just for the hell of it, but I took it off before I checked in. I didn't want to look like a screwball or something. Which is really ironic. I didn't know then that the goddam hotel was full of perverts and morons. Screwballs all over the place. (68)");
        hotelArrival.addAnalysis("This scene shows Holden trying to hide while also noticing the phoniness around him. He wants to be ordinary, but he is immediately aware of the corruption and discomfort in the adult world.");
        hotelArrival.setThemeticElements("Urban decay, Phoniness, Existential dread, Sexual confusion");
        journey.addLocation(hotelArrival);

        JourneyLocation sallyPlans = new JourneyLocation(
            "Planning the Sally Encounter", "Day 1 - Night", "Late night (phone call)", -7.0,
            "Hotel room, Manhattan",
            "Holden calls Sally Hayes, a girl he once dated, trying to seek emotional connection while already expecting the interaction to be phony."
        );
        sallyPlans.addPerson("Sally Hayes (off-scene)");
        sallyPlans.addQuote("She'd written me this long, phony letter, inviting me over to help her trim the Christmas tree Christmas Eve and all-- but I was afraid her mother'd answer the phone.(67)");
        sallyPlans.addAnalysis("This moment shows Holden's growing hatred for phonies and his fear of social exposure. He is too afraid of Sally's mother to act, which reveals his own hypocrisy: he wants connection but is paralyzed by dread.");
        sallyPlans.setThemeticElements("Mental health decline, Isolation, Performative social engagement");
        journey.addLocation(sallyPlans);

        JourneyLocation sallyDate = new JourneyLocation(
            "Sally Hayes Date - Theater", "Day 2 - Sunday", "Afternoon/Early evening", -4.0,
            "Theater in Manhattan, New York",
            "Holden takes Sally to a performance he does not really want to see. He chooses the experience for her, not himself."
        );
        sallyDate.addPerson("Sally Hayes, theater audience");
        sallyDate.addQuote("So what I did was, I went over and bought two orchestra seats for I Know My Love. It was a benefit performance or something. I didn't much want to see it, but I knew old Sally, the queen of the phonies.(126)");
        sallyDate.addAnalysis("Holden's behavior is phony in the way he sacrifices his own preferences for Sally's approval. He calls her a 'queen of the phonies,' yet he is also performing phoniness to earn her favor.");
        sallyDate.setThemeticElements("Phoniness, Incapability of connection, Romantic alienation");
        journey.addLocation(sallyDate);

        JourneyLocation centralPark = new JourneyLocation(
            "Central Park - Breaking Point", "Day 2 - Late afternoon", "Dusk", -8.5,
            "Central Park, Manhattan",
            "After the Sally disaster, Holden breaks down and walks through Central Park, searching for the ducks and relief."
        );
        centralPark.addPerson("Sally Hayes, ducks");
        centralPark.addQuote("But I didn't see any ducks around. I walked all around the whole damn lake--I damn near fell in once, in fact--but I didn't see a single duck.(169)");
        centralPark.addAnalysis("The park symbolizes nature and innocence in contrast to the noisy, phony city. Holden's search for ducks reveals his empathy for displaced creatures and his own sense of being lost.");
        centralPark.setThemeticElements("Nature vs. urban decay, Alienation");
        journey.addLocation(centralPark);

        JourneyLocation museum = new JourneyLocation(
            "Museum of Natural History - Frozen Innocence", "Day 2 - Evening", "Night", -6.0,
            "Museum of Natural History, Upper West Side, Manhattan",
            "Holden visits the museum looking for a place that never changes. The frozen exhibits mirror his wish to preserve innocence and avoid painful life changes."
        );
        museum.addPerson("Phoebe Caulfield");
        museum.addQuote("The best thing, though, in that museum was that everything always stayed right where it was.");
        museum.addAnalysis("Holden wants the world to stay the same. The museum comforts him because it preserves innocence, but it also underscores his resistance to real-life change.");
        museum.setThemeticElements("Preservation of innocence, Stagnation, Fear of change");
        journey.addLocation(museum);

        JourneyLocation antoliniAdvice = new JourneyLocation(
            "Mr. Antolini's Advice", "Day 3 - Monday", "Late night", -3.0,
            "Mr. Antolini's apartment, Manhattan, New York",
            "Holden visits Mr. Antolini and receives serious advice about a dangerous emotional fall. He briefly feels understood."
        );
        antoliniAdvice.addPerson("Mr. Antolini");
        antoliniAdvice.addQuote("Then he said, 'This fall I think you're riding for--it's a special kind of fall, a horrible kind. The man falling isn't permitted to feel or hear himself hit bottom.'(204)");
        antoliniAdvice.addAnalysis("Mr. Antolini serves as one of the few adults who understands Holden's struggle. His warning gives Holden temporary hope, but it also forces Holden to confront the severity of his own decline.");
        antoliniAdvice.setThemeticElements("Mentorship, Warning, Adult hypocrisy");
        journey.addLocation(antoliniAdvice);

        JourneyLocation antoliniBetrayal = new JourneyLocation(
            "Mr. Antolini Betrayal", "Day 3 - Early morning", "Early morning", -9.5,
            "Mr. Antolini's apartment, Manhattan, New York",
            "Holden wakes to find Mr. Antolini behaving in an inappropriate way. The betrayal shatters Holden's trust in one of the few adults he respected."
        );
        antoliniBetrayal.addPerson("Mr. Antolini");
        antoliniBetrayal.addQuote("I could hardly get them on I was so damn nervous. I know more damn perverts, at schools and all, than anybody you ever met, and they're always being perverty when I'm around.(212)");
        antoliniBetrayal.addAnalysis("The incident turns Holden's fragile trust into deep distrust. Mr. Antolini becomes another phony, and Holden retreats further from safety and human connection.");
        antoliniBetrayal.setThemeticElements("Betrayal, Loss of trust, Sexual predation, Alienation");
        journey.addLocation(antoliniBetrayal);

        JourneyLocation remediation = new JourneyLocation(
            "Remediation of Innocence", "Day 3 - Morning", "Morning", 3.0,
            "Manhattan, New York",
            "Holden watches Phoebe ride the carousel and chooses not to stop her. He accepts that innocence must grow even if it means letting go."
        );
        remediation.addPerson("Phoebe Caulfield");
        remediation.addQuote("The thing with kids is, if they want to grab the gold ring, you have to let them do it, and not say anything. If they fall off they fall off, but it's bad if you say anything to them.(230)");
        remediation.addAnalysis("Holden finally accepts that protecting innocence cannot mean freezing life. He allows Phoebe to take a risk, showing his slow shift toward reality.");
        remediation.setThemeticElements("Innocence, Growth, Acceptance of reality");
        journey.addLocation(remediation);

        JourneyLocation westward = new JourneyLocation(
            "Way to the West", "Day 3 - Later", "Unknown", 5.0,
            "Mental hospital in California",
            "Holden has accepted Mr. Antolini's advice and traveled west for treatment, resting with D.B. and trying to understand reality again."
        );
        westward.addPerson("Hospital staff, D.B.");
        westward.addQuote("A lot of people, especially this one psychoanalyst guy they have here, keeps asking me if I'm going apply myself when I go back to school next September. It's such a stupid question.(232)");
        westward.addAnalysis("The ending is open, but Holden's willingness to consider school again suggests he is slowly negotiating the gap between his ideals and the world. He remains judgmental, yet less lost.");
        westward.setThemeticElements("Recovery, Reality, Continued judgment");
        journey.addLocation(westward);

        return journey;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JourneyVisualizerGUI());
    }
}
