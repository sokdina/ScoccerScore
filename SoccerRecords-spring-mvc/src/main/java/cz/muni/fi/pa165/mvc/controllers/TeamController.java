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
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("team", teamFacade.getTeamById(id));
        return "team/view";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
	try {        
		TeamDTO team = teamFacade.getTeamById(id);		
		teamFacade.deleteTeam(id);
        	redirectAttributes.addFlashAttribute("alert_success", "Team: "+ team.toString() +" was deleted.");
	 } catch(Exception ex){
          	log.warn("cannot delete team {}",id);
            	redirectAttributes.addFlashAttribute("alert_danger", "This team cannot be deleted because it is being matched with another team in La Liga now.");
        }
	return "redirect:" + uriBuilder.path("/team/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTeam(Model model) {
        log.debug("new()");
        model.addAttribute("teamCreate", new TeamDTO());
        return "team/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("teamCreate") TeamDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(teamCreate={})", formBean);
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "team/new";
        }
        Long id = teamFacade.createTeam(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Team: " + teamFacade.getTeamById(id) + " was created");
        return "redirect:" + uriBuilder.path("/team/list").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editTeam(@PathVariable long id, Model model) {
        log.debug("editTeam()");
        TeamDTO t = teamFacade.getTeamById(id);
        model.addAttribute("teamEdit", t);
        return "team/edit";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("teamEdit") TeamDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("edit(teamEdit={})", formBean);
        if (bindingResult.hasErrors()) {
            log.debug("some errror");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
                
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "team/edit";
            
        }
        teamFacade.updateTeam(formBean);
        
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Team was edited");
        return "redirect:" + uriBuilder.path("/team/list").toUriString();
        
    }
}



