/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.PlayerCreateDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.enums.Position;
import cz.fi.muni.pa165.facade.IPlayerFacade;
import cz.fi.muni.pa165.facade.ITeamFacade;
import cz.muni.fi.pa165.mvc.validator.PlayerCreateDTOValidator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Martin
 */
@Controller
@RequestMapping("/player")
public class PlayerController {
    
    final static Logger log = LoggerFactory.getLogger(PlayerController.class);
  
    @Autowired
    private IPlayerFacade playerFacade;
    
    @Autowired
    private ITeamFacade teamFacade;
    
    
    /**
     * Shows a list of products with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<PlayerDTO> players = playerFacade.findAll();
        for(int i = 0; i < players.size(); i++){
            log.info(players.get(i).toString());
        }
        
        model.addAttribute("players", players);
        return "player/list";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPlayer(Model model) {
        log.debug("new()");
        model.addAttribute("playerCreate", new PlayerCreateDTO());
        return "player/new";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPlayer(@PathVariable long id, Model model) {
        log.debug("edit()");
        PlayerDTO p = playerFacade.findById(id);
        model.addAttribute("playerEdit", convertPlayerDTO(p));
        return "player/edit";
    }
    
    @ModelAttribute("teams")
    public List<TeamDTO> teams() {
        log.debug("categories()");
        return teamFacade.getAllTeams();
    }
    
    @ModelAttribute("positions")
    public Position[] positions() {
        log.debug("position()");
        return Position.values();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        PlayerDTO player = playerFacade.findById(id);
        playerFacade.deletePlayer(id);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Player \"" + player.toString() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/player/list").toUriString();
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("player", playerFacade.findById(id));
        log.debug("view({})", playerFacade.findById(id));
        return "player/view";
    }
    
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof PlayerCreateDTO) {
            binder.addValidators(new PlayerCreateDTOValidator());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(true);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        }
    }  
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("playerCreate") PlayerCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(playerCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            log.debug("some errror");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
                
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "player/new";
            
        }
       
        //create player
        if(formBean.getName().equals("")){
            redirectAttributes.addFlashAttribute("alert_warning", "Player creation failed - Player name cannot be emty string!");
            return "redirect:" + uriBuilder.path("/player/new").build().encode().toUriString();
        }
        
        if(formBean.getDateOfBirth().compareTo(new Date())>0){
            redirectAttributes.addFlashAttribute("alert_warning", "Player creation failed - Player date cannot be bigger than actuall date!");
            return "redirect:" + uriBuilder.path("/player/new").build().encode().toUriString();
        }

        Long id = playerFacade.createPlayer(convertPlayerCreateDTO(formBean));

        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Player " + playerFacade.findById(id).toString() + " was created");
        return "redirect:" + uriBuilder.path("/player/list").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("playerEdit") PlayerCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("edit(playerEdit={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            log.debug("some errror");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
                
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "player/edit";
            
        }
        
        if(formBean.getName().equals("")){
            redirectAttributes.addFlashAttribute("alert_warning", "Player updating failed - Player name cannot be emty string!");
            return "redirect:" + uriBuilder.path("/player/edit").build().encode().toUriString();
        }
        
        if(formBean.getDateOfBirth().compareTo( new Date())>0){
            redirectAttributes.addFlashAttribute("alert_warning", "Player updaeting failed - Player datee cannot be bigger than actuall date!");
            return "redirect:" + uriBuilder.path("/player/edit").build().encode().toUriString();
        }
        playerFacade.updatePlayer(convertPlayerCreateDTO(formBean));
        
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Player was edited");
        return "redirect:" + uriBuilder.path("/player/list").toUriString();
        
    }
    
    private PlayerCreateDTO convertPlayerDTO(PlayerDTO player){
        PlayerCreateDTO p= new PlayerCreateDTO();
        p.setCountry(player.getCountry());
        p.setDateOfBirth(player.getDateOfBirth());
        p.setDressNumber(player.getDressNumber());
        p.setName(player.getName());
        p.setPosition(player.getPosition());
        p.setTeamId(player.getTeam().getId());  
        p.setId(player.getId());
        return p;
    }
    
    private PlayerDTO convertPlayerCreateDTO(PlayerCreateDTO player){
        PlayerDTO p= new PlayerDTO();
        p.setCountry(player.getCountry());
        p.setDateOfBirth(player.getDateOfBirth());
        p.setDressNumber(player.getDressNumber());
        p.setName(player.getName());
        p.setPosition(player.getPosition());
        p.setTeam(teamFacade.getTeamById(player.getTeamId()));      
        p.setId(player.getId());
        return p;
    }
}