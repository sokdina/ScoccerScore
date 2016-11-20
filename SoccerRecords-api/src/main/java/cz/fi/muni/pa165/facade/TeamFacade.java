package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.TeamCreateDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import java.util.List;

/**
 *
 * TeamFacade provides operations and functions about each team 
 * in the SoccerRecords system
 * (create, delete and update each team, find a team by id, list all teams, list all teams by its name).
 * 
 * @author sokdina999@gamil.com
**/


public interface TeamFacade {
		
	/**
	* createTeam method is used for insert information such as team id,
	* and name into table team through TeamCreateDTO.
	* @param TeamCreateDTO is the TeamCreateDTO DTO class.
	*/	
	public Long createTeam(TeamCreateDTO t);

	/**
	* deleteTeam method is used for delete a team according to team id
	* through TeamCreateDTO.
	* @param teamId is the id of each team.
	*/
	public void deleteTeam(Long teamId);

	/**
    	* Return information about a team corresponding to given team id.
	* Result is always a record about each team such as id, name, city
	* and country.
    	* @param id is the team id.
        */
	public TeamDTO getTeamById(Long teamId);
	
	/**
    	* Returns the list of all information about each team.
	* Result is a list about each team such as id, name, city
	* and country.
        */
	public List<TeamDTO> getAllTeams();
	
	/**
    	* Returns the list of all information about each team.
	* Result is a list about each team such as id, name, city
	* and country corresponding to input team name condition.
        */
	public List<TeamDTO> findByName(String name);
	//public void addPlayer(Long teamId, Long playerId);
	//public void removePlayer(Long teamId, Long playerId);
}
