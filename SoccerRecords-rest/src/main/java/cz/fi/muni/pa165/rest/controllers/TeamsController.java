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


@RestController
@RequestMapping(ApiUris.ROOT_URI_TEAMS)
public class TeamsController {

    final static Logger logger = LoggerFactory.getLogger(TeamsController.class);

    @Inject
    private ITeamFacade teamFacade;

    /**
     * get all the categories
     * @return list of CategoryDTOs
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TeamDTO> getCategories() {

        logger.debug("rest getCategories()");
        return teamFacade.getAllTeams();
    }

    /**
     * 
     * Get one category specified by id
     * 
     * @param id identifier for the category
     * @return CategoryDTO
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
}
