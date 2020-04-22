package hh.linneamyl.coronahelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import hh.linneamyl.coronahelper.model.Area;
import hh.linneamyl.coronahelper.model.Post;
import hh.linneamyl.coronahelper.model.User;
import hh.linneamyl.coronahelper.repository.AreaRepository;
import hh.linneamyl.coronahelper.repository.PostRepository;
import hh.linneamyl.coronahelper.repository.UserRepository;


@SpringBootApplication
public class CoronahelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronahelperApplication.class, args);
	}
	

	// 	CommandlineRunner tallentaa muutamia tietoja sivuille katsojien näkyville
	@Bean
	public CommandLineRunner demo(PostRepository postRepository, UserRepository userRepository, AreaRepository areaRepository) {
		return (args) -> {

			//Admin ja user käyttäjät
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			// Lisää paikkoja areaRepositoryyn
			log.info("Areas");
			areaRepository.save(new Area("Helsinki"));
			areaRepository.save(new Area("Espoo"));
			areaRepository.save(new Area("Vantaa"));
			
			// post: id, PostString, email, phone,  area
			log.info("This saves few posts");
			postRepository.save(new Post(0, "Voin auttaa kauppakäynneissä!", "linnea.myllynen@gmail.com", "0441231234", areaRepository.findByName("Helsinki").get(0)));
			postRepository.save(new Post(0, "Haluan lenkittää koiria.", "apina123@google.com", "0412345678", areaRepository.findByName("Espoo").get(0)));	
			
			log.info("fetch all posts");
			for (Post post : postRepository.findAll()) {
				log.info(post.toString());
			}
		};
	}




}
