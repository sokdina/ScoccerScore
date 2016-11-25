package cz.fi.muni.pa165.service;


import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dao.IPlayerDao;
import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.Position;
import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import java.util.Set;
import java.util.HashSet;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class PlayerServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private IPlayerDao playerDao;
  
    @Autowired
    @InjectMocks
    private IPlayerService playerService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    private Player testPlayer;
    private Team testTeam;

    @BeforeMethod
    public void prepareTestPlayer() {
       
        testPlayer = new Player();
        
        
        testPlayer.setName("Lionel Messi");
        testPlayer.setPosition(Position.FORWARD);
        testPlayer.setDateOfBirth(new Date(System.currentTimeMillis()));
        testPlayer.setDressNumber(10);
        testPlayer.setCountry("Spain");
        
        testTeam = new Team();
        testTeam.setName("FC Barcelona");
	testTeam.setCity("Barcelona");
	testTeam.setCountry("Spain");
    }
    

    // test create

    @Test
    public void testCreate() {

        playerService.createPlayer(testPlayer);
        verify(playerDao,times(1)).create(testPlayer);
        
    }

    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testCreateInvalid() {
        doThrow(new IllegalArgumentException()).when(playerDao).create(null);
        
        playerService.createPlayer(null);
       
    }

    // update
    
    @Test
    public void testUpdate(){
        playerService.updatePlayer(testPlayer);

        verify(playerDao, times(1)).update(testPlayer);
    }
    
    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testUpdateInvalid() {
        doThrow(new IllegalArgumentException()).when(playerDao).update(null);
        
        playerService.updatePlayer(null);
       
    }
    
    // delte player

    @Test
    public void testDelete() {
        System.out.println("delete a player");
        
        Player player = new Player();
        
	player.setId(1L);	
	player.setName("Lionel Messi");
        player.setPosition(Position.FORWARD);
        player.setDateOfBirth(new Date(System.currentTimeMillis()));
        player.setDressNumber(7);
        player.setCountry("Spain");

        when(playerDao.findById(player.getId())).thenReturn(player);

        playerService.deletePlayer(player);

        verify(playerDao, times(1)).delete(player);
        
    }
    
    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testDeleteInvalid() {
        doThrow(new IllegalArgumentException()).when(playerDao).delete(null);
      
        playerService.deletePlayer(null);
        
    }
    
    //test find by ID

    @Test
    public void testFindById() {
        Player player = new Player();

	player.setId(1L);	
	player.setName("Lionel Messi");
        player.setPosition(Position.FORWARD);
        player.setDateOfBirth(new Date(System.currentTimeMillis()));
        player.setDressNumber(7);
        player.setCountry("Spain");

        when(playerDao.findById(1l)).thenReturn(player);

        Player player2 = playerService.findById(player.getId()); 
        
        verify(playerDao, times(1)).findById(1L);

        assertEquals(player2.getId(), player.getId());
        
    }

    // find all
    
    @Test
    public void testfindAll() {
        System.out.println("findByAll");
        Player player = new Player();
	
	player.setId(2L);	
	player.setName("Cristiano Ronaldo");
        player.setPosition(Position.FORWARD);
        player.setDateOfBirth(new Date(System.currentTimeMillis()));
        player.setDressNumber(7);
        player.setCountry("Spain");

	Set<Player> playerList = new HashSet<>();
	playerList.add(player);
	playerList.add(testPlayer);
      
        when(playerDao.findAll()).thenReturn(playerList);
        
        assertEquals(playerService.findAll().size(), 2);
        verify(playerDao, times(1)).findAll();
             
    }    

    // find by team
    
    @Test
    public void testfindPlayersByTeam() {
	System.out.println("findPlayersByTeam");
        Player player = new Player();
	
	player.setId(2L);	
	player.setName("Cristiano Ronaldo");
        player.setPosition(Position.FORWARD);
        player.setDateOfBirth(new Date(System.currentTimeMillis()));
        player.setDressNumber(7);
        player.setCountry("Spain");

	Set<Player> playerList = new HashSet<>();
	playerList.add(player);
	playerList.add(testPlayer);
        
	testTeam.addPlayer(player);
	testTeam.addPlayer(testPlayer);
	
	when(playerDao.findPlayersByTeam(testTeam)).thenReturn(playerList);
	assertEquals(playerService.findPlayersByTeam(testTeam).size(), 2);
        verify(playerDao, times(1)).findPlayersByTeam(testTeam);
    }

  
  }
