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
    private Long homeTeam;

    @NotNull
    private Long guestTeam;

    @NotNull
    private Date dateOfGame;

    public Long getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Long homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Long getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Long guestTeam) {
        this.guestTeam = guestTeam;
    }

    public Date getDateOfGame() {
        return dateOfGame;
    }

    public void setDateOfGame(Date dateOfGame) {
        this.dateOfGame = dateOfGame;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.homeTeam != null ? this.homeTeam.hashCode() : 0);
        hash = 67 * hash + (this.guestTeam != null ? this.guestTeam.hashCode() : 0);
        hash = 67 * hash + (this.dateOfGame != null ? this.dateOfGame.hashCode() : 0);
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
