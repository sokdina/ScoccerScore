package cz.fi.muni.pa165.service;


import com.sun.javafx.scene.control.skin.VirtualFlow;
import cz.fi.muni.pa165.dao.IGameDao;
import cz.fi.muni.pa165.dao.IPlayerDao;
import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.MatchResult;
import cz.fi.muni.pa165.enums.Position;
import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Martin Kocak
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ITeamDao teamDao;

    @Mock
    private IGameDao gameDao;
    
    @Autowired
    @InjectMocks
    private ITeamService teamService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    private Team testTeam;

    @BeforeMethod
    public void prepareTestTeam() {
        testTeam = new Team();
        testTeam.setCity("London");
        testTeam.setCountry("England");
        testTeam.setName("Chelsea FC");
    }
    
    @Test
    public void testCreate() {

        teamService.create(testTeam);
        
        verify(teamDao,times(1)).create(testTeam);
        
    }
    
    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testCreateInvalid() {
        doThrow(new IllegalArgumentException()).when(teamDao).create(null);
        
        teamService.create(null);
       
    }
    
    /**
     * Test of delete method, of class TeamServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        
        Team t1 = new Team();
        t1.setId(1L);
        t1.setName("Arsenal");
        t1.setCountry("England");
        t1.setCity("London");

        when(teamDao.findById(t1.getId())).thenReturn(t1);

        teamService.delete(t1.getId());

        verify(teamDao, times(1)).delete(t1);
        
    }
    
    
    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testDeleteInvalid() {
        doThrow(new IllegalArgumentException()).when(teamDao).delete(null);
      
        teamService.delete(null);
        
    }
    
    @Test
    public void testFindById() {
        Team t1 = new Team();
        t1.setId(1L);
        t1.setName("Arsenal");
        t1.setCountry("England");
        t1.setCity("London");

        when(teamDao.findById(1l)).thenReturn(t1);

        Team t2 = teamService.findById(t1.getId()); 
        
        verify(teamDao, times(2)).findById(1L);

        assertEquals(t2.getId(), t1.getId());
        
       
    }


    /**
     * Test of findByAll method, of class TeamServiceImpl.
     */
    @Test
    public void testFindByAll() {
        System.out.println("findByAll");
        Team team = new Team();
        team.setCity("London");
        team.setCountry("England");
        team.setName("Arsenal FC");
        List<Team> teamList = new ArrayList<>();
        teamList.add(team);
        teamList.add(testTeam);
        
        when(teamDao.findByAll()).thenReturn(teamList);
        
        assertEquals(teamService.findByAll().size(), 2);
        verify(teamDao, times(1)).findByAll();
             
    }

    /**
     * Test of findByName method, of class TeamServiceImpl.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        Team team = new Team();
        team.setCity("Bratislava");
        team.setCountry("Slovakia");
        team.setName("Chelsea FC");
        List<Team> teamList = new ArrayList<>();
        teamList.add(team);
        teamList.add(testTeam);
        when(teamDao.findByName(testTeam.getName())).thenReturn(teamList);
        
        assertEquals(teamService.findByName(testTeam.getName()).get(0).getName(),team.getName());
        assertEquals(teamService.findByName(testTeam.getName()).get(1).getName(),testTeam.getName());
        
        assertNotEquals(teamService.findByName(testTeam.getName()).get(0), teamService.findByName(testTeam.getName()).get(1));
        verify(teamDao,times(4)).findByName(team.getName());
    }
  
    /**
    * Test of addPlayer method, of class TeamServiceImpl.
    */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");
        Player p1 = new Player();
        
        p1.setName("Ronaldo");
        p1.setPosition(Position.FORWARD);
        p1.setDateOfBirth(new Date());
        p1.setDressNumber(7);
        p1.setCountry("Portugal");
              
        teamService.addPlayer(testTeam, p1);
              
        assertTrue(testTeam.getPlayers().contains(p1));
        
        
    }
    
        /**
     * Test of deletePlayer method, of class TeamServiceImpl.
     */
    @Test
    public void testDeletePlayer() {
        System.out.println("deletePlayer");
        Player p1 = new Player();
        
        p1.setName("Ronaldo");
        p1.setPosition(Position.FORWARD);
        p1.setDateOfBirth(new Date());
        p1.setDressNumber(7);
        p1.setCountry("Portugal");
        testTeam.addPlayer(p1);
        
        teamService.deletePlayer(testTeam, p1);
        assertFalse(testTeam.getPlayers().contains(p1));
    }
    
        
    @Test
    public void testCreateTurnamentBrackets() {
        System.out.println("");
        
        Game gameA = new Game();
        Game gameB = new Game();
        Game gameC = new Game();
        Game gameD = new Game();
        Game gameE = new Game();
        Game gameF = new Game();
        Game gameG = new Game();
        Game gameH = new Game();
        
        Team t1 = new Team();
        t1.setName("Chelsea FC");
        Team t2 = new Team();
        t2.setName("Arsenal FC");
        Team t3 = new Team();
        t3.setName("Man City");
        Team t4 = new Team();
        t4.setName("Man Untd");
        Team t5 = new Team();
        t5.setName("Leicester");
        Team t6 = new Team();
        t6.setName("Liverpool");
        
                
        gameA.setHomeScore(3);
        gameA.setGuestScore(1);
        gameA.setMatchResult(MatchResult.HOME_TEAM_WIN);
        
        gameB.setHomeScore(1);
        gameB.setGuestScore(1);
        gameB.setMatchResult(MatchResult.DRAW);
        
        gameC.setHomeScore(0);
        gameC.setGuestScore(2);
        gameC.setMatchResult(MatchResult.GUEST_TEAM_WIN);
        
        gameD.setHomeScore(4);
        gameD.setGuestScore(0);
        gameD.setMatchResult(MatchResult.HOME_TEAM_WIN);
        
        gameE.setHomeScore(1);
        gameE.setGuestScore(0);
        gameE.setMatchResult(MatchResult.HOME_TEAM_WIN);
        
        gameF.setHomeScore(0);
        gameF.setGuestScore(3);
        gameF.setMatchResult(MatchResult.GUEST_TEAM_WIN);
        
        gameG.setHomeScore(3);
        gameG.setGuestScore(3);
        gameG.setMatchResult(MatchResult.DRAW);
        
        gameH.setHomeScore(3);
        gameH.setGuestScore(1);
        gameH.setMatchResult(MatchResult.HOME_TEAM_WIN);
        
        gameA.setHomeTeam(t1);
        gameA.setGuestTeam(t2);
        
        gameB.setHomeTeam(t3);
        gameB.setGuestTeam(t4);
        
        gameC.setHomeTeam(t6);
        gameC.setGuestTeam(t5);
        
        gameD.setHomeTeam(t1);
        gameD.setGuestTeam(t6);
        
        gameE.setHomeTeam(t2);
        gameE.setGuestTeam(t3);
        
        gameF.setHomeTeam(t4);
        gameF.setGuestTeam(t5);
        
        gameG.setHomeTeam(t3);
        gameG.setGuestTeam(t5);
        
        gameH.setHomeTeam(t4);
        gameH.setGuestTeam(t1);       
        
        Set<Team> teams = new HashSet<>();
        teams.add(t1);
        teams.add(t2);
        teams.add(t3);
        teams.add(t4);
        teams.add(t5);
        teams.add(t6);       
        
        List<Game> games = new ArrayList<>();
        games.add(gameA);
        games.add(gameB);
        games.add(gameC);
        games.add(gameD);
        games.add(gameE);
        games.add(gameF);
        games.add(gameG);
        games.add(gameH);

        when(gameDao.findAll()).thenReturn(games);
        
        List<Game> generatedGames = teamService.createTurnamentBrackets(teams);
        
        //It should working that time with at most point(wind and draw) play 
        //with team with at least points
        //our order     pts     team    
        // Liverpool    0   -   t6
        // Man City     2   -   t3
        // Arsenal      3   -   t2
        // Man Untd     4   -   t4
        // Chelsea FC   6   -   t1
        // Leceister    7   -   t5
        
        assertEquals(generatedGames.size(), 3);
        
        //six teams , every call findall 3 times
        verify(gameDao, times(18)).findAll();
        
        //home team has lower pts 
        assertEquals(generatedGames.get(0).getHomeTeam(), t6);
        assertEquals(generatedGames.get(0).getGuestTeam(), t5);
        
        assertEquals(generatedGames.get(1).getHomeTeam(), t3);
        assertEquals(generatedGames.get(1).getGuestTeam(), t1);
        
        assertEquals(generatedGames.get(2).getHomeTeam(), t2);
        assertEquals(generatedGames.get(2).getGuestTeam(), t4);
            
    }
}
