
package cz.muni.fi.pa165.sampledata;


import cz.fi.muni.pa165.entity.*;
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
    private ITeamService teamService;


    @Override
    public void loadData() throws IOException {
    // Add User to log in
	User pepa = user("heslo", "Pepa", "Novák", "pepa@novak.cz", "603123456", toDate(2015, 5, 12), "Horní Kotěhůlky 12");
        User jiri = user("heslo", "Jiří", "Dvořák", "jiri@dvorak.cz", "603789123", toDate(2015, 3, 5), "Dolní Lhota 56");
        User eva = user("heslo", "Eva", "Adamová", "eva@adamova.cz", "603457890", toDate(2015, 6, 5), "Zadní Polná 44");        
	User admin = user("123", "Dina", "Sok", "sokdina999@gmail.com", "123456789", toDate(2016, 12, 12), "Slakova, Brno");
        log.info("Loaded SoccerRecords System users.");
    // Add team Sample-Data
      //  Team real_madrid = team(1L, "Real Madrid C.F.","Madrid", "Spain");
	//Team barcelona = team(2L, "FC Barcelona","Barcelona", "Spain");
	//Team atletico = team(3L, "Atletico Madrid","Madrid", "Spain");
	//Team sevilla = team(4L, "Sevilla FC","Seville", "Spain");
	//Team villarReal = team(5L, "Villarreal CF","Villar Real", "Spain");
	//Team real_sociedad = team(6L, "Real Sociedad","San Sebastian", "Spain");
    }

    private Team team(Long id, String name, String city, String country) {
	Team t = new Team();
	t.setId(id);
	t.setName(name);
	t.setCity(city);
	t.setCountry(country);
	teamService.create(t);
        return t;
    }


      private User user(String password, String givenName, String surname, String email, String phone, Date joined, String address) {
        User u = new User();
        u.setGivenName(givenName);
        u.setSurname(surname);
        u.setEmail(email);
        u.setPhone(phone);
        u.setAddress(address);
        u.setJoinedDate(joined);
        if(password.equals("admin")) u.setAdmin(true);
        userService.registerUser(u, password);
        return u;
    }

     private static Date toDate(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
