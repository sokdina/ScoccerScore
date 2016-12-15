package cz.fi.muni.pa165.service;

import com.google.common.collect.Lists;
import com.google.common.math.IntMath;
import cz.fi.muni.pa165.comparator.SortByPoints;
import cz.fi.muni.pa165.dao.IGameDao;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;

import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.enums.MatchResult;
import cz.fi.muni.pa165.utils.TournamentTeamDto;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * TeamService delegates some services (create and delete teams ,
 * find a team by id, list all teams or list teams by name) to ITeamDao.
 * 
 * @author sokdina999@gamil.com
**/

@Service
public class TeamServiceImpl implements ITeamService {
	
        @Autowired
	private ITeamDao teamDao;
        
        @Autowired
        private IGameDao gameDao;
        
    
	@Override
	public Team create(Team t) {
		try{           	
			teamDao.create(t);
			return t;
		} catch(Exception ex){
			throw new SoccerRecordsDataAccessException(ex);
		}
        }
      
	@Override
        public void update(Team t){
		try{           	
			teamDao.update(t);
		} catch(Exception ex){
			throw new SoccerRecordsDataAccessException(ex);
		}
	}
      
        @Override
        public void delete(Long teamId){
		try{     
			teamDao.delete( teamDao.findById(teamId)); 	
		} catch(Exception ex){
            		throw new SoccerRecordsDataAccessException(ex); 
       		}
	}

	@Override
	public Team findById(Long id) {
		try{	   	
			return teamDao.findById(id);
		} catch(Exception ex){
			throw new SoccerRecordsDataAccessException(ex);
		}
        }

	@Override
        public List<Team> findByAll() {
		try{
			return teamDao.findByAll();
		} catch(Exception ex){
            		throw new SoccerRecordsDataAccessException(ex); 
       		}
	}
       
        @Override
	public List<Team> findByName(String name) {
		try{
			return teamDao.findByName(name);
		} catch(Exception ex){
            		throw new SoccerRecordsDataAccessException(ex); 
       		}
	}

	@Override	
	public void addPlayer(Team t, Player p){
		if(t.getPlayers().contains(p)){
			throw new SoccerRecordsDataAccessException(
					"Teams already contain this player. Team: "
							+ t.getId() + ", player: "
							+ p.getId());
		}
		t.addPlayer(p);
	}

	@Override
	public void deletePlayer(Team t, Player p){
		try{
			t.removePlayer(p);
		} catch(Exception ex){
            		throw new SoccerRecordsDataAccessException(ex); 
       		}
	}
        
        @Override
        public int getTeamPoints(Team t){
            int score = 0;
            score += getGamesWon(t).size()*3;
            score += getGamesDraw(t).size();
            return  score;
        }
        
        @Override
        public List<Team> getTeamsSortedByPoints() {
            List<Team> teams=  new ArrayList<>();
            teams.addAll(teamDao.findByAll());          
            Collections.sort(teams, new SortByPoints(gameDao.findAll()));
            return teams;
        }
        
        @Override
        public List<Game> createTurnamentBrackets(Set<Team> teams){
            List<TournamentTeamDto> sortedTeams = new ArrayList<>();
            List<Game> games = new ArrayList<>();
            teams.stream().forEach((t) -> {
                int score = 0;
                score += getGamesWon(t).size()*3;
                score += getGamesDraw(t).size();
                sortedTeams.add(new TournamentTeamDto(t, score));
            });
            Collections.sort(sortedTeams);
            int partitionSize = IntMath.divide(sortedTeams.size(), 2, RoundingMode.UP);
            List<List<TournamentTeamDto>> parts = Lists.partition(sortedTeams,partitionSize);
            int count = parts.get(1).size();
            if(parts.size() == 2){
                Date today = new Date();
                long plus2days = 1000 * 60 * 60 * 24*2;
                for(TournamentTeamDto t1 :parts.get(0)){
                    Game g = new Game();
                    count--;
                    if(parts.get(1).size()>0){
                        TournamentTeamDto t2 = parts.get(1).get(count);
                        g.setGuestTeam(t2.getTeam());
                    }
                    g.setHomeTeam(t1.getTeam());
                    g.setDateOfGame(new Date(today.getTime()+( plus2days * (long)(count+1))));
                    games.add(g);
                }
            }
            
            return games;
            
        }
        
        
        private Set<Game> getGamesWon(Team t){
            Set<Game> games = new HashSet<>();
            
            games.addAll(
                gameDao.findAll().
                    stream().
                    filter(g->g.getHomeTeam().equals(t)).
                    filter(g->g.getMatchResult().equals(MatchResult.HOME_TEAM_WIN)).
                    collect(Collectors.toSet()));
            
            games.addAll(
                gameDao.findAll().
                    stream().
                    filter(g->g.getGuestTeam().equals(t)).
                    filter(g->g.getMatchResult().equals(MatchResult.GUEST_TEAM_WIN)).
                    collect(Collectors.toSet()));
            return games;
        }
        
        private Set<Game> getGamesDraw(Team t){
            Set<Game> games = new HashSet<>();
            
            games.addAll(
                gameDao.findAll().
                    stream().
                    filter(g->g.getMatchResult().equals(MatchResult.DRAW)).
                    filter(g->g.getGuestTeam().equals(t) || g.getHomeTeam().equals(t)).
                    collect(Collectors.toSet()));
            
            return games;
        }

    @Override
    public int[] getTeamScore(Team t) {
        int goalsScored = 0;
        int goalsConsidered = 0;
        
        List<Team> teams =  teamDao.findByAll();
        
        for(Team team : teams){
            if(team.getId() != t.getId()){
                List<Game> g = new ArrayList<>();
                try{
                    g.addAll(gameDao.findGamesBetweenTeams(t.getId(), team.getId()));
                }catch(SoccerRecordsDataAccessException e){
                    
                }
                
                for(Game game : g){
                    if (game.getGuestTeam().getId() != t.getId()){
                        goalsScored += game.getHomeScore();
                        goalsConsidered+= game.getGuestScore();
                    }else{
                         goalsScored += game.getGuestScore();
                        goalsConsidered+=game.getHomeScore();
                    }
                    
                }
            }
        }
     int score[] = new int[2];
     score[0] = goalsScored;
     score[1] = goalsConsidered;
     return score;
        
    }
}


  

