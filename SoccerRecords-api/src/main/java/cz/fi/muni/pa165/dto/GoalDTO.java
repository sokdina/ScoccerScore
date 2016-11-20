
package cz.fi.muni.pa165.dto;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Jaromir Sys
 */
public class GoalDTO{
     private Long id;

    private Date goalTime;

    private String description;

    private GameDTO game;

    private PlayerDTO player;

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

    public GameDTO getGame() {
        return game;
    }

    public void setGame(GameDTO game) {
        this.game = game;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
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
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        return Objects.equals(this.player, other.player);
    }

    @Override
    public String toString() {
        return "GoalDTO{" + "id=" + id + ", goalTime=" + goalTime + ", description=" + description + ", game=" + game + ", player=" + player + '}';
    }
}
