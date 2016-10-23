package www.muni.cz.dao;

import java.util.List;

import www.muni.cz.entity.Team;

public interface TeamDAO {

        public void create(Team p);
	public Team update(Team p);
	public void delete(Team p)throws IllegalArgumentException;;
	public Team findById(int id);
	public List<Team> findAll();
	public List<Team> findByName(String name);
              
}
