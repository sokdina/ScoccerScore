package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Team;
import java.util.List;
import java.util.Set;

/**
 *
 * TeamFacade provides operations and functions about each team 
 * in the SoccerRecords system
 * (create, delete and update each team, find a team by id, list all teams, list all teams by its name).
 * 
 * @author sokdina999@gamil.com
**/


public interface ITeamFacade {
		
	/**
	* createTeam method is used for insert information such as team id,
	* name, city and country into table team through TeamCreateDTO.
	* @param TeamCreateDTO is the TeamCreateDTO DTO class.
	*/	
	public Long createTeam(TeamDTO t);

	/**
	* updateTeam method is used to update each team information (team id,
	* name, city, country) into table team through TeamDTO
	* according to team id.
	* @param TeamDTO is the TeamDTO DTO class.
	*/
	
	public void updateTeam(TeamDTO t);


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

	/**
	* addPlayer method is used for insert a player 
	* according to paramesters team id and player id
	* @param  teamId is team id of each team.
	* @param  playerID is player id of each player.
	*/
	public void addPlayer(Long teamId, Long playerId);

	/**
	* removePlayer method is used for delete a player 
	* according to paramesters team id and player id
	* @param  teamId is team id of each team.
	* @param  playerID is player id of each player.
	*/
	public void removePlayer(Long teamId, Long playerId);

        public List<GameDTO> createTurnamentBrackets(Set<TeamDTO> teams);
        
        public int getTeamPoints(TeamDTO t);
        
        public int[] getTeamScore(TeamDTO t); 
}
