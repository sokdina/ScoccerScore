/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dao.IGoalDao;
import cz.fi.muni.pa165.dao.IPlayerDao;
import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.Position;
import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;
import cz.fi.muni.pa165.service.config.PersistenceSampleApplicationContext;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 *
 * @author Jaromir Sys
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PlayerFacadeTest extends AbstractTestNGSpringContextTests{
    

    @Autowired
    private IPlayerFacade playerFacade;
    @Autowired
    private IGoalDao goalDao;
    @Autowired 
    private ITeamDao teamDao;
    @Autowired
    private IPlayerDao playerDao;
    
    @Autowired
    private ITeamFacade teamFacade;
    
    private PlayerDTO p1;
    
    private Team t1,t2;
    private Goal g1;
    
    private Player p9;
    
    @BeforeMethod
    public void setUpMethod(){
        p1 = new PlayerDTO();
        p1.setName("p123");
        p1.setCountry("p1country");
        p1.setPosition(Position.FORWARD);
        p1.setDateOfBirth(new Date());
        p1.setDressNumber(1);
        
        t1 = new Team();
        t1.setCity("whateverville");
        t1.setCountry("whateverstan");
        t1.setName("asdasd");
        teamDao.create(t1);
        
        t2 = new Team();
        t2.setCity("whateverville");
        t2.setCountry("whateverstan");
        t2.setName("asdasd");
        teamDao.create(t2);
        
        g1 = new Goal();
        g1.setDescription("asdasd");
        g1.setGoalTime(10);
        goalDao.create(g1);
        
        p9 = new Player();
        p9.setName("p123");
        p9.setCountry("p1country");
        p9.setPosition(Position.FORWARD);
        p9.setDateOfBirth(new Date());
        p9.setDressNumber(1);
        
        playerDao.create(p9);
    }

    @Test(enabled = true)
    public void testFindById() {
        IPlayerFacade instance = playerFacade;
        Long id = p9.getId();
        
        PlayerDTO result = instance.findById(id);
        assertNotNull(result);
        assertEquals(result.getId(), id);
    }

    @Test(enabled = true)
    public void testFindAll() {
        System.out.println("findAll");
        IPlayerFacade instance = playerFacade;
        List result = instance.findAll();
        assertEquals(result.size(), 2);
    }

    @Test(enabled = true)
    public void testCreatePlayer() {
        IPlayerFacade instance = playerFacade;
        Long result = instance.createPlayer(p1);
        assertNotNull(result);
    }

    @Test(enabled = true)
    public void testChangeTeam() {
        IPlayerFacade instance = playerFacade;
        TeamDTO t1DTO = teamFacade.getTeamById(t1.getId());
        p1.setTeam(t1DTO);
        Long playerId = instance.createPlayer(p1);
        Long teamId = t2.getId();
        instance.changeTeam(playerId, teamId);
    }

    @Test(enabled = true)
    public void testDeletePlayer() {
        IPlayerFacade instance = playerFacade;
        Long playerId = p9.getId();
        assertEquals(instance.findAll().size(),2);
        instance.deletePlayer(playerId);
        assertEquals(instance.findAll().size(),1);
    }

    @Test(enabled = true)
    public void testFindPlayersByTeam() {
        Long teamId = t1.getId();
        IPlayerFacade instance = playerFacade;
        p9.setTeam(t1);
        playerDao.update(p9);
        List result = instance.findPlayersByTeam(teamId);
        assertEquals(result.size(), 1);
    }

    @Test(enabled = true)
    public void testAddGoal() {
        IPlayerFacade instance = playerFacade;
        Long playerId = instance.createPlayer(p1);
        Long goalId = g1.getId();
        instance.addGoal(playerId, goalId);
    }

    @Test(enabled = true,expectedExceptions = SoccerRecordsDataAccessException.class)
    public void testRemoveGoal() {
        IPlayerFacade instance = playerFacade;
        Long playerId = instance.createPlayer(p1);
        Long goalId = g1.getId();
        instance.removeGoal(playerId, goalId);
    }
}
