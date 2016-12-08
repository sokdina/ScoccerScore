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
    
    public Player findById(Long id);
    public List<Player> findAll();
    public Player createPlayer(Player p);
    public void updatePlayer(Player p);
    public void changeTeam(Player player, Team team);
    public void deletePlayer(Player player);
    public Set<Player> findPlayersByTeam(Team team);
    public void addGoal(Player player, Goal goal);
    public void removeGoal(Player player, Goal goal);

    
}