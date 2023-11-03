package tn.esprit.devops_project;

import org.junit.jupiter.api.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
//@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF and frame options to allow access to the H2 Console
        http.csrf().disable();
        http.headers().frameOptions().disable();

        // Define security rules here if needed for other parts of your application
        // For the H2 Console, it's usually not protected in a development profile.
    }
}
