package cz.fi.muni.pa165.dao;


import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import java.util.Set;
/**
 * @author Martin Kocák
 */
 
public interface IPlayerDao 
{
	/**
	 * This method add player to db
         * @param player 
         * @throws IllegalArgumentException - if attribttes od Player has wrong forma
	 */	 
	public void create(Player player) ;

        /**
         * This method removes player from db, if player exists
         * @param player
	 */	 
	public void delete(Player player) ;
	
        /**
         * This method finds all players in db
         * @return List of players
	 */	 
	public Set<Player> findAll() ;
	
        /**
	 * This method finds player in db if exists by its id
         * @param id
         * @return Player -founded in db
	 */	 
	public Player findById(Long id) ;
	
         /**
	 * This method edits player in db
         * @param player 
         * @throws IllegalArgumentException - if attribttes od Player has wrong format
	 */	 	 
	public void update(Player player) ;
        
        /**
    	* This method finds players in db if exists by their name
	* Result is a set about each player such as id, name, city
	* and country corresponding to input plaer name condition.
        * @param name of player
        * @return set of players
        */
	public Set<Player> findByName(String name);
        
        
        /**
    	* This method finds players in db if exists by their team
	* Result is a set about each player such as id, name, city
	* and country corresponding to input player name condition.
        * @param t 
        * @return set of players
        */
        public Set<Player> findPlayersByTeam(Team t);

}

