package hh.linneamyl.coronahelper.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hh.linneamyl.coronahelper.model.Post;

	
	public interface PostRepository extends CrudRepository<Post, Long> {
		
		List<Post> findByEmail(String email); //Voidaan etsiä post-listauksesta yksi postaus emailin perusteella
		

	}


