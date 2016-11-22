/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameDTO;
import java.util.List;

/**
 *
 * @author peter
 */
public interface IGameFacade {
    
    
    public void create(GameDTO g);

    public GameDTO update(GameDTO g);

    public void delete(GameDTO g) throws IllegalArgumentException;

    public List<GameDTO> findGamesBetweenTeams(long teamId1, long teamId2);
    
    public GameDTO findById(long id);

    public List<GameDTO> findAll();
}
