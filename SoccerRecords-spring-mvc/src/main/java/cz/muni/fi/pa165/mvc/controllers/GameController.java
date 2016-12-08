/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.facade.IGameFacade;
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
 * @author peter
 */
@Controller
@RequestMapping("/game")
public class GameController {
    
    final static Logger log = LoggerFactory.getLogger(GameController.class);
    
    @Autowired
    private IGameFacade gameFacade;
    
    
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
    
}
