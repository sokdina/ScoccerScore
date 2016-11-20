package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dao.IGoalDao;
import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.dto.GoalDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.service.BeanMappingService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jaromir Sys
 */
public class GoalFacadeImpl implements IGoalFacade{

    @Autowired
    private IGoalDao goalDao;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public GoalDTO findById(Long id) {
        return beanMappingService.mapTo(goalDao.findById(id), GoalDTO.class);
    }

    @Override
    public List<GoalDTO> findAll() {
        List<GoalDTO> dtos = new ArrayList<>();
        goalDao.findAll().stream().forEach((g) -> {
            dtos.add(beanMappingService.mapTo(g, GoalDTO.class));
        });
        return Collections.unmodifiableList(dtos);
    }

    @Override
    public void createGoal(GoalDTO goal) {
        goalDao.create(beanMappingService.mapTo(goal, Goal.class));
    }

    @Override
    public void deleteGoal(GoalDTO goal) {
        goalDao.delete(beanMappingService.mapTo(goal, Goal.class));
    }

    @Override
    public void updateGoal(GoalDTO goal) {
        goalDao.update(beanMappingService.mapTo(goal, Goal.class));
    }

    @Override
    public List<GoalDTO> findByGoalTime(Date date) {
        List<GoalDTO> dtos = new ArrayList<>();
        goalDao.findAll().stream().filter(g->g.getGoalTime().equals(date)).forEach((g) -> {
            dtos.add(beanMappingService.mapTo(g, GoalDTO.class));
        });
        return Collections.unmodifiableList(dtos);
    }

    @Override
    public List<GoalDTO> findByGame(GameDTO game) {
        Game temp = beanMappingService.mapTo(game, Game.class);
        List<GoalDTO> dtos = new ArrayList<>();
        goalDao.findAll().stream().filter(g->g.getGame().equals(temp)).forEach((g) -> {
            dtos.add(beanMappingService.mapTo(g, GoalDTO.class));
        });
        return Collections.unmodifiableList(dtos);
    }

    @Override
    public List<GoalDTO> findByDescription(String description) {
        List<GoalDTO> dtos = new ArrayList<>();
        goalDao.findAll().stream().filter(g->g.getDescription().equals(description)).forEach((g) -> {
            dtos.add(beanMappingService.mapTo(g, GoalDTO.class));
        });
        return Collections.unmodifiableList(dtos);
    }

    @Override
    public List<GoalDTO> findByplayer(PlayerDTO player) {
        Player temp = beanMappingService.mapTo(player, Player.class);
        List<GoalDTO> dtos = new ArrayList<>();
        goalDao.findAll().stream().filter(g->g.getPlayer().equals(temp)).forEach((g) -> {
            dtos.add(beanMappingService.mapTo(g, GoalDTO.class));
        });
        return Collections.unmodifiableList(dtos);
    }
    
}
