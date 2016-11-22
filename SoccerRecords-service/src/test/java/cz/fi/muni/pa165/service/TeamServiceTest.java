package cz.fi.muni.pa165.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.service.TeamService;

import cz.fi.muni.pa165.service.config.ServiceConfiguration;

@ContextConfiguration(classes=ServiceConfiguration.class)
public class TeamServiceTest extends AbstractTransactionalTestNGSpringContextTests
{
    @Mock
    private ITeamDao teamDao;
    
    @Autowired
    @InjectMocks
    private TeamService teamService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Team testTeam;

    @BeforeMethod
    public void prepareTestTeam(){
    	testTeam = new Team();
        testTeam.setName("Real Madrid C.F.");
	testTeam.setCity("Madrid");
	testTeam.setCountry("Spain");
    }
    
       
   
}
