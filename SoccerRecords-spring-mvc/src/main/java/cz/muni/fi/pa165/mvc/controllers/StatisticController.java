package cz.muni.fi.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.facade.IPlayerFacade;
import static cz.muni.fi.pa165.mvc.controllers.PlayerController.log;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Martin
 */
@Controller
@RequestMapping("/statistics")
public class StatisticController {
    
    final static Logger log = LoggerFactory.getLogger(PlayerController.class);
  
    @Autowired
    private IPlayerFacade playerFacade;
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<PlayerDTO> players = playerFacade.getsortedPlayerByCountGoals();
        for(int i = 0; i < players.size(); i++){
            log.info(players.get(i).toString());
        }        
        model.addAttribute("players", players);
        return "statistics/list";
    }
    
    @RequestMapping(value = "/viewPlayerDetail/{id}" , method = RequestMethod.GET)
    public String viewPlayerDetail(@PathVariable long id, Model model) {
        log.debug("viewPlayerDetail({})", id);
        model.addAttribute("player", playerFacade.findById(id));
        log.debug("viewPlayerDetail({})", playerFacade.findById(id));
        return "statistics/viewPlayerDetail";
    }
}
