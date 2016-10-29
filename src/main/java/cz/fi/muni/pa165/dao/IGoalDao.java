package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
public interface IGoalDao 
{	 
	public void create(Goal parameter);	 
	public void delete(Goal parameter) ;
	public Set<Goal> findAll() ;	 
	public Goal findById(long parameter) ;	 
	public void update(Goal parameter) ;
        public Set<Goal> findByPlayer(Player player);
        public Set<Goal> findByGame(Game game);
}

