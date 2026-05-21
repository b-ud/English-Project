package com.holden.journey;

/**
 * Main application that builds and displays Holden's journey.
 * Populates journey data with locations, quotes, and analysis.
 */
public class JourneyBuilder {
    public static void main(String[] args) {
        HoldenJourney journey = new HoldenJourney();

        // Day 1 - The Departure and Arrival in the Phonies' World
        JourneyLocation penceyDeparture = new JourneyLocation(
            "Pencey Prep Departure",
            "Day 1 - Saturday",
            "Late afternoon",
            -8.0,
            "Pencey Prep, Agerstown, Pennsylvania",
            "Holden is physically expelled from Pencey, lingering around the city to say goodbye yet feeling emotionally detached.");
        penceyDeparture.addPerson("Mr. Spencer (ex-teacher)");
        penceyDeparture.addQuote(" What I was really hanging around for, I was trying to feel some kind of a good-by(4)");
        penceyDeparture.addAnalysis("Holden's departure from Pencey has a deeper meaning that simply being kicked out of school, it represents his departure from his academic life and a place that his parents wanted him to be at. This not only showed the growth of departure from the parents, but also marked the beginning of Holden's unique point of view on things. His desire for a \"good-by\" shows that he is in search for a good ending of his childhood, at the same time, Holden is miserable, he is numb about what he is supposed to feel because he is emotionally detached from others.");
        penceyDeparture.setThemeticElements("Phoniness, Social decay, Adolescent alienation");
        journey.addLocation(penceyDeparture);

        // Day 1 - Grand Central/Hotel Arrival
        JourneyLocation hotelArrival = new JourneyLocation(
            "Hotel Edmont",
            "Day 1 - Night",
            "Evening/Night",
            -6.5,
            "Manhattan, NYC",
            "Holden arrives in Manhattan after departing from Pencey. Which represents both " +
            "freedom and entrapment to Holden. He checks into the Hotel Edmont, surrounded by phonies and perverts."
        );
        hotelArrival.addPerson("Phonies inside the Hotel");
        hotelArrival.addQuote("We got to the Edmont Hotel, and I checked in. I'd put on my red hunting cap when I was in the cab, just for the hell of it, but I took it off before I checked in. I didn't want to look like a screwball or something. Which is really ironic. I didn't know then that the goddam hotel was full of perverts and morons. Screwballs all over the place. (68)");
        hotelArrival.addAnalysis("");
        hotelArrival.setThemeticElements("Urban decay, Phoniness, Existential dread, Sexual confusion");
        journey.addLocation(hotelArrival);

        // Day 1 - Sally Hayes Encounter (Lead-in)
        JourneyLocation sallyPlans = new JourneyLocation(
            "Planning the Sally Encounter",
            "Day 1 - Night",
            "Late night (phone call)",
            -7.0,
            "Hotel room, Manhattan",
            "Holden calls Sally Hayes, a girl he once dated. He's intoxicated and lonely, " +
            "seeking connection but already cynical about the interaction ahead. He knows " +
            "Sally represents the phoniness he abhors."
        );
        sallyPlans.addPerson("Sally Hayes (off-scene)");
        sallyPlans.addQuote("I'm in terrible shape.");
        sallyPlans.addAnalysis("Holden's admission of psychological breakdown is clothed in his typical " +
            "casual language. He recognizes his deterioration but lacks agency or desire to change. " +
            "The 'terrible shape' prefigures his eventual hospitalization and sets up his pattern " +
            "of emotional instability throughout the novel.");
        sallyPlans.setThemeticElements("Mental health decline, Isolation, Performative social engagement");
        journey.addLocation(sallyPlans);

        // Day 2 - Sally Hayes Date
        JourneyLocation sallyDate = new JourneyLocation(
            "Sally Hayes Date - Theater & Lunch",
            "Day 2 - Sunday",
            "Afternoon/Early evening",
            -4.0,
            "Theater district & upscale restaurant, Midtown Manhattan",
            "Holden takes Sally to a theater matinee and lunch. He initially plays the phony game, " +
            "wearing the social mask. The date reveals his internal conflict: desire for genuine " +
            "connection clashing with his contempt for Sally's superficiality and bourgeois phoniness."
        );
        sallyDate.addPerson("Sally Hayes, theater audience");
        sallyDate.addQuote("She was very merry and all, but I don't think I ever really liked her much.");
        sallyDate.addAnalysis("This reveals Holden's core problem: he's incapable of authentic relationships. " +
            "Sally's cheerfulness doesn't penetrate his alienation. The date satirizes upper-class " +
            "entertainment and dating rituals as hollow performances. Holden resents her for being " +
            "'merry'—for failing to recognize the phoniness they're both performing.");
        sallyDate.setThemeticElements("Class satire, Phoniness, Incapability of connection, Romantic alienation");
        journey.addLocation(sallyDate);

        // Day 2 - Impulsive Escape to Central Park
        JourneyLocation centralParkEscape = new JourneyLocation(
            "Central Park - Breaking Point",
            "Day 2 - Late afternoon",
            "Dusk",
            -8.5,
            "Central Park, Manhattan",
            "After the Sally disaster, Holden breaks down and desperately asks if she'll go away with him. " +
            "When she refuses, he has a near-suicidal ideation. He flees to Central Park, finding temporary " +
            "solace near the ducks. His alienation peaks."
        );
        centralParkEscape.addPerson("Sally Hayes, ducks (symbolic)");
        centralParkEscape.addQuote("It really did depress me, it really did. I'm in New York and it's lousy and phony.");
        centralParkEscape.addAnalysis("The park represents nature and innocence in the phony urban landscape. " +
            "Holden's concern for the ducks reveals his empathy for vulnerable creatures displaced from " +
            "their 'place.' This parallels his own displacement. The repetition ('It really did...it really did') " +
            "emphasizes his spiraling mental state. His connection to nature is more authentic than any human interaction.");
        centralParkEscape.setThemeticElements("Nature vs. urban decay, Suicidal ideation, Compassion amidst alienation");
        journey.addLocation(centralParkEscape);

        // Day 2 - Museum of Natural History
        JourneyLocation museum = new JourneyLocation(
            "Museum of Natural History - Frozen Innocence",
            "Day 2 - Evening",
            "Night",
            -6.0,
            "Museum of Natural History, Upper West Side, Manhattan",
            "After his breakdown, Holden reunites with his younger sister Phoebe. They visit the museum, " +
            "where Holden finds comfort in the static, unchanging displays. He loves the museum because " +
            "it preserves innocence and prevents decay—everything he fears in the real world."
        );
        museum.addPerson("Phoebe (his sister), museum visitors");
        museum.addQuote("The best thing, though, in that museum was that everything always stayed right where it was.");
        museum.addAnalysis("This quote crystallizes Holden's death wish—he yearns for stasis, for preservation " +
            "against time's corruption. The museum represents his desire to freeze youth and innocence " +
            "before phoniness and sexuality destroy them. His love for Phoebe is the only authentic emotion " +
            "he expresses. The museum is a metaphor for his consciousness: protected but ultimately lifeless, " +
            "a retreat from rather than engagement with reality.");
        museum.setThemeticElements("Preservation of innocence, Death wish, Authentic sibling love, Time's corrosion");
        journey.addLocation(museum);

        // Day 2/3 - Ducks in Central Park (Symbolic Concern)
        JourneyLocation ducksSymbol = new JourneyLocation(
            "Obsession with Ducks - Displaced Concern",
            "Day 2 - Multiple times",
            "Throughout the day",
            -7.5,
            "Central Park lagoon, Manhattan",
            "Holden's repeated obsession with where the ducks go in winter represents his displaced " +
            "anxiety. He can't articulate his own displacement, so he fixates on the ducks. This is " +
            "a nervous mannerism masking deeper existential terror about belonging."
        );
        ducksSymbol.addPerson("None (symbolic)");
        ducksSymbol.addQuote("Where do the ducks go? Do you know, by any chance? I mean does somebody go around and notify them that it's time to go, or do they just fly away on their own?");
        ducksSymbol.addAnalysis("This obsessive questioning reveals Holden's need for external guidance and structure. " +
            "He can't fathom autonomous action—mirroring his own paralysis. The ducks symbolize innocence " +
            "displaced by seasonal change (time, maturation). His repeated asking is performative anxiety, " +
            "a way to engage without genuine connection. The question is fundamentally unanswerable, like " +
            "his existential problems.");
        ducksSymbol.setThemeticElements("Displacement, Nervous tic, Existential anxiety, Lost innocence");
        journey.addLocation(ducksSymbol);

        // Day 3 - Mr. Antolini's Apartment (The Predatory Phony)
        JourneyLocation antolini = new JourneyLocation(
            "Mr. Antolini's Apartment - Betrayal of Trust",
            "Day 3 - Monday",
            "Night/Early morning",
            -9.5,
            "East Side, Manhattan",
            "Holden seeks refuge with former teacher Mr. Antolini, who offers food, philosophy, and " +
            "a bed. But Antolini's apparent homosexual advances traumatize Holden. The supposed mentor " +
            "becomes the ultimate phony—intellectually sophisticated but morally corrupt. Holden flees."
        );
        antolini.addPerson("Mr. Antolini (teacher/predator)");
        antolini.addQuote("It's full of phonies, and all you do is get more depressed. So I don't know. It's terrible, it is, truly.");
        antolini.addAnalysis("Antolini articulates Holden's despair while embodying its source: adult hypocrisy. " +
            "The man who recognizes phoniness practices it most dangerously. This scene represents Holden's " +
            "final loss of faith in adult guidance. The attempted assault isn't just sexual; it's a violation " +
            "of trust by the one adult figure who seemed to understand him. This precipitates his total breakdown.");
        antolini.setThemeticElements("Adult hypocrisy, Sexual predation disguised as mentorship, Loss of innocence");
        journey.addLocation(antolini);

        // Day 3 - Grand Central Terminal (Rebirth Fantasy/Breakdown)
        JourneyLocation grandCentral = new JourneyLocation(
            "Grand Central Terminal - The Catcher Fantasy",
            "Day 3 - Early morning",
            "Dawn",
            -9.0,
            "Grand Central Terminal, Manhattan",
            "Fleeing Antolini, Holden spends the night in Grand Central. In the terminal, he fantasizes " +
            "about being 'the catcher in the rye'—rescuing children from falling off the cliff of adulthood. " +
            "This fantasy reveals his desire to prevent all corruption, an impossible, death-oriented fantasy."
        );
        grandCentral.addPerson("None; fantasy children");
        grandCentral.addQuote("Somebody's got to catch you if you start to go over. I'd just be the catcher in the rye and all.");
        grandCentral.addAnalysis("The 'catcher' fantasy is Holden's manifesto of futility. He misunderstands " +
            "'If a body catch a body coming through the rye' (Burns poem, 'If a body MEET a body')—" +
            "his misreading symbolizes his fundamental alienation. The fantasy reveals messianic delusion " +
            "masking suicidal ideation. He wants to freeze the world, prevent growth, deny time. " +
            "This is the novel's thematic climax: childhood vs. adulthood, innocence vs. corruption, " +
            "stasis vs. change. His mission is existentially impossible.");
        grandCentral.setThemeticElements("Messianic delusion, Resistance to maturation, Death wish as salvation");
        journey.addLocation(grandCentral);

        // Day 3 - Breakdown/Psychiatric Hold
        JourneyLocation hospitalEnd = new JourneyLocation(
            "Recovery & Hospitalization - Ambiguous Ending",
            "Day 3 - Later",
            "Throughout Day 3 into recovery",
            -5.0,
            "Psychiatric hospital in California (implied)",
            "After his breakdown, Holden is hospitalized. The novel ends with him ambiguously " +
            "recovering, narrating from a hospital. He's gained perspective but remains deeply " +
            "troubled. The ending is deliberately ambiguous about his psychological status."
        );
        hospitalEnd.addPerson("Hospital staff, psychoanalysts, Phoebe (visits)");
        hospitalEnd.addQuote("I really do. That's the thing about me. I'm quite the sex maniac, in my own way. I really am. I've had quite a few opportunities to lose my virginity and all, but I never got around to it.");
        hospitalEnd.addAnalysis("Even in recovery, Holden's self-awareness is filtered through defensive humor " +
            "and irony. He confesses his sexual confusion and passivity. The admission of never acting " +
            "represents his broader paralysis. His recovery is incomplete; he remains trapped in the " +
            "same alienated consciousness. The hospital doesn't cure him—it merely contains him. " +
            "His final state mirrors his initial state: intelligent, articulate, but fundamentally unable " +
            "to genuinely connect with the human world.");
        hospitalEnd.setThemeticElements("Incomplete recovery, Defensive irony persists, Existential paralysis");
        journey.addLocation(hospitalEnd);

        // Generate the markdown report
        journey.generateMarkdownReport(
            "c:\\Users\\2cvez\\Downloads\\English-Project\\HoldenJourneyAnalysis.md"
        );

        // Print journey summary to console
        System.out.println(journey.getTitle());
        System.out.println("=".repeat(60));
        System.out.println("Total Locations: " + journey.getLocations().size());
        System.out.println("Average Emotional State: " + String.format("%.1f/10", journey.getAverageEmotionalState()));
        System.out.println("=".repeat(60));
        System.out.println("\nJourney Progression:");
        for (JourneyLocation loc : journey.getLocations()) {
            System.out.println(loc.toString());
            System.out.println();
        }
    }
}
