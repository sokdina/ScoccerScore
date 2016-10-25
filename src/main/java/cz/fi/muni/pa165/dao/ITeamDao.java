package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Team;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
public interface ITeamDao 
{	 
	public void create(Team parameter) ;
	 
	public void delete(Team parameter) ;
	 
	public Set<Team> findAll() ;
	 
	public Team findById(long parameter) ;
	 
	public void update(Team parameter) ;

}

