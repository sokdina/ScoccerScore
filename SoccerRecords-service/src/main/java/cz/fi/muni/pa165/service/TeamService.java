package cz.fi.muni.pa165.service;

import org.springframework.stereotype.Service;

import java.util.List;

import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.entity.Player;

/**
 * 
 * A TeamService defines some services (create, delete and update team, find a team by id or list all teams) 
 * to ITeamDao for references to a Team entity.
 *
 * @author sokdina999@gamil.com
**/

@Service
public interface TeamService {

	public Team create(Team t);
	public void update(Team t);
	public void delete(Long teamId);
	public Team findById(Long id);
	public List<Team> findByAll();
	public List<Team> findByName(String name);
        public void addPlayer(Team t, Player p);
	public void deletePlayer(Team t, Player p);
}



