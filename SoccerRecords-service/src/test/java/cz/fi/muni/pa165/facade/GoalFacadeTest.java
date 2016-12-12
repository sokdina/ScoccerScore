/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameCreateDTO;
import cz.fi.muni.pa165.dto.GoalCreateDTO;
import cz.fi.muni.pa165.dto.GoalDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.MatchResult;
import cz.fi.muni.pa165.enums.Position;
import cz.fi.muni.pa165.service.IGameService;
import cz.fi.muni.pa165.service.IPlayerService;
import cz.fi.muni.pa165.service.ITeamService;
import cz.fi.muni.pa165.service.config.PersistenceSampleApplicationContext;
import java.util.Date;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.AfterMethod;
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
    private ITeamService teamService;
    @Autowired
    private IGameService gameService;
    @Autowired
    private IPlayerService playerService;

    private GoalCreateDTO goalCreateDto;
    private Player player;
    private Game game;
    private Team teamOne;
    private Team teamTwo;

    @BeforeMethod
    public void setUpMethod() {
        teamOne = new Team();
        teamOne.setName("Real Madrid C.F.");
        teamOne.setCity("Madrid");
        teamOne.setCountry("Spain");
        teamService.create(teamOne);

        teamTwo = new Team();
        teamTwo.setName("FC Barcelona");
        teamTwo.setCity("Barcelona");
        teamTwo.setCountry("Spain");
        teamService.create(teamTwo);

        
        game = new Game();
        // please help to check	
        game.setHomeTeam(teamOne);
        game.setGuestTeam(teamTwo);
        game.setMatchResult(MatchResult.DRAW);
        game.setDateOfGame(new Date());
        game.setHomeScore(1);
        game.setGuestScore(1);
        gameService.create(game);
        
        
        
        player = new Player();
        player.setCountry("Argentina");
        player.setDateOfBirth(new Date(System.currentTimeMillis()));
        player.setDressNumber(5);
        player.setName("Messi");
        player.setPosition(Position.FORWARD);
        player.setTeam(teamOne);
        playerService.createPlayer(player);
        
        
        goalCreateDto = new GoalCreateDTO();
        goalCreateDto.setGame(game);
        goalCreateDto.setPlayer(player);
        goalCreateDto.setGoalTime(10);
        goalCreateDto.setDescription("GOOOL");
    }

    /**
     * Test of createTeam method, of class TeamFacadeImpl.
     */
    @Test(enabled = true)
    public void testCreateTeamAndFinbById() {
        goalFacade.createGoal(goalCreateDto);
        
        for(GoalDTO goal: goalFacade.findAll()){
            System.out.println("fuckfuck"+goal.toString());
        }
        //Long id = goalDto.getId();

    }

}
