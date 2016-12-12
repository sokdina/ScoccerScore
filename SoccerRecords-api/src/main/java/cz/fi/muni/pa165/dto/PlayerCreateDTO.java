package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.enums.Position;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Martin
 */
public class PlayerCreateDTO {
    
    private Long id;
    
    @NotNull
    private String name;

    @NotNull   
    private Date dateOfBirth;
    
    @NotNull
    @Min(value = 1)
    private int dressNumber;

    @NotNull
    private Position position;
    
    @NotNull
    private String country;
    
    @NotNull
    private Long teamId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDressNumber() {
        return dressNumber;
    }

    public void setDressNumber(int dressNumber) {
        this.dressNumber = dressNumber;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerCreateDTO other = (PlayerCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlayerCreateDTO{" + "name=" + name + ", dateOfBirth=" + dateOfBirth + ", dressNumber=" + dressNumber + ", position=" + position + ", country=" + country + '}';
    }
    
}
