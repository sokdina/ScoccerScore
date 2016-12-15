package cz.muni.fi.pa165.mvc.controllers;

import com.google.common.collect.Lists;
import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.dto.UserCreateDTO;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.facade.IPlayerFacade;
import cz.fi.muni.pa165.facade.ITeamFacade;
import static cz.muni.fi.pa165.mvc.controllers.PlayerController.log;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/statistics")
public class StatisticController {
    
    final static Logger log = LoggerFactory.getLogger(PlayerController.class);
  
    @Autowired
    private IPlayerFacade playerFacade;
    
    @Autowired
    private ITeamFacade teamFacade;
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<PlayerDTO> players = playerFacade.getsortedPlayerByCountGoals();
        for(int i = 0; i < players.size(); i++){
            log.info(players.get(i).toString());
        }        
        model.addAttribute("players", players);
        return "statistics/list";
    }
    
    @RequestMapping(value = "/standings", method = RequestMethod.GET)
    public String standings(Model model) {
        List<TeamDTO> teams = teamFacade.getTeamsSortedByPoints();
        for(int i = 0; i < teams.size(); i++){
            log.info(teams.get(i).toString());
        }        
        model.addAttribute("teams", teams);
        int points[] = new int[teams.size()];
        int[][] score = new int[teams.size()][2];
        for( int i =0; i<teams.size();i++){
            points[i] = teamFacade.getTeamPoints(teams.get(i));
            score[i] = teamFacade.getTeamScore(teams.get(i));
        }
        
        model.addAttribute("score",score);
        model.addAttribute("points",points);
        return "statistics/standings";
    }
    
    @RequestMapping(value = "/viewPlayerDetail/{id}" , method = RequestMethod.GET)
    public String viewPlayerDetail(@PathVariable long id, Model model) {
        log.debug("viewPlayerDetail({})", id);
        model.addAttribute("player", playerFacade.findById(id));
        log.debug("viewPlayerDetail({})", playerFacade.findById(id));
        return "statistics/viewPlayerDetail";
    }
    
    @RequestMapping(value = "/brackets", method = RequestMethod.GET)
    public String brackets(Model model) {
        log.debug("teams()");
        model.addAttribute("teams", teamFacade.getAllTeams());
        return "statistics/brackets";
    }
    
    @RequestMapping(value = "/generated" , method = RequestMethod.POST)
    public String generated(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        String[] checkboxValues = request.getParameterValues("teamIds");
        if(checkboxValues == null){
            redirectAttributes.addFlashAttribute("alert_warning", "You must select count wchich is power of two like 2, 4 , 8 ..., except zero");
            return "redirect:" + uriBuilder.path("/statistics/brackets").toUriString() ;
        }
        
        if((checkboxValues.length & checkboxValues.length-1) != 0 || checkboxValues.length == 1 ){
            redirectAttributes.addFlashAttribute("alert_warning", "You must select count wchich is power of two like 2, 4 , 8 ...");
            return "redirect:" + uriBuilder.path("/statistics/brackets").toUriString() ;
        }
        
        Set<TeamDTO> selectedTeams = new HashSet<>();
        for(int i=0; i<checkboxValues.length; i++){
            selectedTeams.add(teamFacade.getTeamById(  Long.parseLong(checkboxValues[i], 10) ));
        }
        
        List<GameDTO> games = teamFacade.createTurnamentBrackets(selectedTeams);              
        model.addAttribute("games",Lists.reverse(games));
        return "statistics/generated";
    }
    
}
