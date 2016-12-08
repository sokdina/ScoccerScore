package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.PlayerDTO;
import javax.inject.Inject;

import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;

import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.IPlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import cz.fi.muni.pa165.service.ITeamService;

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
    public void createTeam(TeamDTO teamDTO) {
	teamService.create(beanMappingService.mapTo(teamDTO, Team.class));
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
        return beanMappingService.mapTo( teamService.findById(teamId), TeamDTO.class);

    }

    @Override
    public List<TeamDTO> getAllTeams() {
	return beanMappingService.mapTo(teamService.findByAll(), TeamDTO.class);
    }

    @Override
    public List<TeamDTO> findByName(String name){
	return beanMappingService.mapTo(teamService.findByName(name), TeamDTO.class);
    }

    @Override
    public void addPlayerFacade(TeamDTO t, PlayerDTO p){
        teamService.addPlayer(beanMappingService.mapTo(t, Team.class), beanMappingService.mapTo(p, Player.class));
    }

    @Override
    public void removePlayer(TeamDTO t, PlayerDTO p){
	teamService.deletePlayer(beanMappingService.mapTo(t, Team.class), beanMappingService.mapTo(p, Player.class));
    }



}




