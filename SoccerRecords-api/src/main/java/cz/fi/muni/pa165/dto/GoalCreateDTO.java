
package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Player;
import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jaromir Sys
 */
public class GoalCreateDTO{

    @Min(0)
    @Max(120)
    private int goalTime;

    @NotNull
    private String description;
    
    @NotNull
    private Game game;
    
    //@NotNull
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
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
        final GoalCreateDTO other = (GoalCreateDTO) obj;
        if (this.goalTime != other.goalTime) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GoalCreateDTO{" + "goalTime=" + goalTime + ", description=" + description + '}';
    }
}