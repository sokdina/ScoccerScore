package cz.fi.muni.pa165.entity;
import java.util.Date;
import java.util.Objects;
import javax.persistence.GenerationType;
 
@javax.persistence.Entity 
public class Goal
{
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Column(nullable = false) 
    private Long id;

    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE) 
    @javax.persistence.Column(nullable = false) 
    private Date time;


    @javax.persistence.Column(nullable = false) 
    private String description;


    @javax.persistence.ManyToOne 
    private Game match;

    @javax.persistence.ManyToOne 
    @javax.persistence.JoinColumn(nullable = false) 
    private Player player;

    public Goal(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Game getMatch() {
        return match;
    }

    public void setMatch(Game match) {
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Goal{" + "id=" + id + ", time=" + time + ", description=" + description + ", match=" + match + ", player=" + player + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Goal other = (Goal) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (!Objects.equals(this.match, other.match)) {
            return false;
        }
        if (!Objects.equals(this.player, other.player)) {
            return false;
        }
        return true;
    }     
}

