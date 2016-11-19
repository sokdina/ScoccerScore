package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.TeamCreateDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import java.util.List;

/**
 * 
 * @author sokdina999@gamil.com
**/


public interface TeamFacade {
		
	public Long createTeam(TeamCreateDTO t);
	public void deleteTeam(Long teamId);
	public TeamDTO getTeamById(Long teamId);
	public List<TeamDTO> getAllTeams();
	//public void addPlayer(Long teamId, Long playerId);
	//public void removePlayer(Long teamId, Long playerId);
}
