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
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.IGameService;
import cz.fi.muni.pa165.service.ITeamService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.util.Pair;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
/**
 *
 * @author peter
 */

@Service
@Transactional
public class GameFacadeImpl implements IGameFacade{


    @Inject
    private IGameService gameService;

    @Inject
    private ITeamService teamService;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public Long create(GameCreateDTO g) {
        GameDTO gdto = new GameDTO();
        gdto.setDateOfGame(g.getDateOfGame());
        gdto.setHomeTeam(beanMappingService.mapTo(teamService.findById(g.getHomeTeam()), TeamDTO.class));
        gdto.setGuestTeam(beanMappingService.mapTo(teamService.findById(g.getGuestTeam()), TeamDTO.class));
        
        Game game = beanMappingService.mapTo(gdto, Game.class);
	Long newId = gameService.create(game).getId();
        return newId;
    }

    @Override
    public GameDTO update(GameDTO g) {
        Game game = beanMappingService.mapTo(g, Game.class);
	return beanMappingService.mapTo(gameService.update(game), GameDTO.class);
    }

    @Override
    public void delete(GameDTO g) throws IllegalArgumentException {
        Game game = beanMappingService.mapTo(g, Game.class);
	gameService.delete(game);
    }

    @Override
    public List<GameDTO> findGamesBetweenTeams(long teamId1, long teamId2) {
        List<GameDTO> games = new ArrayList<>();
        gameService.findGamesBetweenTeams(teamId1, teamId2).stream().forEach((g) ->{
            games.add(beanMappingService.mapTo(calculateScore(g), GameDTO.class));
        });
        return Collections.unmodifiableList(games);
    }

    @Override
    public GameDTO findById(long id) {
        return beanMappingService.mapTo(calculateScore(gameService.findById(id)), GameDTO.class);
    }

    @Override
    public List<GameDTO> findAll() {
        List<GameDTO> games = new ArrayList<>();
        gameService.findAll().stream().forEach((g) ->{
            games.add(beanMappingService.mapTo(calculateScore(g), GameDTO.class));
        });
        return Collections.unmodifiableList(games);
    }
    
    private Game calculateScore(Game game){
        int homeScore = 0;
        int guestScore = 0;
        
        List<Goal> goals = new ArrayList(game.getGoals());
        
        for(Goal goal : goals){
            if(goal.getPlayer().getTeam().equals(game.getHomeTeam())){
                homeScore++;
            }else{
                guestScore++;
            }
        }
        
        game.setHomeScore(homeScore);
        game.setGuestScore(guestScore);
        
        return game;
    }

    @Override
    public List<List<GameDTO>> generateSeasonMatches() {
        List<List<Pair>> seasonMatches = gameService.generateSeasonMatches();
        List<List<GameDTO>> seasonMatchesDTO = new ArrayList<>();
        
        for(List<Pair> rounds : seasonMatches){
            List<GameDTO> roundsDTO = new ArrayList<>();
            for(Pair match : rounds){
                GameDTO game = new GameDTO();
                game.setHomeTeam(beanMappingService.mapTo((Team)match.getKey(),TeamDTO.class));
                game.setGuestTeam(beanMappingService.mapTo((Team)match.getValue(),TeamDTO.class));
                roundsDTO.add(game);
            }
            seasonMatchesDTO.add(roundsDTO);
        }
        
        return seasonMatchesDTO;
    }
    
    
    
    
}
