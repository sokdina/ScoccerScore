package cz.fi.muni.pa165.service;


import cz.fi.muni.pa165.comparator.SortByGoals;
import cz.fi.muni.pa165.dao.IGoalDao;
import cz.fi.muni.pa165.dao.IPlayerDao;
import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;
import cz.fi.muni.pa165.exception.SoccerServiceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Kocak
 */
@Service
public class PlayerServiceImpl implements IPlayerService{

    @Autowired
    private IPlayerDao playerdao;
    
    @Autowired
    private ITeamDao teamdao;
    
    @Autowired 
    private IGoalDao goalDao;
    
    @Autowired
    private IGoalService goalService;
    
    @Override
    public Player findById(Long id) {
        try{
            return playerdao.findById(id);
        }
        catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public List<Player> findAll() {
        try{
            List<Player> players = new ArrayList<>();
            players.addAll(playerdao.findAll());
            return Collections.unmodifiableList(players);
        }
        catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public Player createPlayer(Player p) {
        try{
            playerdao.create(p);
            return p;
        }
        catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }


    @Override
    public void changeTeam(Player player, Team team) {
        if(team == null || player == null) 
             throw new SoccerRecordsDataAccessException("Team or Goal cannot be null");
        Team oldTeam = player.getTeam();
        oldTeam.removePlayer(player);
        player.setTeam(team);
        try{
            teamdao.update(team);
            teamdao.update(oldTeam);
            playerdao.update(player);
        }catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public void deletePlayer(Player player) {
        
        try{
            for(Iterator<Goal> i = player.getGoals().iterator(); i.hasNext();){
                Goal g = i.next();
                goalService.deleteGoal(g);
            }
            
            player.unsetTeam();
                
            playerdao.delete(player);
                       
        }catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }
    
    @Override
    public Set<Player> findPlayersByTeam(Team team) {
        try{
            Set<Player> players = playerdao.findPlayersByTeam(team);
         
            return Collections.unmodifiableSet(players);
        }catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }

    @Override
    public void updatePlayer(Player p) {
        try{
            playerdao.update(p);
        }
        catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }
    
    @Override
    public void addGoal(Player player, Goal goal) {
        if(goal == null || player == null) 
             throw new SoccerRecordsDataAccessException("Player or Goal cannot be null");
        
        if(player.getGoals().contains(goal)){
            throw new SoccerServiceException(
                    "Player already contais this goal. Player: "
                            + player.getId() + ", goal: "
                            + goal.getId());
        }
        try{
            player.addGoal(goal);
            playerdao.update(player);
            goalDao.update(goal);
        }
        catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
        
    }
    
    @Override
    public void removeGoal(Player player, Goal goal) {
        if(goal == null || player == null) 
             throw new SoccerRecordsDataAccessException("Player or Goal cannot be null");
        
        try{
            player.removeGoal(goal);
            goalService.deleteGoal(goal);
        }catch(Exception e){
            throw new SoccerRecordsDataAccessException(e); 
        }
    }
    
    public List<Player> getsortedPlayerByCountGoals(){
        List<Player> players = new ArrayList<>();
        players.addAll(playerdao.findAll());
        Collections.sort(players, new SortByGoals());
        return players;
    }

    
}
