package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Game;
import org.springframework.stereotype.Service;

import java.util.List;

import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.entity.Player;
import java.util.Set;

/**
 * 
 * A TeamService defines some services (create, delete and update team, find a team by id or list all teams) 
 * to ITeamDao for references to a Team entity.
 *
 * @author sokdina999@gamil.com
**/
public interface ITeamService {

	 Team create(Team t);
	 void update(Team t);
	 void delete(Long teamId);
	 Team findById(Long id);
	 List<Team> findByAll();
	 List<Team> findByName(String name);
         void addPlayer(Team t, Player p);
	 void deletePlayer(Team t, Player p);
         List<Game> createTurnamentBrackets(Set<Team> teams);
}



