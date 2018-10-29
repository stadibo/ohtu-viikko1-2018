package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author peje
 */
public class StatisticsTest {

    Reader readerStub;

    Statistics stats;

    public StatisticsTest() {
        this.readerStub = new Reader() {
            public List<Player> getPlayers() {
                ArrayList<Player> players = new ArrayList<>();

                players.add(new Player("Semenko", "EDM", 4, 12));
                players.add(new Player("Lemieux", "PIT", 45, 54));
                players.add(new Player("Kurri", "EDM", 37, 53));
                players.add(new Player("Yzerman", "DET", 42, 56));
                players.add(new Player("Gretzky", "EDM", 35, 89));

                return players;
            }
        };
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchingPlayerReturnsCorrectPlayer() {
        Player p1 = stats.search("Lemieux");
        assertEquals("Lemieux, PIT", p1.getName() + ", " + p1.getTeam());
    }

    @Test
    public void searchingNonexistingPlayerReturnsNull() {
        Player p1 = stats.search("Harri");
        assertEquals(null, p1);
    }

    @Test
    public void gettingTeamReturnsCorrectPlayers() {
        List<Player> players = stats.team("EDM");
        assertEquals("Kurri", players.get(1).getName());
    }

    @Test
    public void gettingTopScorersReturnsCorrectPlayers() {
        List<Player> players = stats.topScorers(2);
        assertEquals("Gretzky and Lemieux",
                players.get(0).getName()
                + " and "
                + players.get(1).getName());
    }

}
