package cz.fi.muni.pa165.service;


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


@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ITeamDao teamDao;

    @Mock
    private IPlayerDao playerDao;
    
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

        Team t2 = teamService.findById(1L);

        verify(teamDao).findById(1L);

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
        List<Team> teamList = new ArrayList();
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
        List<Team> teamList = new ArrayList();
        teamList.add(team);
        teamList.add(testTeam);
        when(teamDao.findByName(testTeam.getName())).thenReturn(teamList);
        
        assertEquals(teamService.findByName(testTeam.getName()).get(0).getName(),team.getName());
        assertEquals(teamService.findByName(testTeam.getName()).get(1).getName(),testTeam.getName());
        
        //Dina you have wrong implementation here
        // If are two teams the same only by their name then don`t return Collection in findByName, 
        //if they not same, then assertNotEqual should return true, but it return false
        //so repair this a then remove this comment :)
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
    
    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testInvalidAddPlayer() {
        System.out.println("addInvalidPlayer");
                  
        teamService.addPlayer(testTeam, null);              
        
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
    
    /**
     * Test of deletePlayer method, of class TeamServiceImpl.
     */
    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testInvalidDeletePlayer() {
        System.out.println("deleteInvalidPlayer");
        
        
        teamService.deletePlayer(testTeam, null);
    }
    

}
