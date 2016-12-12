package cz.muni.fi.pa165.mvc.validator;

import cz.fi.muni.pa165.dto.TeamDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author sokdina999@gmail.com
 */
public class TeamValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return TeamDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeamDTO teamDTO = (TeamDTO) target;
        if (teamDTO.getName()== null) return;
        if (teamDTO.getCity() == null) return;
 	if (teamDTO.getCountry()== null) return;
        
    }
}
