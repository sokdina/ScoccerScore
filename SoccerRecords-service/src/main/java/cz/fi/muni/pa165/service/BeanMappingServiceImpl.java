package cz.fi.muni.pa165.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sokdina999@gmail.com
 * 
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {

    @Autowired
    private Mapper mapper;

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        return objects.stream().map(o -> mapper.map(o, mapToClass)).collect(Collectors.toList());
    }

    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return mapper.map(u, mapToClass);
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }
}
