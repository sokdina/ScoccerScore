package cz.fi.muni.pa165.service.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.service.TeamServiceImpl;
import cz.fi.muni.pa165.facade.UserFacadeImpl;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={TeamServiceImpl.class})
public class ServiceConfiguration {
	
	@Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();		
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}

	public class DozerCustomConfig extends BeanMappingBuilder {
	    @Override
	    protected void configure() {
	        mapping(Team.class, TeamDTO.class);
	    }
	}
		
}


