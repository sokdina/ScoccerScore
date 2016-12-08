/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Game;
import java.util.List;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

/**
 *
 * @author peter
 */
public interface IGameService {
    /**
     * inserts game record into database
     * @param g game to be inserted
     */
    public Game create(Game g);

    /**
     * updates game record in database
     * @param g updated game object
     * @return returns updated game
     */
    public Game update(Game g);

    /**
     * deletes record from database
     * @param g game record to be deleted
     * @throws IllegalArgumentException when no such record in database exists
     */
    public void delete(Game g); //throws IllegalArgumentException;

    /**
     * finds games between two teams
     * @param teamId1 first team
     * @param teamId2 second team
     * @return return all games between this teams
     */
    public List<Game> findGamesBetweenTeams(long teamId1, long teamId2);
    
    /**
     * find game by id
     * @param id id to be found
     * @return returns game record based on id
     */
    public Game findById(long id);

    /**
     * finds all games in database
     * @return return all games
     */
    public List<Game> findAll();
    
    /**
     * generates matches for the whole season
     * @return returns lists (all rounds) of list (one round of season) as Team pairs
     */
    public List<List<Pair>> generateSeasonMatches();
}
