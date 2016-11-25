
package cz.fi.muni.pa165.facade;


import cz.fi.muni.pa165.dto.PlayerDTO;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Martin Kocak
 */
public interface IPlayerFacade {
    
     /**
     * Finds player DTO by its id
     * @param id
     * @return PlayerDTO 
     */
    public PlayerDTO findById(Long id);
    
     /**
     * Finds list of players DTOs 
     * @return List of PlayerDTO
     */
    public List<PlayerDTO> findAll();
    
     /**
     * Creates  player 
     * @param p
     * @return Long id
     */
    public Long createPlayer(PlayerDTO p);
    
    /**
     * Change player current team by new one
     * @param playerId
     * @param teamId
     */
    public void changeTeam(Long playerId, Long teamId);
    
    /**
     * deletes plaer by its id
     * @param playerId
     */
    public void deletePlayer(Long playerId);
    
    /**
     * Find all players by team id, return List of PlayerDTO
     * @param teamId
     * @return List of PlayerDTO
     */
    public List<PlayerDTO> findPlayersByTeam(Long teamId);
    
    /**
     * Add Goal to player 
     * @param playerId
     * @param goalId
     */
    public void addGoal(Long playerId, Long goalId);
    
    /**
     * Delete sepcific goal which belong to player  
     * @param playerId
     * @param goalId
     */
    public void removeGoal(Long playerId, Long goalId);

}
