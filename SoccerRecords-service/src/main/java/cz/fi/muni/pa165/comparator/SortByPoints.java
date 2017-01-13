/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.comparator;

import cz.fi.muni.pa165.dao.IGameDao;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.enums.MatchResult;
import cz.fi.muni.pa165.exception.SoccerRecordsDataAccessException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Martin
 */
public class SortByPoints implements Comparator<Team> {

    
    private List<Game> games;
    
    public SortByPoints(List<Game> games) {
        this.games = games;
    }
       
     
    @Override
    public int compare(Team t1, Team t2) {
        int t1score = 0, t2score = 0;
        t1score += getGamesWon(t1).size()*3;
        t1score += getGamesDraw(t1).size();
        t2score += getGamesWon(t2).size()*3;
        t2score += getGamesDraw(t2).size();
        if (t1score > t2score) {
            return -1;
        } else{
            return 1;
        } 
    }
    
    private Set<Game> getGamesWon(Team t){
            Set<Game> gamesW = new HashSet<>();
            
            gamesW.addAll(
                games.
                    stream().
                    filter(g->g.getHomeTeam().equals(t)).
                    filter(g->g.getMatchResult().equals(MatchResult.HOME_TEAM_WIN)).
                    collect(Collectors.toSet()));
            
            gamesW.addAll(
                games.
                    stream().
                    filter(g->g.getGuestTeam().equals(t)).
                    filter(g->g.getMatchResult().equals(MatchResult.GUEST_TEAM_WIN)).
                    collect(Collectors.toSet()));
            return gamesW;
        }
        
        private Set<Game> getGamesDraw(Team t){
            Set<Game> gamesD = new HashSet<>();
            
            gamesD.addAll(
                games.
                    stream().
                    filter(g->g.getMatchResult().equals(MatchResult.DRAW)).
                    filter(g->g.getGuestTeam().equals(t) || g.getHomeTeam().equals(t)).
                    collect(Collectors.toSet()));
            
            return gamesD;
        }           
}