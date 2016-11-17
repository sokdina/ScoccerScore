package cz.fi.muni.pa165.soccerrecordspersistence.entity;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity 
public class Goal
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Temporal(TemporalType.DATE) 
    @Column
    private Date goalTime;

    @Column 
    private String description;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="player_id")
    private Player player;

    public Goal(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(Date goalTime) {
        this.goalTime = goalTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        if(!game.getGoal().contains(this)){
            game.getGoal().add(this);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        if(!player.getGoal().contains(this)){
            player.getGoal().add(this);
        }
    }

    @Override
    public String toString() {
        return "Goal{" + "id=" + id + ", time=" + goalTime + ", description=" + description + ", game=" + game + ", player=" + player + '}';
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
        if (!Objects.equals(this.goalTime, other.goalTime)) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        if (!Objects.equals(this.player, other.player)) {
            return false;
        }
        return true;
    }     
}

