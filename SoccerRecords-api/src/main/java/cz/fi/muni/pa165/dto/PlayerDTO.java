package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.Position;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * PlayerDTO provides Data Transfer Objects 
 * (id, name, date Of Birth, country, dress number, position in field, team and players) as references 
 * to Team facade interface
 * @author Martin Kocak
 */
public class PlayerDTO {    
  
    
    private Long id;


    private String name;


    private Date dateOfBirth;

    private int dressNumber;


    private Position position;
 
    private String country;


    private Team team;

    private Set<Goal> goal = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDressNumber() {
        return dressNumber;
    }

    public void setDressNumber(int dressNumber) {
        this.dressNumber = dressNumber;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<Goal> getGoal() {
        return goal;
    }

    public void setGoal(Set<Goal> goal) {
        this.goal = goal;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.dateOfBirth);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PlayerDTO)) {
            return false;
        }
       
        PlayerDTO other = (PlayerDTO) obj;
        
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.getDateOfBirth())) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "PlayerDTO{" + "id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", dressNumber=" + dressNumber + ", position=" + position + ", country=" + country + ", team=" + team + ", goal=" + goal + '}';
    }
}