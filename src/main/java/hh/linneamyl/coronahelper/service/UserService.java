package hh.linneamyl.coronahelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.linneamyl.coronahelper.model.User;
import hh.linneamyl.coronahelper.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	private final UserRepository repository;

	@Autowired // @Autowired-merkintää voidaan käyttää luokkien automaattiseen yhdistämiseen setter-menetelmällä
	public UserService(UserRepository userRepository) {
		this.repository = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	User curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   

}
