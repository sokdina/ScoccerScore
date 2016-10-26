package cz.fi.muni.pa165.entity;
import cz.fi.muni.pa165.enums.MatchResult;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity 
public class Game
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @OneToOne 
    private Team homeTeam;

    @OneToOne 
    private Team guestTeam;

    @Temporal(TemporalType.DATE) 
    @Column
    private Date dateOfGame;

    @Enumerated(EnumType.STRING) 
    @Column
    private MatchResult matchResult;

    @Column
    private int homeScore;

    @Column
    private int guestScore;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "game")
    private Set<Goal> goal = new HashSet<>();

    public Game(){
            super();
    }

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

    public Set<Goal> getGoal() {
        return goal;
    }

    public void setGoal(Set<Goal> goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", homeTeam=" + homeTeam + ", guestTeam=" + guestTeam + ", dateOfGame=" + dateOfGame + ", result=" + matchResult + ", homeScore=" + homeScore + ", guestScore=" + guestScore + ", goal=" + goal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.homeTeam);
        hash = 97 * hash + Objects.hashCode(this.guestTeam);
        hash = 97 * hash + Objects.hashCode(this.dateOfGame);
        hash = 97 * hash + Objects.hashCode(this.matchResult);
        hash = 97 * hash + this.homeScore;
        hash = 97 * hash + this.guestScore;
        hash = 97 * hash + Objects.hashCode(this.goal);
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
        final Game other = (Game) obj;
        if (this.guestScore != other.guestScore) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.homeTeam, other.homeTeam)) {
            return false;
        }
        if (!Objects.equals(this.guestTeam, other.guestTeam)) {
            return false;
        }
        if (!Objects.equals(this.dateOfGame, other.dateOfGame)) {
            return false;
        }
        if (this.matchResult != other.matchResult) {
            return false;
        }
        if (!Objects.equals(this.goal, other.goal)) {
            return false;
        }
        return true;
    }
        
        
}

