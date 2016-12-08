package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.GameDTO;
import java.util.List;

/**
 *
 * @author peter
 */
public interface IGameFacade {
    
    
     void create(GameDTO g);

     void update(GameDTO g);

     void delete(GameDTO g); //throws IllegalArgumentException;

     List<GameDTO> findGamesBetweenTeams(long teamId1, long teamId2);
    
     GameDTO findById(long id);

     List<GameDTO> findAll();
}
