package peaksoft.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("elnura").password("elnura").roles("Instructor"))
                .withUser(userBuilder.username("ryskeldi").password("ryskeldi").roles("Student"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole( "Instructor", "Student")
                .antMatchers("/companies","/courses","/groups","/teachers").hasRole("Instructor")
                .antMatchers("/students").hasAnyRole("Student","Instructor")
                .and().formLogin().permitAll();
        super.configure(http);
    }
}
