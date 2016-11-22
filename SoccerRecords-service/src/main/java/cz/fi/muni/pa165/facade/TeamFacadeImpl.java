package cz.fi.muni.pa165.facade;

import javax.inject.Inject;

import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.dto.TeamCreateDTO;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.facade.TeamFacade;

import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author sokdina999@gamil.com
**/



@Service
@Transactional
public class TeamFacadeImpl implements TeamFacade {

    @Inject
    private TeamService teamService;

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

}
