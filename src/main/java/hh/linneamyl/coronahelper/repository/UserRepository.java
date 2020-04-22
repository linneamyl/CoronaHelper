package hh.linneamyl.coronahelper.repository;

import org.springframework.data.repository.CrudRepository;

import hh.linneamyl.coronahelper.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username); //Voidaan etsiä yksi käyttäjä käyttäjälistasta käyttäjänimen perusteella
}
