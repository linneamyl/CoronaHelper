package hh.linneamyl.coronahelper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import hh.linneamyl.coronahelper.model.Area;
import hh.linneamyl.coronahelper.model.Post;
import hh.linneamyl.coronahelper.repository.PostRepository;

//Tämä testi testaa, löytyykö postrepositorystä postauksista tietyllä sähköpostiosoitteella

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

	    @Autowired
	    private PostRepository postRepository;

	    @Test
	    public void findByEmailShouldReturnPost() {
	        List<Post> posts = postRepository.findByEmail("apina123@google.com");
	        
	        assertThat(posts).isNotNull();
	        assertThat(posts.get(0).getEmail()).isEqualTo("linnea.myllynen@gmail.com");
	    }
	    
	    // Testaa toimiiko uuden postauksen luonti
	    
	    @Test
	    public void createNewPost() {
	    	Post post = new Post(0, "Voin tuoda kaljaa", "jaripekka@hotmail.com", "0401432643", new Area("Kauniainen"));
	    	postRepository.save(post);
	    	assertThat(post.getId()).isNotNull();
	    }    

	}
