/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.GameCreateDTO;
import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.facade.IGameFacade;
import cz.fi.muni.pa165.facade.IPlayerFacade;
import cz.fi.muni.pa165.facade.ITeamFacade;
import cz.muni.fi.pa165.mvc.validator.GameCreateDTOValidator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
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
 * @author peter
 */
@Controller
@RequestMapping("/game")
public class GameController {
    
    final static Logger log = LoggerFactory.getLogger(GameController.class);
    
    @Autowired
    private IGameFacade gameFacade;
    
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
        List<GameDTO> games = gameFacade.findAll();
        for(int i = 0; i < games.size(); i++){
            log.info(games.get(i).toString());
        }
        
        List<PlayerDTO> players = playerFacade.findAll();
        for(int i = 0; i < players.size(); i++){
            log.info(players.get(i).toString());
        }
        
        model.addAttribute("players", players);
        model.addAttribute("games", games);
        return "game/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        GameDTO game = gameFacade.findById(id);
        gameFacade.delete(game);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Game \"" + game.toString() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/game/list").toUriString();
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("game", gameFacade.findById(id));
        return "game/view";
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof GameCreateDTO) {
            binder.addValidators(new GameCreateDTOValidator());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(true);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        }
    } 
    
    
    @ModelAttribute("teams")
    public List<TeamDTO> teams() {
        log.debug("teams()");
        return teamFacade.getAllTeams();
    }
    
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("playerCreate") GameCreateDTO formBean, BindingResult bindingResult,
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
            return "game/new";
            
        }
       
        //create game

        Long id = gameFacade.create(formBean);

        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Match " + id + " was created");
        return "redirect:" + uriBuilder.path("/game/list").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newGame(Model model) {
        log.debug("new()");
        model.addAttribute("gameCreateDTO", new GameCreateDTO());
        return "game/new";
    }
    
    
    
}