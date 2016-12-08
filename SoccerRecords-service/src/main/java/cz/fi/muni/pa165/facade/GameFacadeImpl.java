/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.IGameService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author peter
 */

@Service
@Transactional
public class GameFacadeImpl implements IGameFacade{


    @Inject
    private IGameService gameService;

    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public void create(GameDTO g) {
         gameService.create(beanMappingService.mapTo(g, Game.class));
    }

    @Override
    public void update(GameDTO g) {
      gameService.update(beanMappingService.mapTo(g, Game.class));
    }

    @Override
    public void delete(GameDTO g) throws IllegalArgumentException {
	gameService.delete(beanMappingService.mapTo(g, Game.class));
    }

    @Override
    public List<GameDTO> findGamesBetweenTeams(long teamId1, long teamId2) {
        List<GameDTO> games = new ArrayList<>();
        gameService.findGamesBetweenTeams(teamId1, teamId2).stream().forEach((g) ->{
            games.add(beanMappingService.mapTo(g, GameDTO.class));
        });
        return Collections.unmodifiableList(games);
    }

    @Override
    public GameDTO findById(long id) {
        return beanMappingService.mapTo(gameService.findById(id), GameDTO.class);
    }

    @Override
    public List<GameDTO> findAll() {
        return beanMappingService.mapTo(gameService.findAll(),GameDTO.class);
    }

    
}
