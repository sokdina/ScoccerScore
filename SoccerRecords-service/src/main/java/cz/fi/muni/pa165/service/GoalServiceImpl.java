package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.IGoalDao;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jaromir Sys
 */
@Transactional
public class GoalServiceImpl implements IGoalService{

    @Autowired
    private IGoalDao goalDao;
    
    @Override
    public Goal findById(Long id) {
        return goalDao.findById(id);
    }

    @Override
    public Set<Goal> findAll() {
        return goalDao.findAll();
    }

    @Override
    public void createGoal(Goal goal) {
        goalDao.create(goal);
    }

    @Override
    public void deleteGoal(Goal goal) {
        goalDao.delete(goal);
    }

    @Override
    public void updateGoal(Goal goal) {
        goalDao.update(goal);
    }

    @Override
    public Set<Goal> findByGoalTime(Date date) {
        return goalDao.findAll().stream().filter(g->g.getGoalTime().equals(date)).collect(Collectors.toSet());
    }

    @Override
    public Set<Goal> findByGame(Game game) {
        return goalDao.findByGame(game);
    }

    @Override
    public Set<Goal> findByDescription(String description) {
        return goalDao.findAll().stream().filter(g->g.getDescription().equals(description)).collect(Collectors.toSet());
    }

    @Override
    public Set<Goal> findByplayer(Player player) {
        return goalDao.findByPlayer(player);
    }
    
}
