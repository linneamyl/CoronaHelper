package hh.linneamyl.coronahelper.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hh.linneamyl.coronahelper.model.Area;

public interface AreaRepository extends CrudRepository<Area, Long> {

    List<Area> findByName(String name); // Voidaan etsiä Area-listasta yksi area nimen perusteella
    
}
