package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jaromir Sys
 */
public interface IGoalService {
    public Goal findById(Long id);
    public Set<Goal> findAll();
    public Long createGoal(Goal goal);
    public void deleteGoal(Goal goal);
    public void updateGoal(Goal goal);
    
    public Set<Goal> findByGoalTime(Integer date);
    
    public Set<Goal> findByGame(Game game);
    
    public Set<Goal> findByDescription(String description);
    
    public Set<Goal> findByplayer(Player player);
}
