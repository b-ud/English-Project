package com.holden.journey;

/**
 * Main application that builds and displays Holden's journey.
 * Populates journey data with locations, quotes, and analysis.
 */
public class JourneyBuilder {
    public static void main(String[] args) {
        HoldenJourney journey = buildJourney();

        // Generate the markdown report
        journey.generateMarkdownReport(
            "c:\\Users\\2cvez\\Downloads\\English-Project\\HoldenJourneyAnalysis.md"
        );

        // Print journey summary to console
        System.out.println(journey.getTitle());
        System.out.println(new String(new char[60]).replace('\0', '='));
        System.out.println("Total Locations: " + journey.getLocations().size());
        System.out.println("Average Emotional State: " + String.format("%.1f/10", journey.getAverageEmotionalState()));
        System.out.println(new String(new char[60]).replace('\0', '='));
        System.out.println("\nJourney Progression:");
        for (JourneyLocation loc : journey.getLocations()) {
            System.out.println(loc.toString());
            System.out.println();
        }
    }

    public static HoldenJourney buildJourney() {
        HoldenJourney journey = new HoldenJourney();

        JourneyLocation penceyDeparture = new JourneyLocation(
            "Pencey Prep Departure",
            "Day 1 - Saturday",
            "Late afternoon",
            -8.0,
            "Pencey Prep, Agerstown, Pennsylvania",
            "Holden is physically expelled from Pencey, lingering around the city to say goodbye yet feeling emotionally detached.");
        penceyDeparture.addPerson("Mr. Spencer (ex-teacher)");
        penceyDeparture.addQuote("What I was really hanging around for, I was trying to feel some kind of a good-by(4)");
        penceyDeparture.addAnalysis("Holden's departure from Pencey has a deeper meaning that simply being kicked out of school, it represents his departure from his academic life and a place that his parents wanted him to be at. This not only showed the growth of departure from the parents, but also marked the beginning of Holden's unique point of view on things. His desire for a \"good-by\" shows that he is in search for a good ending of his childhood, at the same time, Holden is miserable, he is numb about what he is supposed to feel because he is emotionally detached from others.");
        penceyDeparture.setThemeticElements("Phoniness, Social decay, Adolescent alienation");
        journey.addLocation(penceyDeparture);

        JourneyLocation penceyToManhattan = new JourneyLocation(
            "Pencey to Menhattan",
            "Day 1 - Saturday",
            "Late afternoon",
            -7.0,
            "Pencey Prep, Agerstown, Pennsylvania to Manhattan, NYC",
            "Holden departs Pencey and begins his journey back toward Manhattan while still feeling detached and uncertain about where he belongs."
        );
        penceyToManhattan.addPerson("None");
        penceyToManhattan.addQuote("I mean I've left school and places I didn't even know I was leaving. (4)");
        penceyToManhattan.addAnalysis("This quote depicts the transition of Holden's withdrawal from Pencey Prep to returning to Manhattan. It represents the collapse of parental expectations and the beginning of Holden's search for meaning. It also shows his emotional numbness in the face of change.");
        penceyToManhattan.setThemeticElements("Transition, Detachment, Family expectations");
        journey.addLocation(penceyToManhattan);

        JourneyLocation hotelArrival = new JourneyLocation(
            "Hotel Edmont",
            "Day 1 - Night",
            "Evening/Night",
            -6.5,
            "Manhattan, NYC",
            "Holden arrives in Manhattan after departing from Pencey. The city represents both freedom and entrapment for him. He checks into the Hotel Edmont, surrounded by phonies and perverts."
        );
        hotelArrival.addPerson("Phonies inside the Hotel");
        hotelArrival.addQuote("We got to the Edmont Hotel, and I checked in. I'd put on my red hunting cap when I was in the cab, just for the hell of it, but I took it off before I checked in. I didn't want to look like a screwball or something. Which is really ironic. I didn't know then that the goddam hotel was full of perverts and morons. Screwballs all over the place. (68)");
        hotelArrival.addAnalysis("");
        hotelArrival.setThemeticElements("Urban decay, Phoniness, Existential dread, Sexual confusion");
        journey.addLocation(hotelArrival);

        JourneyLocation sallyPlans = new JourneyLocation(
            "Planning the Sally Encounter",
            "Day 1 - Night",
            "Late night (phone call)",
            -7.0,
            "Hotel room, Manhattan",
            "Holden calls Sally Hayes, a girl he once dated. Try to seek emotional connection but already posess a negative feeling about the interaction ahead before even truly calling her. He knows Sally represents the phoniness that he hates and dislikes."
        );
        sallyPlans.addPerson("Sally Hayes (off-scene)");
        sallyPlans.addQuote("She'd written me this long, phony letter, inviting me over to help her trim the Christmas tree Christmas Eve and all-- but I was afraid her mother'd answer the phone.(67)");
        sallyPlans.addAnalysis("This moment of reflection on Sally, who he once dated demonstrated the emotional stuggle and increasing hatred toward phonies. The symbol of a 'phony letter' depicts Holden's growth of despise toward adult hypocrisy and socia conformity. At the same time, Holden seems to have lost hope and excitement toward things he has once loved, which is evident when he convinced himself not to call Sally because he is afraid of Sally's mother. When an individual desires something, they give their best effort to achieve it. But in the case of Holden, he desires something but he is too araid to give action, which shows his own hypocritical nature.");
        sallyPlans.setThemeticElements("Mental health decline, Isolation, Performative social engagement");
        journey.addLocation(sallyPlans);

        JourneyLocation sallyDate = new JourneyLocation(
            "Sally Hayes Date - Theater",
            "Day 2 - Sunday",
            "Afternoon/Early evening",
            -4.0,
            "Theater in Manhattan, New York",
            "Hoolden takes Sally to a date and decides to pick two orchestra seats rather than going with what he likes."
        );
        sallyDate.addPerson("Sally Hayes, theater audience");
        sallyDate.addQuote("So what I did was, I went over and bought two orchestra seats for I Know My Love. It was a benefit performance or something. I didn't much want to see it, but I knew old Sally, the queen of the phonies.(126)");
        sallyDate.addAnalysis("While Holden himself described Sally as a 'queen of the phonies' he is also performing what would be considered a phony action himself trying to earn the happiness of Sally. By selecting a show that would favor Sally but not himself, he is depicting an action of phonies and social conformation as he decided to benefit others over himself when making decisions. Moreover, Sally's cheerfulness and truthfulness doesn't break Holden's barrier toward phonies, he is solely too doubtful and afraid to accept others.");
        sallyDate.setThemeticElements("Phoniness, Incapability of connection, Romantic alienation");
        journey.addLocation(sallyDate);

        JourneyLocation centralParkEscape = new JourneyLocation(
            "Central Park - Breaking Point",
            "Day 2 - Late afternoon",
            "Dusk",
            -8.5,
            "Central Park, Manhattan",
            "After the Sally disaster, Holden breaks down and desperately asks if she'll go away with him. When she refuses, he experiences a mental breakdown and decides to go to Central Park."
        );
        centralParkEscape.addPerson("Sally Hayes, ducks");
        centralParkEscape.addQuote("But I didn't see any ducks around. I walked all around the whole damn lake--I damn near fell in once, in fact--but I didn't see a single duck.(169)");
        centralParkEscape.addAnalysis("The park symbolizes nature and innocence in contrast to the noisy, phony urban cities. Holden's desire to see a duck shows his empathy for vulnerable things and creatures in nature and his authenticity toward nature vs toward the hypocritical society.. However this also demonstrates his own situation of not being able to find someone or something reliable to stay with. His instability is just like that of the ducks.");
        centralParkEscape.setThemeticElements("Nature vs. urban decay, alienation");
        journey.addLocation(centralParkEscape);

        JourneyLocation antolini = new JourneyLocation(
            "Mr. Antolini’s Advice",
            "Day2-Late night",
            "Night",
            -3.0,
            "Mr.Antolini’s apartment in Manhattan, New York",
            "Holden visits Mr.Antolini upon his invitation who gives him serious advice about his future and warns him the importance of taking actions to correct his future, this gave Holden some comfort at the beginning."
        );
        antolini.addPerson("Mr. Antolini");
        antolini.addQuote("Then he said, 'This fall I think you're riding for--it's a special kind of fall, a horrible kind. The man falling isn't permitted to feel or hear himself hit bottom.'(204)");
        antolini.addAnalysis("Mr. Antolini in this case serves as a guidance to Holden as Mr. Antolini is one of the few adults that truly understand the stuggle of Holden. His advice gave Holden a place of comfort and rest from his struggle lately. Mr.Antolini pointed out that Holden has begun to fail as an individual in society and no longer just someone who droppe out of school. Which demonstrates the intensification of Holden's issue but also forced Holden to reconsider his future, giving him some temporary relief until Holden found out the inappropriate side of Mr.Antolini.");
        antolini.setThemeticElements("Mentorship, Guidance, Emotional support");
        journey.addLocation(antolini);

        JourneyLocation antoliniBetrayal = new JourneyLocation(
            "Pervert and Mentor",
            "Day2-Late night",
            "Night",
            -9.5,
            "Mr.Antolini’s apartment in Manhattan, New York",
            "Holden woke up discovering Mr. Antolini patting him on the head and called Mr.Antoline a pervert and a phony."
        );
        antoliniBetrayal.addPerson("Mr. Antolini");
        antoliniBetrayal.addQuote("I could hardly get them on I was so damn nervous. I know more damn perverts, at schools and all, than anybody you ever met, and they're always being perverty when I'm around.(212)");
        antoliniBetrayal.addAnalysis("This paragraph highlights the transition from complete trust to Mr.Antolini to now the disappearing of mentorship bond between Holden and Mr.Antolini; he claims: 'they're always pervert when I'm around' to show that he has lost his trust in Mr.Antolini's word and him as a mentor. The action of patting on Holden's head while he is asleep is seen as inappropriate and dangerous to Holden. For which he is now more hurt that even one of his most trusted adults has become a phony that possesed a goal for inviting Holden over to stay. He became more frustrated and entered a deeper state of distrust for society.");
        antoliniBetrayal.setThemeticElements("Betrayal, Loss of trust, Sexual predation, Alienation");
        journey.addLocation(antoliniBetrayal);

        JourneyLocation remediation = new JourneyLocation(
            "Remediation of innocence",
            "Day 3-Morning",
            "Morning",
            3.0,
            "Manhattan, New York",
            "Holden Watches Phoebe ride the carousel, instead of stopping Phoebe out of protection, Holden allows Phoebe to ride in the Carrousel and have fun."
        );
        remediation.addPerson("Phoebe Caulfield");
        remediation.addQuote("The thing with kids is, if they want to grab the gold ring, you have to let them do it, and not say anything. If they fall off they fall off, but it's bad if you say anything to them.(230)");
        remediation.addAnalysis("This moment marked a shift in Holden's unchanging mindset about phonies and the fact that kids must remain innocent. Before this, he wanted to protect the innocence that Phoebe possessed no matter what happened. But here he allows Phoebe to take the risk of riding in a carousel in order to grow and learn. He has made a deal with himself and accepted the fact that growing up is a process of losing innocence, it often involves risk and danger that can not be avoided. Which also showed his gradual adaptation to accept reality.");
        remediation.setThemeticElements("Innocence, Growth, Acceptance of reality");
        journey.addLocation(remediation);

        JourneyLocation westward = new JourneyLocation(
            "Way to the west",
            "Unkown",
            "Unknown",
            5.0,
            "Mental Hospital in California",
            "Holden has accepted the advice of Mr.Antolini in the end, going to California for mental tratments, spending time with D.B. and resting to understand what reality is"
        );
        westward.addPerson("Hospital staff, psychoanalysts, Phoebe (visits)");
        westward.addQuote("A lot of people, especially this one psychoanalyst guy they have here, keeps asking me if I'm going apply myself when I go back to school next September. It's such a stupid question.(232)");
        westward.addAnalysis("The book ended with a relatively open response to the reader, Holden mentions the fact that he is returning to school. Which can almost be seen as a deal he made with himself-going to school in exchange for understanding the reality and phonies. He is no longer illusional as he was before, yet when he said 'It's such a stupid question' he remains the old judgmental Holden that has always been around.");
        westward.setThemeticElements("Recovery, Reality, Continued judgment");
        journey.addLocation(westward);

        return journey;
    }
}

