package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jaromir Sys
 */
public interface IGoalService {
    public Goal findById(Long id);
    public Set<Goal> findAll();
    public void createGoal(Goal goal);
    public void deleteGoal(Goal goal);
    public void updateGoal(Goal goal);
    
    public Set<Goal> findByGoalTime(Date date);
    
    public Set<Goal> findByGame(Game game);
    
    public Set<Goal> findByDescription(String description);
    
    public Set<Goal> findByplayer(Player player);
}
