package www.muni.cz;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import www.muni.cz.entity.Team;
import www.muni.cz.dao.TeamDAO;

public class Main {
	public static void main(String[] args) {
            Call_Team();
            Test_Team_HashCode();
        }
        
        
        private static void  Call_Team(){
            ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring.xml");
            TeamDAO teamManager = (TeamDAO) ctx.getBean("TeamDAOImpl");
            
            /* 
            * Create Three Teams and Insert them into table Team 
            */
            Team team_one = new Team();
            team_one.setId(4);
            team_one.setName("FC Barcelona");
            team_one.setCity("Barcelona");
            team_one.setCountry("Spain");
            teamManager.create(team_one);
            System.out.println("Insert FC Barcelona");
            
            Team tean_two = new Team();
            tean_two.setId(5);
            tean_two.setName("Real Madrid");
            tean_two.setCity("Madrid");
            tean_two.setCountry("Spain");
            teamManager.create(tean_two);
            System.out.println("Insert Real Madrid");
           
            Team team_three = new Team();
            team_three.setId(6);
            team_three.setName("Attatico Madrid");
            team_three.setCity("Madrid");
            team_three.setCountry("England");
            teamManager.create(team_three);
            System.out.println("Insert AtlÃ©tico Madrid");
            
            /* 
            * Update AtlÃ©tico Madrid Team by finding a particular team ID
            * Change country field from England to Spain
            */
            Team team_update = teamManager.findById(6);
            if (team_update == null){
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
            
            Team team_delete = teamManager.findById(5);
            if (team_delete == null){
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
            for(Team t:list_name){
                System.out.println("Team ID: " + t.getId() + " Name: " + t.getName() + " City: " + t.getCity() + " Country: " + t.getCountry());
            }
             
             
            /* 
            * List all teams from Team table
            * and show them
            */ 
            List<Team> list = teamManager.findByAll();
            System.out.println("Total Team: " + list.size());
            
            for(Team t:list){
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
		t1.setId(1);
		Team t2 = new Team();
		t2.setName("FC Barcelona");
		t2.setId(2);
		MockProduct mp = new MockProduct();
		mp.setName("FC Barcelona");
		mp.setId(3);
		
		System.out.println("Your equals and hashcode should work on unique 'name' attribute");
		if (t1.equals(t2) && t1.hashCode()==t2.hashCode()){
			System.out.println("CORRECT");
		} else System.out.println("INCORRECT!");
		
		
		System.out.println("Your equals should use instanceof and not getClass()==");
		if (t1.equals(mp)){
			System.out.println("CORRECT");
		} else
			System.out.println("INCORRECT!");

		System.out.println("Your equals should call getter to get 'name' value on the other object, because other object may be a proxy class instance");
		if (mp.getNameCalled){
			System.out.println("CORRECT");
		} else System.out.println("INCORRECT!");
		}
}

