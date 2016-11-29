package cz.fi.muni.pa165.service.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dto.GameDTO;
import cz.fi.muni.pa165.dto.GoalDTO;
import cz.fi.muni.pa165.dto.PlayerDTO;
import cz.fi.muni.pa165.dto.TeamDTO;
import cz.fi.muni.pa165.entity.Game;
import cz.fi.muni.pa165.entity.Goal;
import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.entity.Team;
import cz.fi.muni.pa165.service.GameServiceImpl;
import cz.fi.muni.pa165.service.GoalServiceImpl;
import cz.fi.muni.pa165.service.PlayerServiceImpl;
import cz.fi.muni.pa165.service.TeamServiceImpl;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={TeamServiceImpl.class,GameServiceImpl.class,GoalServiceImpl.class,PlayerServiceImpl.class})
public class ServiceConfiguration {
	
	@Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();		
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}

    private static class DozerCustomConfig extends BeanMappingBuilder {

        public DozerCustomConfig() {
        }

        @Override
        protected void configure() {
            mapping(Game.class, GameDTO.class);
            mapping(Goal.class, GoalDTO.class);
            mapping(Player.class,PlayerDTO.class);
            mapping(Team.class,TeamDTO.class);
        }


    }
		
}

