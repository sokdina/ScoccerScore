/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.enums.Position;
import cz.fi.muni.pa165.service.config.PersistenceSampleApplicationContext;
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

/**
 *
 * @author Martin Kocak
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TeamFacadeTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    private ITeamFacade teamFacade;

    @Autowired
    private IPlayerFacade playerFacade;
    
    private TeamDTO teamDTO;
    
    @BeforeMethod
    public void setUpMethod(){
        teamDTO = new TeamDTO();
        teamDTO.setName("TeamName");
    }


    /**
     * Test of createTeam method, of class TeamFacadeImpl.
     */
    @Test
    public void testCreateTeamAndFinbById() {
        System.out.println("createTeam");   
        
        Long id = teamFacade.createTeam(teamDTO);
        
        TeamDTO f = teamFacade.getTeamById(id);
        assertEquals(f, teamDTO);
        
    }

    /**
     * Test of updateTeam method, of class TeamFacadeImpl.
     */
    @Test
    public void testUpdateTeam() {
        System.out.println("updateTeam");
        
        TeamDTO nTeam = new TeamDTO();
        nTeam.setName("Chelsea");
        Long id = teamFacade.createTeam(nTeam);
        
        nTeam.setCity("London");
        
        teamFacade.updateTeam(teamDTO);
        
        assertEquals(teamFacade.getTeamById(id).getCity(), teamDTO.getCity());

    }

    /**
     * Test of deleteTeam method, of class TeamFacadeImpl.
     */
    @Test
    public void testDeleteTeam() {
        System.out.println("deleteTeam");
        TeamDTO nTeam = new TeamDTO();
        nTeam.setName("Arsenal");
        
        Long id =teamFacade.createTeam(nTeam);
        
        assertNotNull(teamFacade.getTeamById(id));
        
        teamFacade.deleteTeam(id);
        
        assertNull(teamFacade.getTeamById(id));
        
    } 


    /**
     * Test of getAllTeams method, of class TeamFacadeImpl.
     */
    @Test
    public void testGetAllTeams() {
        System.out.println("getAllTeams");
        
        TeamDTO nTeam1 = new TeamDTO();
        nTeam1.setName("Arsenal");
        
        TeamDTO nTeam2 = new TeamDTO();       
        nTeam2.setName("Chelsea");
        
        TeamDTO nTeam3 = new TeamDTO();       
        nTeam3.setName("Spurs");
        
        TeamDTO nTeam4 = new TeamDTO();
        nTeam4.setName("Lpool");
      
        teamFacade.createTeam(nTeam4);
        teamFacade.createTeam(nTeam3);
        teamFacade.createTeam(nTeam2);
        teamFacade.createTeam(nTeam1);
        
        assertEquals( teamFacade.getAllTeams().size(), 4);
    }

    /**
     * Test of findByName method, of class TeamFacadeImpl.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        TeamDTO nTeam1 = new TeamDTO();
        nTeam1.setName("Arsenal");
        
        teamFacade.createTeam(nTeam1);
        
        assertTrue(teamFacade.findByName(nTeam1.getName()).contains(nTeam1) );
    }

    /**
     * Test of addPlayer method, of class TeamFacadeImpl.
     */
    @Test
    public void testAddandRemovePlayer() {
        System.out.println("addPlayer");
        
        PlayerDTO p = new PlayerDTO();
        p.setCountry("England");
        p.setDateOfBirth(new Date());
        p.setDressNumber(28);
        p.setName("John Terry");
        p.setPosition(Position.DEFENDER);
        Long pId = playerFacade.createPlayer(p);
        
        TeamDTO nTeam2 = new TeamDTO();       
        nTeam2.setName("Chelsea");
        nTeam2.setCity("London");
        nTeam2.setCountry("England");
        Long tId = teamFacade.createTeam(nTeam2);
        
        
        teamFacade.addPlayer(tId, pId);
        
        assertEquals(playerFacade.findById(pId).getTeam().getName(),nTeam2.getName());
        
        //teamFacade.removePlayer(tId, pId);
        
        //(teamFacade.getTeamById(tId).getGoal().contains(p));
        
    }

    
    
}
