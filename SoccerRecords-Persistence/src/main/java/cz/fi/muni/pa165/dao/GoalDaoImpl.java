package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * rudimentary implementation of IGoalDao
 * @author Jaromir Sys
 */
@Repository("GoalDao")
public class GoalDaoImpl implements IGoalDao{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void create(Goal parameter) {
        this.validateNotNull(parameter);
        entityManager.persist(parameter);
    }

    @Override
    public void delete(Goal parameter) {
        this.validateNotNull(parameter);
        entityManager.remove(parameter);
    }

    @Override
    public Collection<Goal> findAll() {
        List<Goal> goals = new ArrayList<>();
        goals.addAll(entityManager.createQuery("from Goal",Goal.class).getResultList());
        return Collections.unmodifiableCollection(goals);
    }

    @Override
    public Goal findById(long parameter) {
        return entityManager.find(Goal.class, parameter);
    }

    @Override
    public void update(Goal parameter) {
        this.validateNotNull(parameter);
        if(!entityManager.contains(parameter)){
            throw new IllegalArgumentException("update called with non managed entity");
        }
        entityManager.merge(parameter);
    }    

    @Override
    public Collection<Goal> findByPlayer(Player player) {
        this.validateNotNull(player);
        List<Goal> goals = new ArrayList<>();
        goals.addAll(entityManager.createQuery("from Goal g where g.player = :player",Goal.class).setParameter("player", player).getResultList());
        return Collections.unmodifiableCollection(goals);
    }

    @Override
    public Collection<Goal> findByGame(Game game) {
        this.validateNotNull(game);
        List<Goal> goals = new ArrayList<>();
        goals.addAll(entityManager.createQuery("from Goal g where g.game = :game",Goal.class).setParameter("game", game).getResultList());
        return Collections.unmodifiableCollection(goals);
    }
    
    public void validateNotNull(Object o){
        if(o == null)
        { 
            throw new IllegalArgumentException("input parameter is null");
        }
    }
}
