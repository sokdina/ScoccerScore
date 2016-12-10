/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.PlayerCreateDTO;
import cz.fi.muni.pa165.dto.UserAuthenticateDTO;
import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.facade.UserFacade;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Martin
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    
    final static Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private UserFacade userFacade;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){
          
        model.addAttribute("userDTO", new UserCreateDTO());       
        return "/user/login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder){

        HttpSession session = request.getSession(false);
      
        if (session.getAttribute("authenticatedUser") != null ){  
            session.invalidate();
        }
        redirectAttributes.addFlashAttribute("alert_success", "You were successfully logouted");
        return "redirect:" + uriBuilder.path("/").toUriString();
    }
    
    @RequestMapping(value = "/trylogin", method = RequestMethod.POST)
    public String trylogin(HttpServletRequest request,
            @Valid @ModelAttribute("userDTO") UserCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        UserDTO matchingUser = userFacade.findUserByEmail(formBean.getEmail());
        if(matchingUser==null) {
            redirectAttributes.addFlashAttribute("alert_danger", "Wrong parameters, email or password doesn`t match");
            return "redirect:" + uriBuilder.path("/user/login").toUriString();
        }
        UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO();
        userAuthenticateDTO.setUserId(matchingUser.getId());
        userAuthenticateDTO.setPassword(formBean.getPassword());
        
        if (!userFacade.isAdmin(matchingUser)) {
            redirectAttributes.addFlashAttribute("alert_danger", "Wrong parameters, email or password doesn`t match");
            return "redirect:" + uriBuilder.path("/user/login").toUriString();
        }
        if (!userFacade.authenticate(userAuthenticateDTO)) {
            redirectAttributes.addFlashAttribute("alert_danger", "Wrong parameters, email or password doesn`t match");
           return "redirect:" + uriBuilder.path("/user/login").toUriString();
        }
        log.trace("user",matchingUser);
        request.getSession().setAttribute("authenticatedUser",matchingUser);
        return "home";
        
    }
    
    
    
}
