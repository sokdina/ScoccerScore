package www.muni.cz.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import www.muni.cz.PersistenceSampleApplicationContext;
import www.muni.cz.entity.Team;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TeamDaoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private TeamDAO teamdao;

	private Team t1 ;
	
	@BeforeMethod
	public void createUsers() {
		t1 = new Team();
		
		t1.setId(23);
		t1.setName("Dina");
		t1.setCity("PP");
		t1.setCountry("Cambodia");
		
		teamdao.create(t1);
	}

	@Test
	public void findById() {
		Assert.assertNotNull(teamdao.findById(23));
	}


}