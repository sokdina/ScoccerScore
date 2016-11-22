/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.IGameDao;
import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author peter
 */
@Service
public class GameServiceImpl implements IGameService {

    @Inject
    private IGameDao gameDao;

    @Override
    public void create(Game g) {
        try {
            gameDao.create(g);
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

}
