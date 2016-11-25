package cz.fi.muni.pa165.service;


import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Kocak
 */
public interface IPlayerService {
    
    /**
     * Finds player entity by its id
     * @param id
     * @return Player 
     */
    public Player findById(Long id);

    /**
     * Finds list of players entities 
     * @return List of Player
     */
    public List<Player> findAll();

    /**
     * Creates  player entity
     * @param p
     * @return Player
     */
    public Player createPlayer(Player p);

    /**
     * Updates player entity p
     * @param p
     */
    public void updatePlayer(Player p);

    /**
     * Change player current team by new one
     * @param player
     * @param team
     */
    public void changeTeam(Player player, Team team);

    /**
     * Delete player
     * @param player
     */
    public void deletePlayer(Player player);

    /**
     * Find all players entities by team entity
     * @param team
     * @return Set of Player
     */
    public Set<Player> findPlayersByTeam(Team team);

    /**
     * Add Goal to player 
     * @param player
     * @param goal
     */
    public void addGoal(Player player, Goal goal);

    /**
     * Delete sepcific goal entity which belong to player entity 
     * @param player
     * @param goal
     */
    public void removeGoal(Player player, Goal goal);

    
}