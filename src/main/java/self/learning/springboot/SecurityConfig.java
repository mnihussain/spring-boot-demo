package self.learning.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication().withUser("admin").password("platform").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("user1").password("pass1").roles("USER");
//	}
    
    @Bean
    UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin = User.withUsername("admin")
				.password(encoder.encode("pwd"))
				.roles("ADMIN")
				.build();
		UserDetails user = User.withUsername("user1")
				.password(encoder.encode("pwd1"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin,user);
	}

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	return http.csrf().disable()
//    		.authorizeHttpRequests()
//    		.requestMatchers("/public/**").permitAll()
//    		.and()
//    		.authorizeHttpRequests()
//    		.requestMatchers("/admin/**").authenticated()
//    		.and().formLogin()
//    		.and().build();
    	
    	return http
    			.csrf(AbstractHttpConfigurer::disable)
    			.authorizeHttpRequests(req ->
    				req.requestMatchers(new String[] {"url1","url2"}).permitAll())
    			.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
