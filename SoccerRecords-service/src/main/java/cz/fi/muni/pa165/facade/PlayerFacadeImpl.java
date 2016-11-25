
package cz.fi.muni.pa165.facade;


import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.service.BeanMappingService;
import cz.fi.muni.pa165.service.GoalServiceImpl;
import cz.fi.muni.pa165.service.IGoalService;
import cz.fi.muni.pa165.service.IPlayerService;
import java.util.ArrayList;

import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.fi.muni.pa165.service.ITeamService;

/**
 *
 * @author Martin Kocak
 */
@Service
@Transactional
public class PlayerFacadeImpl implements IPlayerFacade{

    @Autowired
    private BeanMappingService beanMappingService;
    
    @Inject
    private IPlayerService playerService;
           
    @Inject
    private ITeamService teamService;
    
    @Inject
    private IGoalService goalService;
    
    @Override
    public PlayerDTO findById(Long id) {
       return beanMappingService.mapTo(playerService.findById(id), PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> findAll() {
        return beanMappingService.mapTo(playerService.findAll(), PlayerDTO.class);
    }

    @Override
    public Long createPlayer(PlayerDTO p) {
        Player mPlayer = beanMappingService.mapTo(p, Player.class);
        Player newPlayer = playerService.createPlayer(mPlayer);
	return newPlayer.getId();
        
    }

    @Override
    public void changeTeam(Long playerId, Long teamId) {
        playerService.changeTeam(playerService.findById(playerId), teamService.findById(teamId));
    }

    @Override
    public void deletePlayer(Long playerId) {
        playerService.deletePlayer(playerService.findById(playerId));
    }

    @Override
    public List<PlayerDTO> findPlayersByTeam(Long teamId) {
        List<Player> pls = new ArrayList<>();
        pls.addAll(playerService.findPlayersByTeam(teamService.findById(teamId)));
        return beanMappingService.mapTo(pls, PlayerDTO.class);

    }
    
    @Override
    public void addGoal(Long playerId, Long goalId) {
        playerService.addGoal(playerService.findById(playerId), goalService.findById(goalId));
    }

    @Override
    public void removeGoal(Long playerId, Long goalId) {
        playerService.removeGoal(playerService.findById(playerId), goalService.findById(goalId));
    }

    
}
