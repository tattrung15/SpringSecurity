package demo.web.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT Username, Password, Enabled FROM Security WHERE Username = ?")
		.authoritiesByUsernameQuery("SELECT Username, Role FROM Security WHERE Username = ?");
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        .antMatchers("/db/**").access("hasRole('DBA')")
        .and().formLogin().loginPage("/dang-nhap").loginProcessingUrl("/login")
        .usernameParameter("username").passwordParameter("password")
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
}
