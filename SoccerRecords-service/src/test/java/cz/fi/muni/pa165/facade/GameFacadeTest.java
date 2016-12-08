/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.MatchResult;
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
import cz.fi.muni.pa165.facade.IGameFacade;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.IGameService;
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
 * @author sokdina999@gmail.com
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GameFacadeTest extends AbstractTestNGSpringContextTests{

    @Spy
    @Inject
    protected BeanMappingService beanMappingService;

    @Mock
    private IGameService gameService;

    @Autowired
    @InjectMocks
    private IGameFacade gameFacade;

    private Game game;
    private GameDTO gameDTO;

    private Team teamOne, teamTwo;
    private TeamDTO teamDTOOne, teamDTOTwo;


    @BeforeClass
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        gameFacade = (IGameFacade) unwrapProxy(gameFacade);
        ReflectionTestUtils.setField(gameFacade, "gameService", gameService);
        ReflectionTestUtils.setField(gameFacade, "beanMappingService", beanMappingService);

        teamOne = new Team();
        teamOne.setId(1L);
        teamOne.setName("Real Madrid C.F.");
        teamOne.setCity("Madrid");
        teamOne.setCountry("Spain");

        teamTwo = new Team();
        teamTwo.setId(2L);
        teamTwo.setName("FC Barcelona");
        teamTwo.setCity("Barcelona");
        teamTwo.setCountry("Spain"); 

	game = new Game(); 
	game.setId(1L);	
	game.setHomeTeam(teamOne);
        game.setGuestTeam(teamTwo);
        game.setDateOfGame(new Date());
        game.setMatchResult(MatchResult.DRAW);
        game.setHomeScore(1);
        game.setGuestScore(1);   
        
        teamDTOOne = new TeamDTO();
        teamDTOOne.setId(1L);
        teamDTOOne.setName("Real Madrid C.F.");
        teamDTOOne.setCity("Madrid");
        teamDTOOne.setCountry("Spain");

        teamDTOTwo = new TeamDTO();
        teamDTOTwo.setId(2L);
        teamDTOTwo.setName("FC Barcelona");
        teamDTOTwo.setCity("Barcelona");
        teamDTOTwo.setCountry("Spain");   
	
	gameDTO = new GameDTO(); 
	gameDTO.setId(1L);	
	gameDTO.setHomeTeam(teamDTOOne);
        gameDTO.setGuestTeam(teamDTOTwo);
        gameDTO.setDateOfGame(new Date());
        gameDTO.setMatchResult(MatchResult.DRAW);
        gameDTO.setHomeScore(1);
        gameDTO.setGuestScore(1); 

            
    }

    @Test
    public void testCreateGame() {
        gameFacade.create(gameDTO);
        
        verify(gameService, times(1)).create(game);
        verify(beanMappingService, times(1)).mapTo(gameDTO, Game.class);
    }


    @Test
    public void testdeleteGame() {
        
        

	Game gameTest = new Game(); 
	gameTest.setId(3L);	
	gameTest.setHomeTeam(teamOne);
        gameTest.setGuestTeam(teamTwo);
        gameTest.setDateOfGame(new Date());
        gameTest.setMatchResult(MatchResult.DRAW);
        gameTest.setHomeScore(3);
        gameTest.setGuestScore(3);   
        
       	GameDTO gameDTOTest = new GameDTO(); 
	gameDTOTest.setId(3L);	
	gameDTOTest.setHomeTeam(teamDTOOne);
        gameDTOTest.setGuestTeam(teamDTOTwo);
        gameDTOTest.setDateOfGame(new Date());
        gameDTOTest.setMatchResult(MatchResult.DRAW);
        gameDTOTest.setHomeScore(3);
        gameDTOTest.setGuestScore(3);  
        
        gameFacade.delete(gameDTOTest);

        verify(gameService).delete(gameTest);
        verify(beanMappingService).mapTo(gameDTOTest, Game.class);
    }

    @Test
    public void testupdateGame() {     
        
	Game gameTest = new Game(); 
	gameTest.setId(2L);	
	gameTest.setHomeTeam(teamOne);
        gameTest.setGuestTeam(teamTwo);
        gameTest.setDateOfGame(new Date());
        gameTest.setMatchResult(MatchResult.DRAW);
        gameTest.setHomeScore(5);
        gameTest.setGuestScore(5);   
        
       	GameDTO gameDTOTest = new GameDTO(); 
	gameDTOTest.setId(2L);	
	gameDTOTest.setHomeTeam(teamDTOOne);
        gameDTOTest.setGuestTeam(teamDTOTwo);
        gameDTOTest.setDateOfGame(new Date());
        gameDTOTest.setMatchResult(MatchResult.DRAW);
        gameDTOTest.setHomeScore(5);
        gameDTOTest.setGuestScore(5); 
        gameFacade.update(gameDTOTest);
        
        assertEquals(gameTest.getId(), gameDTOTest.getId());         
        verify(gameService).update(gameTest);
        verify(beanMappingService).mapTo(gameDTOTest, Game.class);
    }

    @Test
    public void testfindGameById() {
        when(gameService.findById(1L)).thenReturn(game);

        GameDTO gameDTOTest = gameFacade.findById(1L);

        assertNotNull(gameDTOTest);
        verify(gameService).findById(1L);
        verify(beanMappingService).mapTo(game, GameDTO.class);
    }
    
    @Test
    public void testgetAllGame() {
        when(gameService.findAll()).thenReturn(Collections.singletonList(game));

        List<GameDTO> gameDTOs = gameFacade.findAll();

        //assertEquals(team.getName(), teamDTOs.get(0).getName());
        assertNotNull(gameDTOs);
        verify(gameService).findAll();
        verify(beanMappingService).mapTo(Collections.singletonList(game), GameDTO.class);
    }

    
    // Mapping Bean
    public static final Object unwrapProxy(Object bean) throws Exception {
    
        if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
            Advised advised = (Advised) bean;
            bean = advised.getTargetSource().getTarget();
        }
        return bean;
    }
    
}

   
