/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.IGameDao;
import cz.fi.muni.pa165.dao.IGoalDao;
import cz.fi.muni.pa165.dao.IPlayerDao;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.enums.MatchResult;
import cz.fi.muni.pa165.enums.Position;
import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

/**
 *
 * @author peter
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GoalServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private IGoalDao goalDao;

    @Mock
    private IPlayerDao playerDao;

    @Mock
    private IGameDao gameDao;

    @Autowired
    @InjectMocks
    private IGoalService goalService;

    private Player player;
    private Game game;
    private Goal goal;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void prepareTestTeam() {
        player = new Player();
        player.setName("Ronaldo");
        player.setPosition(Position.FORWARD);
        player.setDateOfBirth(new Date(System.currentTimeMillis()));
        player.setDressNumber(7);
        player.setCountry("Portugal");

        game = new Game();
        game.setDateOfGame(new Date());
        game.setMatchResult(MatchResult.DRAW);
        game.setHomeScore(0);
        game.setGuestScore(0);
        
        goal = new Goal();
        goal.setId(5L);
        goal.setPlayer(player);
        goal.setGame(game);
    }

    @Test
    public void createGoal() {
        goalService.createGoal(goal);

        verify(goalDao, times(1)).create(goal);
    }
    
    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testInvalidCreate() {
        doThrow(new IllegalArgumentException()).when(goalDao).create(null);
        goalService.createGoal(null);
    }
    
    @Test
    public void testDelete(){
        goalService.deleteGoal(goal);

        verify(goalDao, times(1)).delete(goal);
    }
    
    @Test
    public void testUpdate(){
        goalService.updateGoal(goal);

        verify(goalDao, times(1)).update(goal);
    }
    
    @Test(expectedExceptions ={ SoccerRecordsDataAccessException.class,})
    public void testInvalidDelete() {
        doThrow(new IllegalArgumentException()).when(goalDao).delete(null);
        goalService.deleteGoal(null);
    }
    
    
    @Test
    public void testFindById() {
        when(goalDao.findById(goal.getId())).thenReturn(goal);

        Goal g = goalService.findById(goal.getId()); 
        
        verify(goalDao, times(1)).findById(goal.getId());
        assertTrue(goal.equals(g));
    }
    
    
    @Test
    public void testFindAll() {
        Player player2 = new Player();
        player.setName("Messi");
        player.setPosition(Position.FORWARD);
        player.setDateOfBirth(new Date(System.currentTimeMillis()));
        player.setDressNumber(10);
        player.setCountry("Argentina");
        
        Goal goal2 = new Goal();
        goal2.setId(6L);
        goal2.setPlayer(player2);
        goal2.setGame(game);
        
        
        Set<Goal> goals = new HashSet<>();
        goals.add(goal);
        goals.add(goal2);
        
        when(goalDao.findAll()).thenReturn(goals);
        assertEquals(goalService.findAll().size(), 2);
        verify(goalDao, times(1)).findAll();
    }
    
    @Test
    public void testFindByGoalTime(){
        Integer goalTime = 15;
        goal.setGoalTime(goalTime);
        
        Set<Goal> goals = new HashSet<>();
        goals.add(goal);
        
        when(goalDao.findAll()).thenReturn(goals);
        
        assertEquals(goalService.findByGoalTime(goalTime).size(), 1);
        verify(goalDao, times(3)).findAll();
    }
    
    
    @Test
    public void testFindByGame(){
        Set<Goal> goals = new HashSet<>();
        goals.add(goal);
        
        when(goalDao.findByGame(game)).thenReturn(goals);
        
        assertEquals(goalService.findByGame(game).size(), 1);
        verify(goalDao, times(1)).findByGame(game);
    }
    
    
    @Test
    public void testFindByDescription(){
        String description = "description";
        goal.setDescription(description);
        Set<Goal> goals = new HashSet<>();
        goals.add(goal);
        
        when(goalDao.findAll()).thenReturn(goals);
        
        assertEquals(goalService.findByDescription(description).size(), 1);
        verify(goalDao, times(2)).findAll();
    }
    
    @Test
    public void testInvalidFindByDescription(){
        String description = "description";
        goal.setDescription(description);
        Set<Goal> goals = new HashSet<>();
        goals.add(goal);
        
        when(goalDao.findAll()).thenReturn(goals);
        
        assertEquals(goalService.findByDescription("asda").size(), 0);
        verify(goalDao, times(4)).findAll();
    }

    @Test
    public void testFindByPlayer(){
        Set<Goal> goals = new HashSet<>();
        goals.add(goal);
        
        when(goalDao.findByPlayer(player)).thenReturn(goals);
        
        assertEquals(goalService.findByplayer(player).size(), 1);
        verify(goalDao, times(1)).findByPlayer(player);
    }
    
}
