/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.IGameDao;
import cz.fi.muni.pa165.dao.ITeamDao;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author peter
 */
@Service
public class GameServiceImpl implements IGameService {

     @Autowired
     private ITeamDao teamDao;
    
    @Autowired
    private IGameDao gameDao;

    @Override
    public Game create(Game g) {
        try {
            gameDao.create(g);
            return g;
        } catch (Exception e) {
            throw new SoccerRecordsDataAccessException(e);
        }
    }

    @Override
    public Game update(Game g) {
        try {
            return gameDao.update(g);
        } catch (Exception e) {
            throw new SoccerRecordsDataAccessException(e);
        }

    }

    @Override
    public void delete(Game g) throws IllegalArgumentException {
        try {
            gameDao.delete(g);
        } catch (Exception e) {
            throw new SoccerRecordsDataAccessException(e);
        }
    }

    @Override
    public List<Game> findGamesBetweenTeams(long teamId1, long teamId2) {
        try {
            return gameDao.findGamesBetweenTeams(teamId1, teamId2);
        } catch (Exception e) {
            throw new SoccerRecordsDataAccessException(e);
        }
    }

    @Override
    public Game findById(long id) {
        try {
            return gameDao.findById(id);
        } catch (Exception e) {
            throw new SoccerRecordsDataAccessException(e);
        }
    }

    @Override
    public List<Game> findAll() {
        try {
            return gameDao.findAll();
        } catch (Exception e) {
            throw new SoccerRecordsDataAccessException(e);
        }
    }

    @Override
    public List<List<Pair>> generateSeasonMatches() {
        List<List<Pair>> result = new ArrayList<>();
        
        List<Team> teams = teamDao.findByAll();
        Collections.shuffle(teams);
        
        //add ghost team if odd number of teams
        if(teams.size()%2 != 0)teams.add(new Team(-1L));
        
        //round robin
        for(int i = 0; i < teams.size()-1; i++){
            List<Pair> row = new ArrayList<>();
            for(int j = 0; j < teams.size()/2; j++){
                if(teams.get(j).getId() != -1L && teams.get(teams.size()-j-1).getId() != -1L)
                    row.add(new Pair(teams.get(j), teams.get(teams.size()-j-1)));
            }
            result.add(row);
            
            Team t = teams.remove(teams.size()-1);
            teams.add(1, t);
        }
        
        Collections.shuffle(result);
        return result;
    }

}
