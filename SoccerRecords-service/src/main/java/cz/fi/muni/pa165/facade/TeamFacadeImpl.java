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

@Service
@Transactional
public class TeamFacadeImpl implements TeamFacade {

    @Inject
    private TeamService teamService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createTeam(TeamCreateDTO teamCreateDTO) {
        Team team = beanMappingService.mapTo(teamCreateDTO, Team.class);
	teamService.create(team);
	return team.getId();
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


