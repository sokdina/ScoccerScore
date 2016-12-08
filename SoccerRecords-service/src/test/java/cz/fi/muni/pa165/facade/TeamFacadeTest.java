/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.Position;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import cz.fi.muni.pa165.facade.IGameFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.ITeamService;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import java.util.Collections;
import javax.inject.Inject;
import org.aspectj.lang.annotation.Before;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.util.ReflectionTestUtils;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 *
 * @author Martin Kocak
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TeamFacadeTest extends AbstractTestNGSpringContextTests{

    @Spy
    @Inject
    protected BeanMappingService beanMappingService;

    @Mock
    private ITeamService teamService;

    @Autowired
    @InjectMocks
    private ITeamFacade teamFacade;

    private Team team;

    private TeamDTO teamDTO;

    private Player player;

    private PlayerDTO playerDTO;

   
    private Long id = 1L;
    private String name = "FC Barcelona";
    private String city = "Barcelona";
    private String country ="Spain";


    @BeforeClass
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        teamFacade = (ITeamFacade) unwrapProxy(teamFacade);
        ReflectionTestUtils.setField(teamFacade, "teamService", teamService);
        ReflectionTestUtils.setField(teamFacade, "beanMappingService", beanMappingService);

        team = new Team();
        team.setId(id);
        team.setName(name);
        team.setCity(city);
        team.setCountry(country);

        teamDTO = new TeamDTO();
        teamDTO.setId(id);
        teamDTO.setName(name);
        teamDTO.setCity(city);
        teamDTO.setCountry(country);
        
        player = new Player();	
        player.setId(1L);
	player.setName("Ronaldo");
        player.setPosition(Position.FORWARD);
        player.setDateOfBirth(new Date());
        player.setDressNumber(7);
        player.setCountry("Portugal");

	playerDTO = new PlayerDTO();
        playerDTO.setId(1L);
	playerDTO.setName("Ronaldo");
        playerDTO.setPosition(Position.FORWARD);
        playerDTO.setDateOfBirth(new Date());
        playerDTO.setDressNumber(7);
        playerDTO.setCountry("Portugal");

    }

    @Test
    public void testCreateTeam() {
        teamFacade.createTeam(teamDTO);

        verify(teamService).create(team);
        verify(beanMappingService).mapTo(teamDTO, Team.class);
    }
    
    @Test
    public void testupdateTeam() {
              
        TeamDTO teamDTO1 = new TeamDTO();
        teamDTO1.setId(2L);
        teamDTO1.setName("Real Madrid C.F.");
        teamDTO1.setCity("Madrid");
        teamDTO1.setCountry("Spain");
               
        teamFacade.updateTeam(teamDTO1);
        
        Team team1 = new Team();
        team1.setId(2L);
        team1.setName("Real Madrid C.F.");
        team1.setCity("Madrid");
        team1.setCountry("Spain");
       
        verify(teamService).update(team1);
        verify(beanMappingService).mapTo(teamDTO1, Team.class);
    }
    
    @Test
    public void testdeleteTeam() {
        teamFacade.deleteTeam(id);

        verify(teamService).delete(id);
        verify(beanMappingService).mapTo(teamDTO, Team.class);
    }
    
    
    @Test
    public void testgetTeamById() {
        when(teamService.findById(id)).thenReturn(team);

        TeamDTO teamDTO = teamFacade.getTeamById(id);

        assertNotNull(teamDTO);
        assertEquals(team.getName(), teamDTO.getName());
        verify(teamService).findById(id);
        verify(beanMappingService).mapTo(team, TeamDTO.class);
    }

    @Test
    public void testgetAllTeams() {
        when(teamService.findByAll()).thenReturn(Collections.singletonList(team));

        List<TeamDTO> teamDTOs = teamFacade.getAllTeams();

        assertEquals(team.getName(), teamDTOs.get(0).getName());
        verify(teamService).findByAll();
        verify(beanMappingService).mapTo(Collections.singletonList(team), TeamDTO.class);
    }
    
    
    public void testgetAllTeamsWithNull() {
        
 	when(teamService.findByAll()).thenReturn(null);
        List<TeamDTO> teamDTOs = teamFacade.getAllTeams();

        assertNull(teamDTOs);
        verify(teamService).findByAll();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    
    @Test
    public void testaddPlayer() {   
        
        TeamDTO teamDTO1 = new TeamDTO();
        teamDTO1.setId(3L);
        teamDTO1.setName("name3");
        teamDTO1.setCity("city");
        teamDTO1.setCountry("country");
        
        Team team1 = new Team();
        team1.setId(3L);
        team1.setName("name3");
        team1.setCity("city");
        team1.setCountry("country");
        
       
        
	teamFacade.addPlayerFacade(teamDTO1, playerDTO);

        verify(teamService).addPlayer(team1, player);
       
        verify(beanMappingService).mapTo(teamDTO1, Team.class);
        verify(beanMappingService).mapTo(playerDTO, Player.class);
    }


    @Test
    public void testDeletePlayer() {   
        
        TeamDTO teamDTOTest = new TeamDTO();
        teamDTOTest.setId(1L);
        teamDTOTest.setName("Arsenal");
        teamDTOTest.setCity("England");
        teamDTOTest.setCountry("England");
        
        Team teamTest = new Team();
        teamTest.setId(1L);
        teamTest.setName("Arsenal");
        teamTest.setCity("England");
        teamTest.setCountry("England");
        
        Player playerTest = new Player();	
        playerTest.setId(1L);
	playerTest.setName("Theo Walcott");
        playerTest.setPosition(Position.FORWARD);
        playerTest.setDateOfBirth(new Date());
        playerTest.setDressNumber(5);
        playerTest.setCountry("England");

	PlayerDTO playerDTOTest = new PlayerDTO();
        playerDTOTest.setId(1L);
	playerDTOTest.setName("Theo Walcott");
        playerDTOTest.setPosition(Position.FORWARD);
        playerDTOTest.setDateOfBirth(new Date());
        playerDTOTest.setDressNumber(5);
        playerDTOTest.setCountry("England");
                
	teamFacade.removePlayer(teamDTOTest, playerDTOTest);

        verify(teamService).deletePlayer(teamTest, playerTest);
       
        verify(beanMappingService).mapTo(teamDTOTest, Team.class);
        verify(beanMappingService).mapTo(playerDTOTest, Player.class);
    }
   

    public static final Object unwrapProxy(Object bean) throws Exception {
    
        if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
            Advised advised = (Advised) bean;
            bean = advised.getTargetSource().getTarget();
        }
        return bean;
    }
    
}

   
