package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.rest.ApiUris;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.facade.ITeamFacade;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(ApiUris.ROOT_URI_TEAMS)
public class TeamsController {

    final static Logger logger = LoggerFactory.getLogger(TeamsController.class);

    @Inject
    private ITeamFacade teamFacade;

    /**
     * Get list of Teams curl -i -X GET
     * http://localhost:8080/SoccerRecords-rest/teams
     *
     * @return TeamDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TeamDTO> getTeams() {

        logger.debug("rest getTeams()");
        return teamFacade.getAllTeams();
    }


    /**
     * 
     * Get one team specified by id
     * 
     * @param id identifier for the team
     * @return TeamDTO
     * @throws Exception ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO getTeam(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getTeam({})", id);

        TeamDTO teamDTO = teamFacade.getTeamById(id);
        if (teamDTO == null) {
            throw new ResourceNotFoundException();
        }

        return teamDTO;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO createTeam(@RequestBody TeamDTO team) throws Exception {

        logger.debug("rest createTeam()");

        try {
            Long id = teamFacade.createTeam(team);
            return teamFacade.getTeamById(id);
        } catch (Exception ex) {
            throw new Exception();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteTeam(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteTeam({})", id);
        try {
            TeamDTO teamDTO = teamFacade.getTeamById(id);
            teamFacade.createTeam(teamDTO);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }


}
