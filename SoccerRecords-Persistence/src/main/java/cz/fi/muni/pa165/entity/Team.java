package cz.fi.muni.pa165.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * Team class is the base class storing and retrieving information about
 * each team.
 * @author sokdina999@gamil.com
**/

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="name", nullable = false,unique=true)
    private String name;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "team")
    private Set<Player> players = new HashSet<>();

    public Set<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        if(player.getTeam() == null){
            player.setTeam(this);
        }
        else if(!player.getTeam().equals(this)){
            player.setTeam(this);
        }
    }
    
    public void removePlayer(Player player){
        this.players.remove(player);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public Team(Long teamId) {
	this.id = teamId;
    }
    public Team() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
 	result = prime * result + ((city == null) ? 0 : city.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
                return true;
        if (obj == null)
                return false;
        if (!(obj instanceof Team))
                return false;
        Team other = (Team) obj;
        if (name == null) {
                if (other.name != null)
                        return false;
        } else if (!name.equals(other.getName()))
                return false;
        if (city == null) {
                if (other.city != null)
                        return false;
        } else if (!city.equals(other.getCity()))
                return false;
        return true;
    }	

    @Override
    public String toString() {
        return "Team{" + "id=" + id + ", name=" + name + ", city=" + city + ", country=" + country + '}';
    }
    
    
    
}
