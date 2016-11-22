package cz.fi.muni.pa165.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.inject.Inject;

import java.util.List;

import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;

import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.entity.Player;

/**
 * 
 * TeamService delegates some services (create and delete teams ,
 * find a team by id, list all teams or list teams by name) to ITeamDao.
 * 
 * @author sokdina999@gamil.com
**/

@Service
public class TeamServiceImpl implements TeamService {
	
        @Inject
	private ITeamDao teamDao;
    
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
			teamDao.delete(new Team(teamId)); 	
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
}


  

