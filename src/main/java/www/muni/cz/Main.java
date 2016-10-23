package www.muni.cz;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import www.muni.cz.entity.Team;
import www.muni.cz.dao.TeamDAO;

public class Main {
	public static void main(String[] args) {
            Call_Team();
           // Test_Team_HashCode();
        }
        
        
        private static void  Call_Team(){
            ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring.xml");
            TeamDAO teamManager = (TeamDAO) ctx.getBean("TeamDAOImpl");
            
            /* 
            * Create Three Teams and Insert them into table Team 
            */
            Team team_one = new Team();
            team_one.setId(22);
            team_one.setName("FC Barcelona");
            team_one.setCity("Barcelona");
            team_one.setCountry("Spain");
            teamManager.create(team_one);
            System.out.println("Insert FC Barcelona");
            /*
            Team tean_two = new Team();
            tean_two.setId(8);
            tean_two.setName("Real Madrid");
            tean_two.setCity("Madrid");
            tean_two.setCountry("Spain");
            teamManager.create(tean_two);
            System.out.println("Insert Real Madrid");
*/
        }
}
