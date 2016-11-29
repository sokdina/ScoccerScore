/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameCreateDTO;
import cz.fi.muni.pa165.dto.GoalDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.Position;
import cz.fi.muni.pa165.service.config.PersistenceSampleApplicationContext;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author peter
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class GoalFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private IGoalFacade goalFacade;

    @Autowired
    private IGameFacade gameFacade;

    @Autowired
    private IPlayerFacade playerFacade;

    @Autowired
    private ITeamFacade teamFacade;

    private GoalDTO goalDto;
    private Player playerDto;
    private GameCreateDTO gameDTO;
    private Team teamDTOOne;
    private Team teamDTOTwo;

    @BeforeMethod
    public void setUpMethod() {
        teamDTOOne = new Team();
//        teamDTOOne.setId(1L);
        teamDTOOne.setName("Real Madrid C.F.");
        teamDTOOne.setCity("Madrid");
        teamDTOOne.setCountry("Spain");

        teamDTOTwo = new Team();
//        teamDTOTwo.setId(2L);
        teamDTOTwo.setName("FC Barcelona");
        teamDTOTwo.setCity("Barcelona");
        teamDTOTwo.setCountry("Spain");

        //teamFacade.createTeam(teamDTOOne);
        //teamFacade.createTeam(teamDTOTwo);
        
        gameDTO = new GameCreateDTO();
        // please help to check
//        gameDTO.setId(1L);	
        //gameDTO.setHomeTeam(teamDTOOne);
        //gameDTO.setGuestTeam(teamDTOTwo);
        //gameDTO.setMatchResult(MatchResult.DRAW);
        gameDTO.setDateOfGame(new Date());
        gameDTO.setHomeScore(1);
        gameDTO.setGuestScore(1);
        
        //gameFacade.create(gameDTO);
        
        
        playerDto = new Player();
        playerDto.setCountry("Argentina");
        playerDto.setDateOfBirth(new Date(System.currentTimeMillis()));
        playerDto.setDressNumber(5);
        playerDto.setName("Messi");
        playerDto.setPosition(Position.FORWARD);
//        playerDto.setTeam(teamDTOOne);
//        playerDto.setId(5L);

        //playerFacade.createPlayer(playerDto);
        
        playerDto.setTeam(teamDTOOne);
        
        goalDto = new GoalDTO();
//        goalDto.setId(5L);
        goalDto.setGoalTime(new Date(System.currentTimeMillis()));
        goalDto.setDescription("GOOOL");
        //goalDto.setGame(gameDTO);
        goalDto.setPlayer(playerDto);
    }

    /**
     * Test of createTeam method, of class TeamFacadeImpl.
     */
    @Test(enabled = false)
    public void testCreateTeamAndFinbById() {
//        System.out.println("createTeam  -- " + goalDto.getPlayer().toString());   

        goalFacade.createGoal(goalDto);
        Long id = goalDto.getId();

    }

}
