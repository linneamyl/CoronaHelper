package hh.linneamyl.coronahelper.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

	

	// Postauksen Entity luokka. Sisältää attribuutteina id:n, postStringin, emailin ja phonen

	@Entity
	public class Post {
		
		@Id //Luo automaattisesti ID:n uutta postia luodessa
		@GeneratedValue(strategy = GenerationType.AUTO) //Tarjoaa primaryvaimen arvojen generointistrategioiden määrittämiseen. 
		public long id;
		private String postString;
		private String email;
		private String phone;
		
		    
		    @ManyToOne //  lähdeobjektilla on määrite, joka viittaa toiseen objektiin, kohdeobjektiin.
			@JsonIgnore // jättää luokan jäsenet JSONin ulkopuolelle.
			  @JoinColumn(name = "areaid") // Liittää area-luokan areaid:n kautta
			    private Area area;
		    
		    //KONSTRUKTORIT

			public Post() {
				super();
			}

			public Post(long id, String postString, String email, String phone, Area area) {
				super();
				this.id = id;
				this.postString = postString;
				this.email = email;
				this.phone = phone;
				this.area = area;
			}

			//GETIT JA SETIT
			
			public long getId() {
				return id;
			}

			public void setId(long id) {
				this.id = id;
			}

			public String getPostString() {
				return postString;
			}

			public void setPostString(String postString) {
				this.postString = postString;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public String getPhone() {
				return phone;
			}

			public void setPhone(String phone) {
				this.phone = phone;
			}

			public Area getArea() {
				return area;
			}

			public void setArea(Area area) {
				this.area = area;
			}

			//TOSTRING
			
			@Override
			public String toString() {
				return "Post [id=" + id + ", postString=" + postString + ", email=" + email + ", phone=" + phone + ", area=" + area + "]";
			}
		

	}


