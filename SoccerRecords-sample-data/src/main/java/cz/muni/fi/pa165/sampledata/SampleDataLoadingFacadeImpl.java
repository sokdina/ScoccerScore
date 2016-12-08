
package cz.muni.fi.pa165.sampledata;


import cz.fi.muni.pa165.entity.*;
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
    
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
       
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
        if(password.equals("admin")) u.setAdmin(true);
        userService.registerUser(u, password);
        return u;
    }

     private static Date toDate(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
