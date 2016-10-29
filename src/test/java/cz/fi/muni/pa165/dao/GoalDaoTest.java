/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.Position;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;





/**
 *
 * @author Martin Kocak
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GoalDaoTest extends AbstractTestNGSpringContextTests{
    
    @Autowired 
    private IGoalDao goalDao;
    
    @Autowired
    private IPlayerDao playerDao;
    
    //uncomment after gameDao implementation
    //@Autowired
    //private IGameDao gameDao;
     
    private Goal g1;
    private Goal g2;
    
    @BeforeMethod
    public void initialization() {
            g1 = new Goal();
            g2 = new Goal();

          
            g1.setGoalTime(new Date());
            g1.setDescription("fantastic goal");
         
            g2.setGoalTime(new Date());         
            g2.setDescription("goal after corner");  
            goalDao.create(g2);
  }
    
    @Test
    public void createAndFintById(){
        goalDao.create(g1);
        Assert.assertEquals(goalDao.findById(g1.getId()), g1);
        
    }
    
    @Test(expectedExceptions ={ IllegalArgumentException.class, InvalidDataAccessApiUsageException.class})
    public void createWithNull(){
        goalDao.create(null);
       // Assert.assertEquals(goalDao.findById(g1.getId()), g1);
        
    }
  
    
    @Test
    public void testWithPlayer(){
        Player p = new Player();
        p.setName("Jose");
        p.setPosition(Position.DEFENDER);
        g2.setPlayer(p);
        
        Assert.assertEquals(goalDao.findById(g2.getId()).getPlayer(),g2.getPlayer());
    }
    
    @Test
    public void testWithGame(){
        Game game = new Game();
        game.setDateOfGame(new Date());
        game.setGuestScore(1);
        game.setHomeScore(2);
        
                
        g2.setGame(game);
        
        Game g = goalDao.findById(g2.getId()).getGame();
        Assert.assertEquals(goalDao.findById(g2.getId()).getGame(),g2.getGame());
        
    }
    
    @Test
    public void deleteGame(){
        
        Assert.assertNotNull(goalDao.findById(g2.getId()));
        goalDao.delete(g2);
        Assert.assertNull(goalDao.findById(g2.getId()));
    }
    
    @Test
    public void updateGoal(){
        Assert.assertNotNull(goalDao.findById(g2.getId()));
        g2.setDescription("amazing shot");
        goalDao.update(g2);
        Assert.assertEquals(goalDao.findById(g2.getId()).getDescription(),"amazing shot");
    }
    
    
    @Test
    public void findAll(){
        Set<Goal> goals = goalDao.findAll();
        Assert.assertEquals( goals.size() ,1);
    }
    
    
    @Test
    public void findById(){
        Assert.assertNotNull(goalDao.findById(g2.getId()));
    }
    
    @Test
    public void findByPlayer(){
        Player p = new Player();
        p.setName("Jose");
        p.setDressNumber(15);
        p.setDateOfBirth(new Date());
        p.setPosition(Position.DEFENDER);
        playerDao.create(p);
                 
        g1.setPlayer(p);
        g2.setPlayer(p);
              
        goalDao.create(g1);
        goalDao.update(g2);
       
        Set<Goal> goals = goalDao.findByPlayer(p);
        Assert.assertEquals( goals.size() ,2);
        
    }
    
    //Uncomment after GameDao implementation will be in 
    /*@Test
    public void findByGame(){
        Game g = new Game();
        g.setDateOfGame(new Date());
        g.setGuestScore(1);
        g.setHomeScore(2);
        
        g1.setGame(g);
        
        g2.setGame(g);
        
        gameDao.create(g);
        
        goalDao.update(g2);
        goalDao.create(g1);
        
        Set<Goal> games = goalDao.findByGame(g);
        Assert.assertEquals( games.size() ,2);
        
    }*/
    
}
