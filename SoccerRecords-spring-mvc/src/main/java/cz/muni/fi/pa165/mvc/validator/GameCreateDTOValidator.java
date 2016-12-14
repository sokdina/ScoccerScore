/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.mvc.validator;

import cz.fi.muni.pa165.dto.GameCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author peter
 */
public class GameCreateDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return GameCreateDTO.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        GameCreateDTO gcd = (GameCreateDTO) o;
        
        if(gcd.getDateOfGame()==null)return;
        if(gcd.getGuestTeam()==null)return;
        if(gcd.getHomeTeam()==null)return;
    }
    
}
