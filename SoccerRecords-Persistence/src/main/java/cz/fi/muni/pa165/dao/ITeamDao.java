package cz.fi.muni.pa165.dao;

import java.util.List;
import cz.fi.muni.pa165.entity.Team;

/**
 * TeamDao performs some basic operations such as insert, update, delete and find data in Team table .
 *
 * @author sokdina999@gamil.com
**/



public interface ITeamDao {

	/**
	* create method is used for insert information such as team id,
	* name, city and country into table team.
	* @param team is the Team class.
	*/
        public void create(Team t);
	
	/**
	* update method is used for update information such as 
	* name, city and country according to team id in table team.
	* @param team is the Team class.
	*/
	public Team update(Team t);

	/**
	* delete method is used for delete teams according to team id in table team
	* @param team is the Team class.
	* @throws IllegalArgumentException when there is null.
	*/
	public void delete(Team t)throws IllegalArgumentException;
	
	/**
    	* Returns information about a team corresponding to given team id.
	* Result is always a record about each team such as id, name, city
	* and country.
    	* @param id is the team id.
        */
	public Team findById(Long id);
	
	/**
    	* Returns the list of all information about each team.
	* Result is a list about each team such as id, name, city
	* and country.
        */
	public List<Team> findByAll();
	
	/**
    	* Returns the list of all information about each team.
	* Result is a list about each team such as id, name, city
	* and country corresponding to input team name condition.
        */
	public List<Team> findByName(String name);
              
}
