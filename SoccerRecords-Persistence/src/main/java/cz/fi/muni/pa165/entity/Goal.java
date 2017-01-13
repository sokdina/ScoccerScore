package cz.fi.muni.pa165.entity;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
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

    @Column
    private Integer goalTime;

    @Column 
    private String description;

    @ManyToOne(cascade = CascadeType.DETACH)
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

    public Integer getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(Integer goalTime) {
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
        if(!game.getGoals().contains(this)){
            game.addGoal(this);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        if(!player.getGoals().contains(this)){
            player.addGoal(this);
        }
    }

    @Override
    public String toString() {
        return "Goal{" + "id=" + id + ", time=" + goalTime + ", description=" + description + ", game=" + ", player=" + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.goalTime);
        hash = 89 * hash + Objects.hashCode(this.description);
        //hash = 89 * hash + Objects.hashCode(this.game);
        //hash = 89 * hash + Objects.hashCode(this.player);
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
