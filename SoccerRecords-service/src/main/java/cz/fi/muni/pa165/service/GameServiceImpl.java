/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.IGameDao;
import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.entity.Game;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author peter
 */
public class GameServiceImpl implements IGameService{

    @Inject
    private IGameDao gameDao;

    @Override
    public void create(Game g) {
        gameDao.create(g);
    }

    @Override
    public Game update(Game g) {
        return gameDao.update(g);
    }

    @Override
    public void delete(Game g) throws IllegalArgumentException {
        gameDao.delete(g);
    }

    @Override
    public List<Game> findGamesBetweenTeams(long teamId1, long teamId2) {
        return gameDao.findGamesBetweenTeams(teamId1, teamId2);
    }

    @Override
    public Game findById(long id) {
        return gameDao.findById(id);
    }

    @Override
    public List<Game> findAll() {
        return gameDao.findAll();
    }
    
    
    
}
