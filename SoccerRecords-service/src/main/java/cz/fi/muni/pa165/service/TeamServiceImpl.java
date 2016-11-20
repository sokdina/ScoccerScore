package cz.fi.muni.pa165.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.inject.Inject;

import java.util.List;

import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.entity.Team;

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
	public void create(Team t) {
           	teamDao.create(t);
        }
	               
        @Override
        public void delete(Long teamId){
		teamDao.delete(new Team(teamId)); 	
	}

	@Override
	public Team findById(Long id) {
	   	return teamDao.findById(id);
        }

	@Override
        public List<Team> findByAll() {
		return teamDao.findByAll();
	}
       
        @Override
	public List<Team> findByName(String name) {
		return teamDao.findByName(name);
	}

}
