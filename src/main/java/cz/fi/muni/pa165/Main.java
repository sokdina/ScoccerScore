package cz.fi.muni.pa165;

import cz.fi.muni.pa165.dao.IGameDao;
import cz.fi.muni.pa165.dao.IGoalDao;
import cz.fi.muni.pa165.dao.IPlayerDao;
import cz.fi.muni.pa165.dao.PlayerDaoImpl;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.dao.TeamDAO;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.enums.MatchResult;
import cz.fi.muni.pa165.enums.Position;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "spring.xml");

    public static void main(String[] args) {
        game();
//        Call_Team();
//        Test_Team_HashCode();
//        goal();
    }

    public static void game() {
        System.out.println("GOAL METHOD CALLED");

        TeamDAO teamManager = (TeamDAO) ctx.getBean("TeamDAOImpl");

        /* 
            * Create Three Teams and Insert them into table Team 
         */
        Team team_one = new Team();
        //team_one.setId(13);
        team_one.setName("FC Barcelona");
        team_one.setCity("Barcelona");
        team_one.setCountry("Spain");
        teamManager.create(team_one);
        System.out.println("Insert FC Barcelona");

        Team tean_two = new Team();
        // tean_two.setId(14);
        tean_two.setName("Real Madrid");
        tean_two.setCity("Madrid");
        tean_two.setCountry("Spain");
        teamManager.create(tean_two);
        System.out.println("Insert Real Madrid");

        team_one = teamManager.findByName(team_one.getName()).get(0);
        tean_two = teamManager.findByName(tean_two.getName()).get(0);

        IGameDao gameManager = (IGameDao) ctx.getBean("GameDAOImpl");

        Game game = new Game();
        game.setGuestTeam(team_one);
        game.setHomeTeam(tean_two);
        java.util.Date utilDate = new java.util.Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(utilDate);
        cal.set(Calendar.MILLISECOND, 0);
        game.setDateOfGame(new java.util.Date(1220227200));
        game.setMatchResult(MatchResult.HOME_TEAM_WIN);
        gameManager.create(game);

        System.out.println(gameManager.findAll());
        System.out.println(team_one.getId());
        System.out.println(gameManager.findById(3));
        System.out.println(gameManager.findGamesBetweenTeams(team_one.getId(), tean_two.getId()));
    }

    private static void goal() {
        System.out.println("testing goal");

        IGoalDao goalDao = (IGoalDao) ctx.getBean("GoalDao");
        IPlayerDao playerDao = (IPlayerDao) ctx.getBean("PlayerDaoImpl");
        Goal g = new Goal();
        g.setDescription("asdsda");
        goalDao.create(g);
        Goal g1 = new Goal();
        goalDao.create(g1);
        Goal g2 = new Goal();
        goalDao.create(g2);
        Goal g3 = new Goal();
        goalDao.create(g3);
        Goal g4 = new Goal();
        goalDao.create(g4);
        Goal g5 = new Goal();

        Player p1 = new Player();

        p1.setName("John Terry");
        p1.setPosition(Position.DEFENDER);
        p1.setDateOfBirth(new Date());
        p1.setDressNumber(26);
        p1.setCountry("England");
        Set<Goal> goalss = new HashSet<>();
        goalss.add(g5);
        p1.setGoal(goalss);
        g5.setPlayer(p1);
        playerDao.create(p1);
        goalDao.create(g5);

        System.out.println("goal created");

        Set<Goal> goals = goalDao.findAll();
        System.out.println("goals found : " + goals.size());
        for (Goal temp : goals) {
            System.out.println("goal id: " + temp.getId() + " desc: " + temp.getDescription());
        }

        for (Goal temp : goalDao.findByPlayer(p1)) {
            System.out.println("Goal " + temp.getId() + " scored by " + p1.getName());
        }

    }

    private static void Call_Team() {
        TeamDAO teamManager = (TeamDAO) ctx.getBean("TeamDAOImpl");

        /* 
            * Create Three Teams and Insert them into table Team 
         */
        Team team_one = new Team();
        //team_one.setId(13);
        team_one.setName("FC Barcelona");
        team_one.setCity("Barcelona");
        team_one.setCountry("Spain");
        teamManager.create(team_one);
        System.out.println("Insert FC Barcelona");

        Team tean_two = new Team();
        // tean_two.setId(14);
        tean_two.setName("Real Madrid");
        tean_two.setCity("Madrid");
        tean_two.setCountry("Spain");
        teamManager.create(tean_two);
        System.out.println("Insert Real Madrid");

        Team team_three = new Team();
        //team_three.setId(15);
        team_three.setName("Attatico Madrid");
        team_three.setCity("Madrid");
        team_three.setCountry("England");
        teamManager.create(team_three);
        System.out.println("Insert AtlÃ©tico Madrid");

        /* 
            * Update AtlÃ©tico Madrid Team by finding a particular team ID
            * Change country field from England to Spain
         */
        Team team_update = teamManager.findById(team_three.getId());
        if (team_update == null) {
            System.out.println("Team deon's exit, please check your Team ID again!");
            return;
        }
        team_update.setCountry("Spain");
        teamManager.update(team_update);
        System.out.println("Update AtlÃ©tico Madrid Team's Country to: " + team_update.getCountry());

        /* 
            * Remove Real Madrid Team from Team
            * by a specific team Team ID
         */
        Team team_delete = teamManager.findById(tean_two.getId());
        if (team_delete == null) {
            System.out.println("Team deon's exit, please check your Team ID again!");
            return;
        }
        teamManager.delete(team_update);
        System.out.println("Delete Real Madrid Team from Team.");

        /* 
            * List all teams from Team table
            * corresponding to input name;
         */
        List<Team> list_name = teamManager.findByName("Real");
        System.out.println("List Team corresponding to input Name");
        for (Team t : list_name) {
            System.out.println("Team ID: " + t.getId() + " Name: " + t.getName() + " City: " + t.getCity() + " Country: " + t.getCountry());
        }

        /* 
            * List all teams from Team table
            * and show them
         */
        List<Team> list = teamManager.findByAll();
        System.out.println("Total Team: " + list.size());

        for (Team t : list) {
            System.out.println("Team ID: " + t.getId() + " Name: " + t.getName() + " City: " + t.getCity() + " Country: " + t.getCountry());
        }
    }

    private static void Test_Team_HashCode() {
        System.out.println("Testing Hash Code for Team");

        class MockProduct extends Team {

            private boolean getNameCalled = false;

            @Override
            public String getName() {
                getNameCalled = true;
                return super.getName();
            }
        }

        Team t1 = new Team();
        t1.setName("FC Barcelona");
        t1.setId(1L);
        Team t2 = new Team();
        t2.setName("FC Barcelona");
        t2.setId(2L);
        MockProduct mp = new MockProduct();
        mp.setName("FC Barcelona");
        mp.setId(3L);

        System.out.println("Your equals and hashcode should work on unique 'name' attribute");
        if (t1.equals(t2) && t1.hashCode() == t2.hashCode()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT!");
        }

        System.out.println("Your equals should use instanceof and not getClass()==");
        if (t1.equals(mp)) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT!");
        }

        System.out.println("Your equals should call getter to get 'name' value on the other object, because other object may be a proxy class instance");
        if (mp.getNameCalled) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT!");
        }
    }
}
