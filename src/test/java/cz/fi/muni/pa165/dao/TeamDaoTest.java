package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.TeamDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Team;

/**
 * TeamDaoTest class is used to test functionalities of
 * each team.
 * @author sokdina999@gamil.com
**/

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TeamDaoTest extends AbstractTestNGSpringContextTests {
    
	@Autowired
	private TeamDAO teamdao;
        
        private Team t1;
        private Team t2;
	
	@BeforeMethod
	public void createTests() {
		t1 = new Team();
		t2 = new Team();
                
                t1.setId(1);
		t1.setName("FC Barcelona");
		t1.setCity("Barcelona");
		t1.setCountry("Spain");
		
		t2.setId(2);
                t2.setName("Real Madrid");
		t2.setCity("Madrid");
		t2.setCountry("Spain");
                                	
		teamdao.create(t1);
		teamdao.create(t2);
      }
        @Test()
	public void create(){
		Team team = new Team();
                
                team.setId(3);
		team.setName("Attatico Madrid");
                team.setCity("Madrid");
                team.setCountry("Spain");      
		teamdao.create(team);
		
                Assert.assertEquals(teamdao.findById(team.getId()).getName(),"Attatico Madrid");
	}  
        
        @Test
	public void update(){
            Team team = new Team();
            team.setId(1);
            team.setName("Attatico Madrid");
            team.setCity("Madrid");
            team.setCountry("Spain Country"); 
                        
            teamdao.update(team);
            Assert.assertEquals(teamdao.findById(team.getId()).getCountry(), "Spain Country");
	}
    
        @Test
	public void delete() {
		Assert.assertNotNull(teamdao.findById(t1.getId()));
		teamdao.delete(t1);
		Assert.assertNull(teamdao.findById(t1.getId()));
	}
        
        @Test
	public void findById() {
		Assert.assertNotNull(teamdao.findById(1));
	}
        
	@Test
	public void findAll() {
		List<Team> team = teamdao.findByAll();
		Assert.assertEquals(team.size(), 2);
	}
        
        @Test
	public void findByName() {
		Assert.assertEquals(teamdao.findByName("a").size(), 2);
		Assert.assertEquals(teamdao.findByName("Real").size(), 1);
	}
        
       
}
