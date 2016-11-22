/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.enums.MatchResult;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author peter
 */
public class GameCreateDTO {
    
    @NotNull
    private TeamDTO homeTeam;

    @NotNull
    private TeamDTO guestTeam;

    @NotNull
    private Date dateOfGame;

    @NotNull
    @Min(0)
    private int homeScore;

    @NotNull
    @Min(0)
    private int guestScore;

    private Set<GoalDTO> goal = new HashSet<>();

    public TeamDTO getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamDTO homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamDTO getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(TeamDTO guestTeam) {
        this.guestTeam = guestTeam;
    }

    public Date getDateOfGame() {
        return dateOfGame;
    }

    public void setDateOfGame(Date dateOfGame) {
        this.dateOfGame = dateOfGame;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getGuestScore() {
        return guestScore;
    }

    public void setGuestScore(int guestScore) {
        this.guestScore = guestScore;
    }

    public Set<GoalDTO> getGoal() {
        return goal;
    }

    public void setGoal(Set<GoalDTO> goal) {
        this.goal = goal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.homeTeam != null ? this.homeTeam.hashCode() : 0);
        hash = 67 * hash + (this.guestTeam != null ? this.guestTeam.hashCode() : 0);
        hash = 67 * hash + (this.dateOfGame != null ? this.dateOfGame.hashCode() : 0);
        hash = 67 * hash + this.homeScore;
        hash = 67 * hash + this.guestScore;
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
        final GameCreateDTO other = (GameCreateDTO) obj;
        if (this.homeScore != other.homeScore) {
            return false;
        }
        if (this.guestScore != other.guestScore) {
            return false;
        }
        if (this.homeTeam != other.homeTeam && (this.homeTeam == null || !this.homeTeam.equals(other.homeTeam))) {
            return false;
        }
        if (this.guestTeam != other.guestTeam && (this.guestTeam == null || !this.guestTeam.equals(other.guestTeam))) {
            return false;
        }
        if (this.dateOfGame != other.dateOfGame && (this.dateOfGame == null || !this.dateOfGame.equals(other.dateOfGame))) {
            return false;
        }
        return true;
    }
    
    
}
