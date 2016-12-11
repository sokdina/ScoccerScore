package cz.muni.fi.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.GoalDTO;
import cz.fi.muni.pa165.facade.IGoalFacade;
import cz.fi.muni.pa165.facade.IPlayerFacade;
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
@RequestMapping("/goal")
public class GoalController {
    
    final static Logger log = LoggerFactory.getLogger(GoalController.class);
    
    @Autowired
    private IGoalFacade goalFacade;
    
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
        List<GoalDTO> goals = goalFacade.findAll();
        
        for(GoalDTO goal: goals){
            log.info(goal.toString());
        }
        
        model.addAttribute("goals", goals);
        return "goal/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        GoalDTO goal = goalFacade.findById(id);
        goalFacade.deleteGoal(goal);
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
    
}