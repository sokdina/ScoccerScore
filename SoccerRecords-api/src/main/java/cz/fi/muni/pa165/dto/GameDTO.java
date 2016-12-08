/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.MatchResult;
import java.util.*;

/**
 *
 * @author peter
 */
public class GameDTO {
    
    private Long id;

    private Team homeTeam;

    private Team guestTeam;

    private Date dateOfGame;

    private MatchResult matchResult;

    private int homeScore;

    private int guestScore;

    private Set<GoalDTO> goals = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Team guestTeam) {
        this.guestTeam = guestTeam;
    }

    public Date getDateOfGame() {
        return dateOfGame;
    }

    public void setDateOfGame(Date dateOfGame) {
        this.dateOfGame = dateOfGame;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
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

    public Set<GoalDTO> getGoals() {
        return goals;
    }

    public void setGoals(Set<GoalDTO> goal) {
        this.goals = goal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.homeTeam != null ? this.homeTeam.hashCode() : 0);
        hash = 23 * hash + (this.guestTeam != null ? this.guestTeam.hashCode() : 0);
        hash = 23 * hash + (this.dateOfGame != null ? this.dateOfGame.hashCode() : 0);
        hash = 23 * hash + (this.goals != null ? this.goals.hashCode() : 0);
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
        final GameDTO other = (GameDTO) obj;
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
        if (this.matchResult != other.matchResult) {
            return false;
        }
        if (this.goals != other.goals && (this.goals == null || !this.goals.equals(other.goals))) {
            return false;
        }
        return true;
    }
    
    
}
