package hh.linneamyl.coronahelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.linneamyl.coronahelper.service.UserService;


@Configuration
	@EnableWebSecurity
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Autowired
	    private UserService userDetailsService;	
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	            	.antMatchers("/css/*", "/img/*").permitAll()
	            	.anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login") //käyttäjän ensimmäinen sivu on aina /login jos käyttäjä ei ole vielä kirjautunut sisään
	                .defaultSuccessUrl("/", true)
	                .permitAll()
	                .and()
	                .httpBasic()
	    			.and()
	    			.csrf().disable()
	    		.logout()
	    			.logoutSuccessUrl("/login") //Kun  käyttäjä kirjautuu ulos, ohjataan takaisin /login sivulle
	    			.invalidateHttpSession(true)
	    			.deleteCookies("JSESSIONID");
	    }

	    //BCryptaa käyttäjän salasanan
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	    }
	}
