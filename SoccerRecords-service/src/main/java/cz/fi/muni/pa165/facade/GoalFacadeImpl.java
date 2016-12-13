package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.dto.GoalCreateDTO;
import cz.fi.muni.pa165.dto.GoalDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.IGoalService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jaromir Sys
 */
@Transactional
@Service
public class GoalFacadeImpl implements IGoalFacade{

    @Autowired
    private IGoalService goalService;
    
    @Autowired
    private IPlayerFacade playerFacade;
    
    @Autowired
    private IGameFacade gameFacade;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public GoalDTO findById(Long id) {
        return beanMappingService.mapTo(goalService.findById(id), GoalDTO.class);
    }

    @Override
    public List<GoalDTO> findAll() {
        List<GoalDTO> dtos = new ArrayList<>();
        goalService.findAll().stream().forEach((g) -> {
            dtos.add(beanMappingService.mapTo(g, GoalDTO.class));
        });
        return Collections.unmodifiableList(dtos);
    }

    @Override
    public Long createGoal(GoalCreateDTO goal) {
        if(goal.getGame() == null && goal.getGameId() != null){
            goal.setGame(gameFacade.findById(goal.getGameId()));
        }
        if(goal.getPlayer() == null && goal.getPlayerId() != null){
            goal.setPlayer(playerFacade.findById(goal.getPlayerId()));
        }
        
        return goalService.createGoal(beanMappingService.mapTo(goal, Goal.class));
    }

    @Override
    public void deleteGoal(GoalDTO goal) {
        goalService.deleteGoal(beanMappingService.mapTo(goal, Goal.class));
    }

    @Override
    public void updateGoal(GoalDTO goal) {
        goalService.updateGoal(beanMappingService.mapTo(goal, Goal.class));
    }

    @Override
    public List<GoalDTO> findByGoalTime(Integer date) {
        List<GoalDTO> dtos = new ArrayList<>();
        goalService.findByGoalTime(date).stream().forEach((g) -> {
            dtos.add(beanMappingService.mapTo(g, GoalDTO.class));
        });
        return Collections.unmodifiableList(dtos);
    }

    @Override
    public List<GoalDTO> findByGame(GameDTO game) {
        Game temp = beanMappingService.mapTo(game, Game.class);
        List<GoalDTO> dtos = new ArrayList<>();
        goalService.findByGame(temp).stream().forEach((g) -> {
            dtos.add(beanMappingService.mapTo(g, GoalDTO.class));
        });
        return Collections.unmodifiableList(dtos);
    }

    @Override
    public List<GoalDTO> findByDescription(String description) {
        List<GoalDTO> dtos = new ArrayList<>();
        goalService.findByDescription(description).stream().forEach((g) -> {
            dtos.add(beanMappingService.mapTo(g, GoalDTO.class));
        });
        return Collections.unmodifiableList(dtos);
    }

    @Override
    public List<GoalDTO> findByplayer(PlayerDTO player) {
        Player temp = beanMappingService.mapTo(player, Player.class);
        List<GoalDTO> dtos = new ArrayList<>();
        goalService.findByplayer(temp).stream().forEach((g) -> {
            dtos.add(beanMappingService.mapTo(g, GoalDTO.class));
        });
        return Collections.unmodifiableList(dtos);
    }
    
}
