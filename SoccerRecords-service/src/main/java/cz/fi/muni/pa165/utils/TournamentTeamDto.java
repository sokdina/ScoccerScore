package cz.fi.muni.pa165.utils;

import cz.fi.muni.pa165.entity.Team;

/**
 *
 * @author Jaromir Sys
 */
public class TournamentTeamDto implements Comparable<TournamentTeamDto>
{
    private final Team team;
    private final Integer score;

    public TournamentTeamDto(Team t, Integer s) {
        this.team = t;
        this.score = s;
    }

    @Override
    public int compareTo(TournamentTeamDto o) {
        return Integer.compare(this.getScore(), o.getScore());
    }

    public Integer getScore(){
        return this.score;
    }
    
    public Team getTeam(){
        return this.team;
    }
}
