package cz.fi.muni.pa165.soccerrecordspersistence.dao;

import cz.fi.muni.pa165.soccerrecordspersistence.entity.Game;
import cz.fi.muni.pa165.soccerrecordspersistence.entity.Goal;
import cz.fi.muni.pa165.soccerrecordspersistence.entity.Player;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
public interface IGoalDao 
{	
    /**
     * persists goal entity
     * @param parameter Goal entity
     */
    public void create(Goal parameter);
    
    
    /**
     * deletes persisted entity from db
     * @param parameter Goal entity
     */
    public void delete(Goal parameter);
    
    
    /**
     * finds all persisted entities and return them as a set
     * @return Set<Goal> all goals persisted or empty set
     */
    
    public Set<Goal> findAll();
    
    
    /**
     * finds entity by specified id
     * @param parameter id
     * @return Goal entity or null
     */
    public Goal findById(long parameter) ;
    
    
    /**
     * updates persisted entity
     * @param parameter Goal entity
     */
    public void update(Goal parameter) ;
    
    
    /**
     * retrieves all persisted Goal entities with specified Player
     * @param player Player entity
     * @return Set<Goal> matching the input parameter or empty set
     */
    public Set<Goal> findByPlayer(Player player);
    
    
    /**
     * retrieves all persisted Goal entities with specified Game
     * @param game Game entity
     * @return Set<Goal> matching the input parameter or empty set
     */
    public Set<Goal> findByGame(Game game);
}

