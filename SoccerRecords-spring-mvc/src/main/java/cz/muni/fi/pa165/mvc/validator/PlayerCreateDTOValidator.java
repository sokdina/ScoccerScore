/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.mvc.validator;

import cz.fi.muni.pa165.dto.PlayerCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author maceek
 */
public class PlayerCreateDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PlayerCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PlayerCreateDTO playerCreateDTO = (PlayerCreateDTO) target;
        if (playerCreateDTO.getPosition()== null) return;
        if (playerCreateDTO.getDateOfBirth()== null) return;
        
    }
}
