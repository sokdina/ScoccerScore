package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.IGoalDao;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jaromir Sys
 */

public class GoalServiceImpl implements IGoalService{

    @Autowired
    private IGoalDao goalDao;
    
    @Override
    public Goal findById(Long id) {
        try{
            return goalDao.findById(id);
        }
        catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public Set<Goal> findAll() {
        try{
            return goalDao.findAll();
        }
        catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public void createGoal(Goal goal) {
        try{
            goalDao.create(goal);
        }
        catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public void deleteGoal(Goal goal) {
        try{
            goalDao.delete(goal);
        }
        catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public void updateGoal(Goal goal) {
        try{
            goalDao.update(goal);
        }
        catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public Set<Goal> findByGoalTime(Date date) {
        try{
            return goalDao.findAll().stream().filter(g->g.getGoalTime().equals(date)).collect(Collectors.toSet());
        }catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public Set<Goal> findByGame(Game game) {
        try{
            return goalDao.findByGame(game);
        }catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public Set<Goal> findByDescription(String description) {
        try{
            return goalDao.findAll().stream().filter(g->g.getDescription().equals(description)).collect(Collectors.toSet());
        }catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public Set<Goal> findByplayer(Player player) {
        try{
            return goalDao.findByPlayer(player);
        }catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }
    
}
