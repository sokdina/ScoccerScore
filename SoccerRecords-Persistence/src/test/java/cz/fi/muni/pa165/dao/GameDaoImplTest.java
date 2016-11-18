package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.IGameDao;
import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.MatchResult;
import java.util.Date;
import java.util.HashSet;
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
import cz.fi.muni.pa165.dao.ITeamDao;

/**
 *
 * @author Jaromir Sys
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GameDaoImplTest extends AbstractTestNGSpringContextTests {
    
	@Autowired
	private IGameDao gameDao;
        @Autowired
        private ITeamDao teamDao;
        
        private Team t1,t2,t3;
	
	@BeforeMethod
	public void initiliaze() {                
            t1 = new Team();
            t1.setName("whatever1");
            
            t2 = new Team();
            t2.setName("whatever2");
            
            t3 = new Team();
            t3.setName("whatever3");
            
            
            teamDao.create(t1);
            teamDao.create(t2);
            teamDao.create(t3);
        }
        @Test()
	public void testCreate(){
	    Game g = new Game();
            g.setHomeTeam(t1);
            g.setGuestTeam(t2);
            g.setDateOfGame(new Date());
            g.setMatchResult(MatchResult.DRAW);
            g.setHomeScore(0);
            g.setGuestScore(0);
            
            gameDao.create(g);
            
            Assert.assertNotNull(g.getId());
	}  
        
        @Test
	public void testUpdate(){
            Game g1 = new Game();
            gameDao.create(g1);
            g1.setMatchResult(MatchResult.DRAW);
            gameDao.update(g1);
            
            Game g2 = gameDao.findById(g1.getId());
            Assert.assertEquals(g2.getMatchResult(), MatchResult.DRAW);
	}
    
        @Test
	public void testDelete() {
            Game g = new Game();
            gameDao.create(g);
            Assert.assertNotNull(g.getId());
            gameDao.delete(g);
            Assert.assertNull(gameDao.findById(g.getId()));
	}
        
        @Test
	public void testFindById() {
            Game g = new Game();
            gameDao.create(g);
            Assert.assertNotNull(g.getId());
	}
        
	@Test
	public void testFindAll() {
            Game g1 = new Game();
            Game g2 = new Game();
            gameDao.create(g1);
            gameDao.create(g2);
            
            Assert.assertEquals(gameDao.findAll().size(), 2);
            
	}
        
        @Test
        public void testFindGamesBetweenTeams() {
            Game g1 = new Game();
            g1.setHomeTeam(t1);
            g1.setGuestTeam(t2);
            g1.setGameResult(0, 0);
            Game g2 = new Game();
            g2.setHomeTeam(t2);
            g2.setGuestTeam(t1);
            g2.setGameResult(4, 0);
            gameDao.create(g1);
            gameDao.create(g2);
            Assert.assertEquals(gameDao.findGamesBetweenTeams(t1.getId(), t2.getId()).size(), 2);
            Game g3 = new Game();
            g3.setHomeTeam(t1);
            g3.setGuestTeam(t3);
            gameDao.create(g3);
            Assert.assertEquals(gameDao.findGamesBetweenTeams(t1.getId(), t2.getId()).size(), 2);
        }
        
        @Test(expectedExceptions = NullPointerException.class)
        public void testUpdateDetachedEntity(){
            Game g = new Game();
            gameDao.update(g);
            
            Game gg = gameDao.findById(g.getId());
        }
        
       
}
