package cz.fi.muni.pa165.soccerrecordspersistence.dao;

import cz.fi.muni.pa165.soccerrecordspersistence.entity.Game;
import cz.fi.muni.pa165.soccerrecordspersistence.entity.Goal;
import cz.fi.muni.pa165.soccerrecordspersistence.entity.Player;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *  rudimentary implementation of IGoalDao
 * @author Jaromir Sys
 */
@Repository("GoalDao")
public class GoalDaoImpl implements IGoalDao{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public void create(Goal parameter) {
        this.validateNotNull(parameter);
        entityManager.persist(parameter);
    }

    @Override
    @Transactional
    public void delete(Goal parameter) {
        this.validateNotNull(parameter);
        entityManager.remove(parameter);
    }

    @Override
    @Transactional
    public Set<Goal> findAll() {
        Set<Goal> goals = new HashSet<>();
        goals.addAll(entityManager.createQuery("from Goal",Goal.class).getResultList());
        return Collections.unmodifiableSet(goals);
    }

    @Override
    @Transactional
    public Goal findById(long parameter) {
        return entityManager.find(Goal.class, parameter);
    }

    @Override
    @Transactional
    public void update(Goal parameter) {
        this.validateNotNull(parameter);
        if(!entityManager.contains(parameter)){
            throw new IllegalArgumentException("update called with non managed entity");
        }
        entityManager.merge(parameter);
    }    

    @Override
    public Set<Goal> findByPlayer(Player player) {
        this.validateNotNull(player);
        Set<Goal> goals = new HashSet<>();
        goals.addAll(entityManager.createQuery("from Goal g where g.player = :player",Goal.class).setParameter("player", player).getResultList());
        return Collections.unmodifiableSet(goals);
    }

    @Override
    public Set<Goal> findByGame(Game game) {
        this.validateNotNull(game);
        Set<Goal> goals = new HashSet<>();
        goals.addAll(entityManager.createQuery("from Goal g where g.game = :game",Goal.class).setParameter("game", game).getResultList());
        return Collections.unmodifiableSet(goals);
    }
    
    public void validateNotNull(Object o){
        if(o == null)
        { 
            throw new IllegalArgumentException("input parameter is null");
        }
    }
}
