package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Goal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jaromir Sys
 */
@Repository("GoalDao")
public class GoalDao extends BaseDao implements IGoalDao{

    /*@PersistenceContext
    private EntityManager entityManager;*/
    
    @Override
    public void create(Goal parameter) {
        entityManager.persist(parameter);
    }

    @Override
    public void delete(Goal parameter) {
        entityManager.remove(parameter);
    }

    @Override
    public Set<Goal> findAll() {
        Set<Goal> goals = new HashSet<>();
        goals.addAll(entityManager.createQuery("from Goal",Goal.class).getResultList());
        return Collections.unmodifiableSet(goals);
    }

    @Override
    public Goal findById(long parameter) {
        return entityManager.find(Goal.class, parameter);
    }

    @Override
    public void update(Goal parameter) {
        entityManager.merge(parameter);
    }    
}
