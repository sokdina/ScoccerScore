package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameDTO;
import javax.inject.Inject;

import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Team;

import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.IPlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import cz.fi.muni.pa165.service.ITeamService;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author sokdina999@gamil.com
**/

@Service
@Transactional
public class TeamFacadeImpl implements ITeamFacade {

    @Inject
    private ITeamService teamService;

    @Inject
    private IPlayerService playerService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createTeam(TeamDTO teamDTO) {
        Team team = beanMappingService.mapTo(teamDTO, Team.class);
	Team newTeam = teamService.create(team);
	return newTeam.getId();
    }

    @Override
    public void updateTeam(TeamDTO t){
	teamService.update(beanMappingService.mapTo(t, Team.class));
    }
	
    @Override
    public void deleteTeam(Long teamId) {
	teamService.delete(teamId);
     }


    @Override
    public TeamDTO getTeamById(Long teamId) {
        Team team = teamService.findById(teamId);
        return (team == null) ? null : beanMappingService.mapTo(team, TeamDTO.class);
    }

    @Override
    public List<TeamDTO> getAllTeams() {
	return beanMappingService.mapTo(teamService.findByAll(), TeamDTO.class);
    }

    @Override
    public List<TeamDTO> findByName(String name){
	List<Team> teams = teamService.findByName(name);
	return (teams == null) ? null : beanMappingService.mapTo(teams, TeamDTO.class);
    }

    @Override
    public void addPlayer(Long teamId, Long playerId){
	teamService.addPlayer(teamService.findById(teamId), playerService.findById(playerId));
    }

    @Override
    public void removePlayer(Long teamId, Long playerId){
	teamService.deletePlayer(teamService.findById(teamId), playerService.findById(playerId));    
    }

    @Override
    public List<GameDTO> createTurnamentBrackets(Set<TeamDTO> teamsDTO){
        List<GameDTO> brackets = new ArrayList<>();
        Set<Team> teams = new HashSet<>();
        teams.addAll( beanMappingService.mapTo(teamsDTO, Team.class));
        brackets.addAll(beanMappingService.mapTo(teamService.createTurnamentBrackets(teams), GameDTO.class));
        return brackets;
    }

}




