package cz.muni.fi.pa165.sampledata;

import cz.fi.muni.pa165.entity.*;
import cz.fi.muni.pa165.enums.Position;
import cz.fi.muni.pa165.service.IGameService;
import cz.fi.muni.pa165.service.IGoalService;
import cz.fi.muni.pa165.service.IPlayerService;
import cz.fi.muni.pa165.service.ITeamService;
import cz.fi.muni.pa165.service.UserService;
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

    @Autowired
    private IGoalService goalService;
    
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {

        Team t1 = team("Real Madrid C.F.","Madrid", "Spain");
	Team t2 = team("FC Barcelona","Barcelona", "Spain");
        Team t3 = team("Atletico Madrid","Madrid", "Spain");
	Team t4 = team("Sevilla FC","Seville", "Spain");
	Team t5 = team("Villarreal CF","Villar Real", "Spain");
	Team t6 = team("Real Sociedad","San Sebastian", "Spain");
        Team t7 = team("Celta Vigo","Vigo", "Spain");
	Team t8 = team("Sporting Gijon","Gijon", "Spain");
	Team t9 = team("Deportivo La Coruna","A Coruna", "Spain");
	Team t10 = team("Las Palmas","Las Palmas", "Spain");
        
        Game g1 = game(new Date(System.currentTimeMillis()),5,3,t1,t2);
        Game g2 = game(new Date(System.currentTimeMillis()),1,3,t2,t3);
        Game g3 = game(new Date(System.currentTimeMillis()),2,0,t1,t4);
        Game g4 = game(new Date(System.currentTimeMillis()),0,3,t3,t1);
        Game g5 = game(new Date(System.currentTimeMillis()),1,1,t2,t4);
        
        
        Player p1t1 = player("Ronaldo",23,new Date(),"England",Position.DEFENDER,t1);
        Player p2t1 = player("Cristian Bale",24,new Date(),"England",Position.DEFENDER,t1);
        Player p3t1 = player("Larim Brnzema",25,new Date(),"England",Position.DEFENDER,t1);
        Player p4t1 = player("Pepe",26,new Date(),"England",Position.DEFENDER,t1);
        
        Player p1t2 = player("Lionel Messi",23,new Date(),"Argentina",Position.MIDFIELDER,t2);
        Player p2t2 = player("Pique",24,new Date(),"Argentina",Position.MIDFIELDER,t2);
        Player p3t2 = player("Neymar",25,new Date(),"Argentina",Position.MIDFIELDER,t2);
        Player p4t2 = player("Suarez",26,new Date(),"Argentina",Position.MIDFIELDER,t2);
        
        Player p3 = player("Alexis Sanchez",23,new Date(),"Spain",Position.GOAL_KEEPER,t3);
        Player p4 = player("Fili Sebo",23,new Date(),"Slovakia",Position.FORWARD,t4);

        Goal goal1 = goal("Nice goal",g1,p1t1, 15);
        Goal goal2 = goal("Good goal",g1,p1t2, 20);
        Goal goal3 = goal("Epic goal",g1,p2t2, 56);
        
        g1.addGoal(goal1);
        g1.addGoal(goal2);
        g1.addGoal(goal3);
        
        p1t1.addGoal(goal1);
        p2t2.addGoal(goal2);
        p2t2.addGoal(goal3);
        
        playerService.updatePlayer(p1t1);
        playerService.updatePlayer(p2t2);
        
        User admin = user("admin", "Dina", "Sok (464103)", "sokdina999@gmail.com", "123456789", toDate(2016, 12, 12), "Slakova, Brno");

        log.info("Loaded SoccerRecords users.");

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
    
   private Team team(String name, String city, String country) {
	Team t = new Team();
	t.setName(name);
	t.setCity(city);
	t.setCountry(country);
	teamService.create(t);
        return t;
    }
   
   private Goal goal(String description, Game game, Player player, int goalTime) {
	Goal goal = new Goal();
        goal.setDescription(description);
        goal.setGame(game);
        goal.setPlayer(player);
        goal.setGoalTime(goalTime);
        
        goalService.createGoal(goal);
        
        return goal;
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