
package cz.fi.muni.pa165.service;

import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;

/**
 * 
 * @author Sok Dina, Peter Lipcak, Jaromir Sys and Martin Kocák
 */ 

public interface BeanMappingService {
	
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}






