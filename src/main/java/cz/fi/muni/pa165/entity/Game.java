package cz.fi.muni.pa165.entity;
import cz.fi.muni.pa165.enums.MatchResult;
import java.util.Set;
import java.util.Date;
import java.util.Objects;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
 
@javax.persistence.Entity 
public class Game
{

	@javax.persistence.Id
        @javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.Column(nullable = false) 
	private Long id;
	 
	@javax.persistence.OneToOne 
	private Team homeTeam;
	 
	@javax.persistence.OneToOne 
	private Team guestTeam;
	 
	@javax.persistence.Temporal(javax.persistence.TemporalType.DATE) 
	@javax.persistence.Column(nullable = false) 
	private Date dateOfGame;
	 
	@javax.persistence.Enumerated(javax.persistence.EnumType.STRING) 
	@javax.persistence.Column(nullable = false) 
	private MatchResult result;
	 
	@javax.persistence.Column(nullable = false) 
	private int homeScore;
	 
	@javax.persistence.Column(nullable = false) 
	private int guestScore;
	 
	@OneToMany(mappedBy = "game") 
	private Set<Goal> goal;

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

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
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
        return "Game{" + "id=" + id + ", homeTeam=" + homeTeam + ", guestTeam=" + guestTeam + ", dateOfGame=" + dateOfGame + ", result=" + result + ", homeScore=" + homeScore + ", guestScore=" + guestScore + ", goal=" + goal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.homeTeam);
        hash = 97 * hash + Objects.hashCode(this.guestTeam);
        hash = 97 * hash + Objects.hashCode(this.dateOfGame);
        hash = 97 * hash + Objects.hashCode(this.result);
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
        if (this.result != other.result) {
            return false;
        }
        if (!Objects.equals(this.goal, other.goal)) {
            return false;
        }
        return true;
    }
        
        
}

