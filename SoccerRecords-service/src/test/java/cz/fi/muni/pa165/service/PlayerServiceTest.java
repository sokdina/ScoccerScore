package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.IGoalDao;
import cz.fi.muni.pa165.dao.IPlayerDao;
import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.Position;
import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import java.util.Date;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;
import java.util.HashSet;

/**
 * 
 * @author Jaromir Sys
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class PlayerServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private IPlayerDao playerDao;
    
    @Mock
    private IGoalDao goalDao;
    
    @Mock
    private ITeamDao teamDao;
  
    @Autowired
    @InjectMocks
    private IPlayerService playerService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    private Player p1,p2;
    
    private Team t1,t2;
    
    private Goal g1;

    @BeforeMethod
    public void prepareTestPlayer() {
      
        
        p1 = new Player();
        p1.setId(1L);
        p1.setName("p123");
        p1.setCountry("p1country");
        p1.setPosition(Position.FORWARD);
        p1.setDateOfBirth(new Date());
        p1.setDressNumber(1);
        
        
        p2 = new Player();
        p2.setId(2L);
        p2.setName("p2");
        p2.setCountry("p2country");
        p2.setPosition(Position.FORWARD);
        p2.setDateOfBirth(new Date());
        p2.setDressNumber(2);
        
        t1 = new Team();
        t1.setId(3L);
        t1.setCity("whateverville");
        t1.setCountry("whateverstan");
        t1.setName("mandatoryuniqueteamname");
        t1.addPlayer(p1);
        
        t2 = new Team();
        t2.setId(4L);
        t2.setCity("whateverville2");
        t2.setCountry("whateverstan2");
        t2.setName("mandatoryuniqueteamname2");
        
        p1.setTeam(t1);
        p2.setTeam(t2);
        
        g1 = new Goal();
        g1.setDescription("asdsad");
        g1.setGoalTime(10);
        g1.setId(55L);
    }
    
    @Test
    public void testFindById() {
        p1.setId(1L);
        when(playerDao.findById(p1.getId())).thenReturn(p1);
        Player p = playerService.findById(p1.getId());
        verify(playerDao,times(1)).findById(p1.getId());
        assertTrue(p.equals(p1));
    }
    @Test
    public void testFindAll() {
        when(playerDao.findAll()).thenReturn(new HashSet<>());
        assertEquals(playerService.findAll().size(), 0);
    }
    
    @Test(enabled = true)
    public void testCreatePlayer() {
        playerService.createPlayer(p1);
        verify(playerDao,times(1)).create(p1);
    }
    
    @Test(enabled = true)
    public void testChangeTeam() {
        playerService.changeTeam(p1, t2);
        verify(playerDao,times(1)).update(p1);
    }
    
    @Test(enabled = true)
    public void testDeletePlayer() {
        playerService.deletePlayer(p1);
        
        verify(playerDao,times(1)).delete(p1);
    }
  
    @Test
    public void testFindPlayersByTeam() {
        when(playerDao.findPlayersByTeam(t1)).thenReturn(t1.getPlayers());
        Set<Player> players = playerService.findPlayersByTeam(t1);
        verify(playerDao,times(1)).findPlayersByTeam(t1);
        assertEquals(players.size(), 1);
    }
    
    @Test(enabled = true)
    public void testUpdatePlayer() {
        playerService.updatePlayer(p1);
        verify(playerDao,times(1)).update(p1);        
    }
    @Test
    public void testAddGoal(){
        playerService.addGoal(p1, g1);
        verify(playerDao,times(1)).update(p1);
        verify(goalDao,times(1)).update(g1); 
    }
    
    @Test (expectedExceptions = {SoccerRecordsDataAccessException.class}) //goal was not added
    public void testRemoveGoalNull(){
        playerService.removeGoal(p1, g1);
    }
    
    @Test(enabled=false)
    public void testRemoveGoal(){
        p1.addGoal(g1);
        playerService.removeGoal(p1, g1);
    }
    
}
