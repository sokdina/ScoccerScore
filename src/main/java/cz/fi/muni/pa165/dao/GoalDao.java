package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Goal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jaromir Sys
 */
@Repository("GoalDao")
public class GoalDao implements IGoalDao{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public void create(Goal parameter) {
        entityManager.persist(parameter);
    }

    @Override
    @Transactional
    public void delete(Goal parameter) {
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
        entityManager.merge(parameter);
    }    
}
