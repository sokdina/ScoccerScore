package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameDTO;
import java.util.Date;
import java.util.List;
import cz.fi.muni.pa165.dto.GoalDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;

/**
 *
 * @author Jaromir Sys
 */
public interface IGoalFacade {
    public GoalDTO findById(Long id);
    public List<GoalDTO> findAll();
    public void createGoal(GoalDTO goal);
    public void deleteGoal(GoalDTO goal);
    public void updateGoal(GoalDTO goal);
    
    public List<GoalDTO> findByGoalTime(Integer date);
    
    public List<GoalDTO> findByGame(GameDTO game);
    
    public List<GoalDTO> findByDescription(String description);
    
    public List<GoalDTO> findByplayer(PlayerDTO player);
}
