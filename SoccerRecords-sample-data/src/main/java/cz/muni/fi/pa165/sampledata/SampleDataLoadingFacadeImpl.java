package cz.muni.fi.pa165.sampledata;

import cz.fi.muni.pa165.entity.*;
import cz.fi.muni.pa165.enums.Position;
import cz.fi.muni.pa165.facade.ITeamFacade;
import cz.fi.muni.pa165.service.IGameService;
import cz.fi.muni.pa165.service.IPlayerService;
import cz.fi.muni.pa165.service.ITeamService;
import cz.fi.muni.pa165.service.UserService;
import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private IPlayerService playerService;
     
    @Autowired
    private ITeamService teamService;
    
    @Autowired
    private IGameService gameService;

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {

        Team t1 = team("Barcelona","Barcelona","Spain");
        Team t2 = team("Real Madrid","Madrid","Spain");
        Team t3 = team("Liverpool","Liverpool","England");
        Team t4 = team("Arsenal","London","England");
        
        Game g1 = game(new Date(System.currentTimeMillis()),5,3,t1,t2);
        Game g2 = game(new Date(System.currentTimeMillis()),1,3,t2,t3);
        Game g3 = game(new Date(System.currentTimeMillis()),2,0,t1,t4);
        Game g4 = game(new Date(System.currentTimeMillis()),0,3,t3,t1);
        Game g5 = game(new Date(System.currentTimeMillis()),1,1,t2,t4);
        
        
        Player p1 = player("John Terry",23,new Date(),"England",Position.DEFENDER,t1);
        Player p2 = player("Lionel Messi",23,new Date(),"Argentina",Position.MIDFIELDER,t2);
        Player p3 = player("Alexis Sanchez",23,new Date(),"Spain",Position.GOAL_KEEPER,t3);
        Player p4 = player("Fili Sebo",23,new Date(),"Slovakia",Position.FORWARD,t4);
        
        
        
        User admin = user("123", "Dina", "Sok", "sokdina999@gmail.com", "123456789", toDate(2016, 12, 12), "Slakova, Brno");
        log.info("Loaded eShop users.");

    }

    private User user(String password, String givenName, String surname, String email, String phone, Date joined, String address) {
        User u = new User();
        u.setGivenName(givenName);
        u.setSurname(surname);
        u.setEmail(email);
        u.setPhone(phone);
        u.setAddress(address);
        u.setJoinedDate(joined);
        if (password.equals("admin")) {
            u.setAdmin(true);
        }
        userService.registerUser(u, password);
        return u;
    }

    private static Date toDate(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private Game game(Date date, int homeScore, int guestScore, Team homeTeam, Team guestTeam){
        
        Game game = new Game();
        game.setDateOfGame(date);
        game.setGameResult(homeScore, guestScore);
        game.setHomeTeam(homeTeam);
        game.setGuestTeam(guestTeam);
        
        gameService.create(game);
        
        return game;
    }
    
    private Team team(String name, String city, String country){
        Team team = new Team();
        team.setName(name);
        team.setCity(city);
        team.setCountry(country);
        
        teamService.create(team);
        
        return team;
    }
    
    private Player player(String name, int number, Date dateOfBirth, String country, Position position , Team t){
        Player player = new Player();
        player.setName(name);
        player.setDressNumber(number);
        player.setDateOfBirth(dateOfBirth);
        player.setCountry(country);
        player.setPosition(position);
        player.setTeam(t);
  
        playerService.createPlayer(player);
        
        return player;
    }
    
    
}
