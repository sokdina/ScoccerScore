package cz.fi.muni.pa165.service;


import cz.fi.muni.pa165.dao.IPlayerDao;
import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.exception.SoccerServiceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Kocak
 */
@Service
public class PlayerServiceImpl implements IPlayerService{

    @Inject
    private IPlayerDao playerdao;
    
    @Inject
    private ITeamDao teamdao;
    
    @Override
    public Player findById(Long id) {
        return playerdao.findById(id);
    }

    @Override
    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        players.addAll(playerdao.findAll());
        return Collections.unmodifiableList(players);
    }

    @Override
    public Player createPlayer(Player p) {
        playerdao.create(p);
        return p;
    }

    @Override
    public void addGoal(Player player, Goal goal) {
        if(player.getGoal().contains(goal)){
            throw new SoccerServiceException(
					"Player already contais this goal. Player: "
							+ player.getId() + ", goal: "
							+ goal.getId());
        }
        player.addGoal(goal);
    }

    @Override
    public void removeGoal(Player player, Goal goal) {
        player.removeGoal(goal);
    }

    @Override
    public void changeTeam(Player player, Team team) {
        Team oldTeam = player.getTeam();
        oldTeam.removePlayer(player);
        player.setTeam(team);
    }

    @Override
    public void deletePlayer(Player player) {
        playerdao.delete(player);
    }
    
    @Override
    public Set<Player> findPlayersByTeam(Team team) {
        Set<Player> players = playerdao.findPlayersByTeam(team);

        return players;
    }
    
}
