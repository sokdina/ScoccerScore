package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.dto.GoalCreateDTO;
import cz.fi.muni.pa165.dto.GoalDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;
import java.util.Collection;

/**
 *
 * @author Jaromir Sys
 */
public interface IGoalFacade {
    public GoalDTO findById(Long id);
    public Collection<GoalDTO> findAll();
    public Long createGoal(GoalCreateDTO goal);
    public void deleteGoal(GoalDTO goal);
    public void updateGoal(GoalDTO goal);
    
    public Collection<GoalDTO> findByGoalTime(Integer date);
    
    public Collection<GoalDTO> findByGame(GameDTO game);
    
    public Collection<GoalDTO> findByDescription(String description);
    
    public Collection<GoalDTO> findByplayer(PlayerDTO player);
    
    public GoalCreateDTO map(GoalDTO goal);
    
    public GoalDTO map(GoalCreateDTO goal);
}
