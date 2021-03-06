package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Martin Kocák
 */
@Repository("PlayerDaoImpl")
public class PlayerDaoImpl implements IPlayerDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional
    public void create(Player player) {

        if(player.getName().equals(""))
            throw new IllegalArgumentException("Player name must be set");
        

        if(player.getDressNumber() <= 0 )           
            throw new IllegalArgumentException("Player dress number has wrong format");
                     
        em.persist(player);  
        
    }
    
    @Override
    @Transactional
    public void delete(Player player) {
        
        em.remove(em.contains(player) ? player : em.merge(player));
      
    }

    @Override
    @Transactional
    public Set<Player> findAll() {
        Set<Player> players = new HashSet<>();
        players.addAll(em.createQuery("Select p FROM Player p", Player.class).getResultList());
        return Collections.unmodifiableSet(players);
    }

    @Override
    @Transactional
    public Player findById(Long id) {
        if(id != null)
            return em.find(Player.class, id);
        else throw new NullPointerException("Id cannot be null");
    }

    @Override
    @Transactional
    public void update(Player player) {
        
        if(player.getName().equals(""))
            throw new IllegalArgumentException("Player name must be set");
        

        if(player.getDressNumber() <= 0 )           
            throw new IllegalArgumentException("Player dress number has wrong format");
        
 
        em.merge(player);
    }    

    @Override
    @Transactional
    public Set<Player> findByName(String name) {
       Set<Player> players = new HashSet<>();
       players.addAll( em.createQuery("SELECT p FROM Player p WHERE p.name like :name ",
				Player.class).setParameter("name", "%"+name+"%").getResultList());
       return Collections.unmodifiableSet(players);
    }
    
    @Override
    @Transactional
    public Set<Player> findPlayersByTeam(Team t){
       Set<Player> players = new HashSet<>();
       players.addAll( em.createQuery("SELECT p FROM Player p WHERE p.team =:team ",
				Player.class).setParameter("team", t).getResultList());
       return Collections.unmodifiableSet(players);
    }
}
