/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.IGameDao;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author peter
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GameServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private IGameDao gameDao;

    @Autowired
    @InjectMocks
    private IGameService gameService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void createGame() {
        Game game = new Game();
        game.setId(5L);
        
        gameService.create(game);
        
        verify(gameDao, times(1)).create(game);
}

}
