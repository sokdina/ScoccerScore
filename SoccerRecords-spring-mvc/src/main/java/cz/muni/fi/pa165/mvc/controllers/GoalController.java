package cz.muni.fi.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.dto.GoalCreateDTO;
import cz.fi.muni.pa165.dto.GoalDTO;
import cz.fi.muni.pa165.dto.PlayerCreateDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.facade.IGameFacade;
import cz.fi.muni.pa165.facade.IGoalFacade;
import cz.fi.muni.pa165.facade.IPlayerFacade;
import static cz.muni.fi.pa165.mvc.controllers.PlayerController.log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
 * @author peter
 */
@Controller
@RequestMapping("/goal")
public class GoalController {
    
    final static Logger log = LoggerFactory.getLogger(GoalController.class);
    
    @Autowired
    private IGoalFacade goalFacade;
    
    @Autowired
    private IGameFacade gameFacade;
    
    @Autowired
    private IPlayerFacade playerFacade;
    
    /**
     * Shows a list of goal with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        Collection<GoalDTO> goals = goalFacade.findAll();
        
        for(GoalDTO goal: goals){
            log.info(goal.toString());
        }
        
        model.addAttribute("goals", goals);
        return "goal/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        GoalDTO goal = goalFacade.findById(id);
        goalFacade.deleteGoal(goalFacade.findById(id));
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Goal \"" + goal.toString() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/goal/list").toUriString();
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("goal", goalFacade.findById(id));
        return "goal/view";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPlayer(Model model) {
        List<PlayerDTO> players = playerFacade.findAll();
        List<GameDTO> games = gameFacade.findAll();
        
        log.debug("new()");
        model.addAttribute("goalCreateDTO", new GoalCreateDTO());
        model.addAttribute("games",games);
        model.addAttribute("players", players);
        return "goal/new";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("goalCreateDTO") GoalCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(goalCreate={})", formBean);
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
            return "goal/new";
            
        }
        
        List<PlayerDTO> players = new ArrayList<>();
        
        GameDTO game = gameFacade.findById(formBean.getGameId());
        
        players.addAll(playerFacade.findPlayersByTeam(game.getGuestTeam().getId()));
        players.addAll(playerFacade.findPlayersByTeam(game.getHomeTeam().getId()));
        PlayerDTO player = playerFacade.findById(formBean.getPlayerId());
        
        if(!players.contains(player)){
            redirectAttributes.addFlashAttribute("alert_warning", "Goal creation failed - player is not part of playing teams");
            return "redirect:" + uriBuilder.path("/goal/list").build().encode().toUriString();
        }
        
        
        Long id = goalFacade.createGoal(formBean);

        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Goal " + id + " was created");
        return "redirect:" + uriBuilder.path("/goal/list").buildAndExpand(id).encode().toUriString();
    }    
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGoal(@PathVariable long id, Model model) {
        log.debug("edit()");
        GoalCreateDTO g = goalFacade.map(goalFacade.findById(id));
        List<PlayerDTO> players = playerFacade.findAll();
        List<GameDTO> games = gameFacade.findAll();
        
        model.addAttribute("games",games);
        model.addAttribute("players", players);
        model.addAttribute("goalEdit", g);
        return "goal/edit";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("goalEdit") GoalCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("edit(goalEdit={})", formBean);
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
            return "goal/edit";
            
        }
        
        goalFacade.updateGoal(goalFacade.map(formBean));
        //playerFacade.updatePlayer(convertPlayerCreateDTO(formBean));
        
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Goal was edited");
        return "redirect:" + uriBuilder.path("/goal/list").toUriString();
    }
}