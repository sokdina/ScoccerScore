
package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Player;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Jaromir Sys
 */
public class GoalDTO{
     private Long id;

    private int goalTime;

    private String description;
    
    private GameDTO game;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameDTO getGame() {
        return game;
    }

    public void setGame(GameDTO game) {
        this.game = game;
    }
    
    public int getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(int goalTime) {
        this.goalTime = goalTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.goalTime);
        hash = 31 * hash + Objects.hashCode(this.description);
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
        final GoalDTO other = (GoalDTO) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.goalTime, other.goalTime)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "GoalDTO " + id;
        //return "GoalDTO{" + "id=" + id + ", goalTime=" + goalTime + ", description=" + description + ", game=" + game + ", player=" + player + '}';
    }
}