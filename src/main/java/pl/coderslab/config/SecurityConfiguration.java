package pl.coderslab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests() //restrict access based on the HttpServletRequest
                .anyRequest().authenticated() //any Request to the app must be authenticated (i.e. logged in)
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/loginError")
                .permitAll();

    }
}
