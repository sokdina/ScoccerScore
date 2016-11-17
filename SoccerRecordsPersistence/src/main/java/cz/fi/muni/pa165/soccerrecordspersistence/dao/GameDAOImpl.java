/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.soccerrecordspersistence.dao;

import cz.fi.muni.pa165.soccerrecordspersistence.entity.Game;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Peter Lipcak
 */
@Repository("GameDAOImpl")
public class GameDaoImpl implements IGameDao{
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(Game g) {
        entityManager.persist(g);
    }

    @Override
    @Transactional
    public Game update(Game g) {
        return entityManager.merge(g);
    }

    @Override
    @Transactional
    public void delete(Game g) {
        entityManager.remove(findById(g.getId()));
    }
    
    public List<Game> findGamesBetweenTeams(long teamId1, long teamId2) {
        return entityManager.createQuery("select g from Game g where (g.homeTeam.id=:team1 and g.guestTeam.id=:team2) OR (g.homeTeam.id=:team2 and g.guestTeam.id=:team1)", Game.class)
                .setParameter("team1", teamId1)
                .setParameter("team2", teamId2)
                .getResultList();
    }

    @Override
    public Game findById(long id) {
        return entityManager.find(Game.class, id);
    }

    @Override
    public List<Game> findAll() {
        return entityManager.createQuery("select g from Game g", Game.class)
                .getResultList();
    }
}
