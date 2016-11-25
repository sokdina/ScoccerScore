
package cz.fi.muni.pa165.facade;


import cz.fi.muni.pa165.dto.PlayerDTO;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Martin Kocak
 */
public interface IPlayerFacade {
    
    public PlayerDTO findById(Long id);
    public List<PlayerDTO> findAll();
    public Long createPlayer(PlayerDTO p);
    public void changeTeam(Long playerId, Long teamId);
    public void deletePlayer(Long playerId);
    public List<PlayerDTO> findPlayersByTeam(Long teamId);
    public void addGoal(Long playerId, Long goalId);
    public void removeGoal(Long playerId, Long goalId);

}
