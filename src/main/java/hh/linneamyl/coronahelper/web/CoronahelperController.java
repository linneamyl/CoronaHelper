package hh.linneamyl.coronahelper.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.linneamyl.coronahelper.model.Post;
import hh.linneamyl.coronahelper.repository.AreaRepository;
import hh.linneamyl.coronahelper.repository.PostRepository;



@Controller
public class CoronahelperController {

	
	@Autowired
	private PostRepository postRepository; 
	
	@Autowired
	private AreaRepository areaRepository;
	
	//Sivuston etusivu, jossa listataan postaukset
	@RequestMapping(value = {"/", "/postlist"}, method = RequestMethod.GET)
	public String helper(Model model) {
		
		model.addAttribute("post", new Post());
		model.addAttribute("posts", postRepository.findAll());
		return "postlist";
	}
	
	// REST-metodi jolla saadaan kaikki postaukset
    @RequestMapping(value="/posts", method = RequestMethod.GET)
    public @ResponseBody List<Post> postListRest() {	
        return (List<Post>) postRepository.findAll();
    }    

	// REST-metodi, jolla saadaan yksi postaus id:n perusteella
    @RequestMapping(value="/posts/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Post> findPostRest(@PathVariable("id") Long postId) {	
    	return postRepository.findById(postId);
    }   
    
    //Lisää postaus
    @RequestMapping(value = "/addpost")
    public String addPost(Model model){
    	model.addAttribute("post", new Post());
    	model.addAttribute("areas", areaRepository.findAll());
        return "addpost";
    }    
    
    //Tallenna postaus
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Post post){
        postRepository.save(post);
        return "redirect:postlist";
    }   
    
    //Vain admin-käyttäjä saa poistaa postauksia
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") Long postId, Model model) {
    	postRepository.deleteById(postId);
        return "redirect:../postlist";
    }  
    
    // Vain admin saa muokata postauksia
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/editpost/{id}", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") Long postId, Model model) {
    	//Haetaan tietokannasta sql-lauseella kirja jolla on tämä ID ja lisätään modeliin
    	Optional<Post> post = postRepository.findById(postId);
    	model.addAttribute("areas", areaRepository.findAll());
    	model.addAttribute("post", post);
        return "editpost";
    }
    
    //Sisäänbkirjautuminen
    @RequestMapping(value="/login")
   	public String login() {
   		return "login";
   	} 
    
    //uloskirjautuminen, heittää takaisin login sivulle uloskirjautumisen jälkeen.
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
     }
}


