package cz.fi.muni.pa165.dto;



/**
 *
 * TeamCreateDTO provides Data Transfer Objects
 * (ID and Name of each team) as references 
 * to Team facade interface
 *
 * @author sokdina999@gamil.com
**/

public class TeamCreateDTO
{

    private Long id;

    //@NotNull
    //@Size(min = 3, max = 50)
    private String name;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TeamCreateDTO other = (TeamCreateDTO) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TeamCreateDTO{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

}
