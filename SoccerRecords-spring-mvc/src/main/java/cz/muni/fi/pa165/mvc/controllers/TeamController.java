/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.facade.ITeamFacade;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author sokdina999@gmail.com
 */

@Controller
@RequestMapping("/team")
public class TeamController {
    
    final static Logger log = LoggerFactory.getLogger(TeamController.class);
    
    @Autowired
    private ITeamFacade teamFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TeamDTO> teams = teamFacade.getAllTeams();
        for(int i = 0; i < teams.size(); i++){
            log.info(teams.get(i).toString());
        }
        
        model.addAttribute("teams", teams);
        return "team/list";
    }
    
    
}
