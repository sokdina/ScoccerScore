package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.IGameDao;
import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.MatchResult;
import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;

/**
 *
 * @author sokdina999@gmail.com
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GameServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private IGameDao gameDao;

    @Mock
    private ITeamDao teamDao;
    
    @Autowired
    @InjectMocks
    private IGameService gameService;

    private Game game;
    private Team teamOne, teamTwo;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @AfterClass
    public void cleanupMocks(){
        Mockito.reset(gameDao);
        Mockito.reset(teamDao);
    }
    
    @BeforeMethod
    public void prepareTestGame() {
        
	teamOne = new Team();	
        teamOne.setId(1L);
	teamOne.setName("FC Barcelona");
	teamOne.setCity("Barcelona");
	teamOne.setCountry("Spain");

	teamTwo = new Team();
        teamTwo.setId(2L);
	teamTwo.setName("Real Madrid C.F.");
	teamTwo.setCity("Madrid");
	teamTwo.setCountry("Spain");

	game = new Game(); 
	
	game.setId(1L);	
	game.setHomeTeam(teamOne);
        game.setGuestTeam(teamTwo);
        game.setDateOfGame(new Date());
        game.setMatchResult(MatchResult.DRAW);
        game.setHomeScore(3);
        game.setGuestScore(3);
    }

    @Test
    public void createGame() {
        System.out.println("test Create a Game");

	gameService.create(game);
        verify(gameDao, times(1)).create(game);
    }

    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testInvalidCreate() {
        doThrow(new IllegalArgumentException()).when(gameDao).create(null);
        gameService.create(null);
    }

    @Test
    public void testUpdate(){
	System.out.println("test Update a Game");

        gameService.update(game);
        verify(gameDao, times(1)).update(game);
    }
    
    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testInvalidDelete() {
        doThrow(new IllegalArgumentException()).when(gameDao).update(null);
        gameService.update(null);
    }
    
    @Test
    public void testDelete(){
        System.out.println("test delete a Game");

	gameService.delete(game);
        verify(gameDao, times(1)).delete(game);
    }

    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testDeleteInvalid() {
        doThrow(new IllegalArgumentException()).when(gameDao).delete(null);
      
        gameService.delete(null);      
    }

    @Test
    public void testFindGamesBetweenTeams(){
        System.out.println("test Find Games Between two Teams");
 
 	List<Game> games = new ArrayList();
	Game gameTwo = new Game();

	gameTwo.setHomeTeam(teamOne);
        gameTwo.setGuestTeam(teamTwo);
        gameTwo.setDateOfGame(new Date());
        gameTwo.setMatchResult(MatchResult.DRAW);
        gameTwo.setHomeScore(1);
        gameTwo.setGuestScore(1);

        games.add(game);
 	games.add(gameTwo);

        when(gameDao.findGamesBetweenTeams(teamOne.getId(), teamTwo.getId())).thenReturn(games);    
        assertEquals(gameService.findGamesBetweenTeams(teamOne.getId(), teamTwo.getId()).size(), 2);
        verify(gameDao, times(1)).findGamesBetweenTeams(teamOne.getId(), teamTwo.getId());
    }

    @Test
    public void testFindById() {
	System.out.println("test Find By Id");
       
	when(gameDao.findById(game.getId())).thenReturn(game);
        Game gameTwo = gameService.findById(game.getId()); 
        verify(gameDao, times(1)).findById(game.getId());
        assertTrue(game.equals(gameTwo));
    }

    @Test
    public void testfindAll() {
	System.out.println("test Find By Id");

        List<Game> games = new ArrayList();
	Game gameTwo = new Game();

	gameTwo.setHomeTeam(teamOne);
        gameTwo.setGuestTeam(teamTwo);
        gameTwo.setDateOfGame(new Date());
        gameTwo.setMatchResult(MatchResult.DRAW);
        gameTwo.setHomeScore(3);
        gameTwo.setGuestScore(3);
        
        games.add(game);
 	games.add(gameTwo);
        
       
        when(gameDao.findAll()).thenReturn(games);
        assertEquals(gameService.findAll().size(), 2);
        verify(gameDao, times(1)).findAll();
    }
    
    
    @Test
    public void testGenerateSeasonMatches() {
        List<Team> teams = new ArrayList<>();
     
        Team teamThree = new Team();
        teamThree.setId(3L);
	teamThree.setName("Liverpool");
	teamThree.setCity("Liverpool");
	teamThree.setCountry("England");
        
        Team teamFour = new Team();
        teamFour.setId(4L);
	teamFour.setName("Arsenal");
	teamFour.setCity("London");
	teamFour.setCountry("England");
        
        teams.add(teamOne);
        teams.add(teamTwo);
        teams.add(teamThree);
        teams.add(teamFour);
        
        when(teamDao.findByAll()).thenReturn(teams);
        
        assertEquals(gameService.generateSeasonMatches().size(), 3);
        assertEquals(gameService.generateSeasonMatches().get(0).size(), 2);
        
        boolean[][] matchesBetween = new boolean[4][4];
        for(int i = 0; i < matchesBetween.length; i++){
            for(int j = 0; j < matchesBetween.length; j++){
                matchesBetween[i][j] = false;
            }
        }
        
        //Test if everybody played with everybody
        List<List<Pair>> allMatches = allMatches = gameService.generateSeasonMatches();
        for(List<Pair> round : allMatches){
            for(Pair match : round){
                System.out.println(Integer.parseInt(((Team)match.getKey()).getId().toString())-1);
                System.out.println(Integer.parseInt(((Team)match.getValue()).getId().toString())-1);
                System.out.println("");
                matchesBetween[Integer.parseInt(((Team)match.getKey()).getId().toString())-1][Integer.parseInt(((Team)match.getValue()).getId().toString())-1] = true;
                matchesBetween[Integer.parseInt(((Team)match.getValue()).getId().toString())-1][Integer.parseInt(((Team)match.getKey()).getId().toString())-1] = true;
            }
        }
        
        for(int i = 0; i < matchesBetween.length; i++){
            for(int j = 0; j < matchesBetween.length; j++){
                if(i!=j)assertTrue(matchesBetween[i][j]);
            }
        }
        
        verify(teamDao, Mockito.atLeast(1)).findByAll();
    }
    
 
    @Test
    public void testGenerateSeasonMatchesForOddNumberOfTeams() {
        List<Team> teams = new ArrayList<>();
     
        Team teamThree = new Team();
        teamThree.setId(3L);
	teamThree.setName("Liverpool");
	teamThree.setCity("Liverpool");
	teamThree.setCountry("England");
        
        Team teamFour = new Team();
        teamFour.setId(4L);
	teamFour.setName("Arsenal");
	teamFour.setCity("London");
	teamFour.setCountry("England");
        
        Team teamFive = new Team();
        teamFive.setId(5L);
	teamFive.setName("PSG");
	teamFive.setCity("Paris");
	teamFive.setCountry("France");
        
        teams.add(teamOne);
        teams.add(teamTwo);
        teams.add(teamThree);
        teams.add(teamFour);
        teams.add(teamFive);
        
        when(teamDao.findByAll()).thenReturn(teams);
        
        assertEquals(gameService.generateSeasonMatches().size(), 5);
        assertEquals(gameService.generateSeasonMatches().get(0).size(), 2);
        
        boolean[][] matchesBetween = new boolean[5][5];
        for(int i = 0; i < matchesBetween.length; i++){
            for(int j = 0; j < matchesBetween.length; j++){
                matchesBetween[i][j] = false;
            }
        }
        
        //Test if everybody played with everybody
        List<List<Pair>> allMatches = allMatches = gameService.generateSeasonMatches();
        for(List<Pair> round : allMatches){
            for(Pair match : round){
                System.out.println(Integer.parseInt(((Team)match.getKey()).getId().toString())-1);
                System.out.println(Integer.parseInt(((Team)match.getValue()).getId().toString())-1);
                System.out.println("");
                matchesBetween[Integer.parseInt(((Team)match.getKey()).getId().toString())-1][Integer.parseInt(((Team)match.getValue()).getId().toString())-1] = true;
                matchesBetween[Integer.parseInt(((Team)match.getValue()).getId().toString())-1][Integer.parseInt(((Team)match.getKey()).getId().toString())-1] = true;
            }
        }
        
        for(int i = 0; i < matchesBetween.length; i++){
            for(int j = 0; j < matchesBetween.length; j++){
                if(i!=j)assertTrue(matchesBetween[i][j]);
            }
        }
        
        verify(teamDao, Mockito.atLeast(1)).findByAll();
    }

}
