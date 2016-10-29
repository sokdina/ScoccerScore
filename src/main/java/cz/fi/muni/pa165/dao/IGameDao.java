package cz.fi.muni.pa165.dao;


import cz.fi.muni.pa165.entity.Game;
import java.util.List;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
public interface IGameDao 
{
	public void create(Game g);

    public Game update(Game g);

    public void delete(Game g) throws IllegalArgumentException;

    public List<Game> findGamesBetweenTeams(long teamId1, long teamId2);
    
    public Game findById(long id);

    public List<Game> findAll();

}

