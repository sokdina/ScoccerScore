/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameCreateDTO;
import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.MatchResult;
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

/**
 *
 * @author sokdina999@gmail.com
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GameFacadeTest extends AbstractTestNGSpringContextTests{
      
    @Autowired
    private IGameFacade gameFacade;
       
    private Team teamDTOOne, teamDTOTwo;
    private GameCreateDTO gameDTO;
        
    @BeforeMethod
    public void setUpMethod(){
        teamDTOOne = new Team();
        teamDTOOne.setId(1L);
        teamDTOOne.setName("Real Madrid C.F.");
        teamDTOOne.setCity("Madrid");
        teamDTOOne.setCountry("Spain");
        
        teamDTOTwo = new Team();
        teamDTOTwo.setId(2L);
        teamDTOTwo.setName("FC Barcelona");
        teamDTOTwo.setCity("Barcelona");
        teamDTOTwo.setCountry("Spain");
        
        gameDTO = new GameCreateDTO();
        // please help to check
	gameDTO.setHomeTeam(teamDTOOne.getId());
        gameDTO.setGuestTeam(teamDTOTwo.getId());
        gameDTO.setDateOfGame(new Date());        
    }
    
    @AfterMethod
    public void cleanUpMethod(){
        
    }
    
    @Test
    public void testCreateGame() {
        System.out.println("create a game");   
               
        Long id = gameFacade.create(gameDTO);
        
        GameDTO g = gameFacade.findById(id);
        assertNotNull(g);
    }
    
    @Test
    public void testUpdateGame() {
        System.out.println("update Team");
        Long id = gameFacade.create(gameDTO);
        GameDTO g = gameFacade.findById(id);
        g.setHomeScore(2);
        g.setGuestScore(2);
       
        
        gameFacade.update(g);
        
       assertEquals(gameFacade.findById(id).getHomeScore(),0);

    }
    
    
    
    
    
   
     


     

   
    
    
    
}
