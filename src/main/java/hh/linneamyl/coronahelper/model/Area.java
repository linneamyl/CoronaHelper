package hh.linneamyl.coronahelper.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

//Alue -Entity, jossa käyttäjä voi valita alueensa. Sisältää areaid:n ja nimen sekä listauksen postauksista

@Entity
public class Area {
	 @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private Long areaid;
		private String name;
		
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
		@JsonBackReference
		private List<Post> posts;
		
		//KONSTRUKTORIT

		public Area() {
		}

		public Area(String name) {
			super();
			this.name = name;
		}

		//GETIT JA SETIT
		
		public Long getAreaid() {
			return areaid;
		}

		public void setAreaid(Long areaid) {
			this.areaid = areaid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Post> getPosts() {
			return posts;
		}

		public void setPosts(List<Post> posts) {
			this.posts = posts;
		}

		
		
}
