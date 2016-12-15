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
import cz.fi.muni.pa165.rest.exceptions.InvalidParameterException;
import cz.fi.muni.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST Controller for Teams
 * 
 * @author sokdina999@gmail.com
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_TEAMS)
public class TeamsController {

    final static Logger logger = LoggerFactory.getLogger(TeamsController.class);

    @Inject
    private ITeamFacade teamFacade;

    /**
     * Get list of Teams: curl -i -X GET
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
     * Get one team specified by id:
     * 
     * curl -i -X GET http://localhost:8080/pa165/rest/teams/9
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

    /**
     * 
     * Create a team :
     * 
     * curl -X POST -i -H "Content-Type: application/json" --data '{"name":"Valencia CF","city":"Valencia","country":"Spain"}' http://localhost:8080/pa165/rest/teams/create
     * @param TeamDTO is teamDTO
     * @return TeamDTO
     * @throws Exception ResourceNotFoundException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO createTeam(@RequestBody TeamDTO team) throws Exception {

        logger.debug("rest createTeam()");

        try {
            Long id = teamFacade.createTeam(team);
            return teamFacade.getTeamById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }


    /**
     * 
     * delete a team specified by id
     * 
     * curl -i -X DELETE http://localhost:8080/pa165/rest/teams/9
     *
     * @param id identifier for the team
     * @throws Exception ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteTeam(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteTeam({})", id);
        try {
            teamFacade.deleteTeam(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * 
     * update a team specified by id
     * 
     * curl -X PUT -i -H "Content-Type: application/json" --data '{"id":9,"name":"Manchester City","city":"Manchester","country":"Enland"}' http://localhost:8080/pa165/rest/teams/9
     *
     * @param id identifier for the team
     * @return TeamDTO
     * @throws Exception ResourceNotFoundException
     */

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO UpdateTeam(@PathVariable("id") long id, @RequestBody TeamDTO t) throws Exception {

        logger.debug("rest UpdateTeam({})", id);

        try {
            teamFacade.updateTeam(t);
            return teamFacade.getTeamById(id);
        } catch (Exception esse) {
            throw new InvalidParameterException();
        }

    }

}
