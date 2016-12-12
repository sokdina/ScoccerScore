/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author maceek
 */
public class UserCreateDTO {
    
    @NotNull
    private String email;

    @NotNull   
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
