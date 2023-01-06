package medved.java.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class PersonsConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/persons/by-city").permitAll()
                .and()
                .authorizeRequests().antMatchers("/persons/by-age","/persons/by-fio").hasAuthority("first_auth")
                .and()
                .authorizeRequests().antMatchers("/persons/baseperson/by-age").hasAuthority("second_auth")
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated();
        return http.build();
    }
}