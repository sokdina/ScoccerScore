package cz.fi.muni.pa165.entity;
import cz.fi.muni.pa165.enums.MatchResult;
import java.util.Set;
import java.util.Date;
import javax.persistence.GenerationType;



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
	 
	@javax.persistence.OneToMany(mappedBy = "match") 
	private Set<Goal> goal;

	public Game(){
		super();
	}	
}

