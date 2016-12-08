package cz.muni.fi.pa165.sampledata;

import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.service.UserService;
import java.io.IOException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

public interface SampleDataLoadingFacade {

    void loadData() throws IOException;
}
