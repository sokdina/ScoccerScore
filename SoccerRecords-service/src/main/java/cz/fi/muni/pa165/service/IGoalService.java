package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import java.util.Collection;

/**
 *
 * @author Jaromir Sys
 */
public interface IGoalService {
    public Goal findById(Long id);
    public Collection<Goal> findAll();
    public Long createGoal(Goal goal);
    public void deleteGoal(Goal goal);
    public void updateGoal(Goal goal);
    
    public Collection<Goal> findByGoalTime(Integer date);
    
    public Collection<Goal> findByGame(Game game);
    
    public Collection<Goal> findByDescription(String description);
    
    public Collection<Goal> findByplayer(Player player);
}
