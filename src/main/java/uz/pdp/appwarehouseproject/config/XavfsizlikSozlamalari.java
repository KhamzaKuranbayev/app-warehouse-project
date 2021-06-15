package uz.pdp.appwarehouseproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class XavfsizlikSozlamalari extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        // Buni men yozdim
        // ROLE       - lavozim
        // PERMISSION - Huquq
        auth
                .inMemoryAuthentication()
                .withUser("ahmad").password(passwordEncoder().encode("ahmad")).roles("DIRECTOR1")
                .authorities("ADD_USER", "GET_ALL_USER", "GET_USER", "DELETE_USER", "EDIT_USER")
                .and()
                .withUser("ali").password(passwordEncoder().encode("aliyev")).roles("MANAGER")
                .authorities("ADD_USER", "EDIT_USER")
                .and()
                .withUser("salim").password(passwordEncoder().encode("salim")).roles("USER")
                .authorities("GET_ALL_USER")
                .and()
                .withUser("tohirjon").password(passwordEncoder().encode("tohir")).roles("MODERATOR")
                .authorities("GET_ALL_USER")
                .and()
                .withUser("sobir").password(passwordEncoder().encode("sobir")).roles("DIRECTOR")
                .authorities("GET_ALL_USER", "GET_USER");



                // .authorities("ADD_USER", "GET_ALL_USER", "GET_USER", "DELETE_USER", "EDIT_USER")
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                /*.antMatchers(HttpMethod.POST, "/api/users/**").hasAnyRole("MANAGER", "DIRECTOR")
                .antMatchers(HttpMethod.DELETE, "/api/users/*").hasRole("DIRECTOR")
                .antMatchers(HttpMethod.PUT, "/api/users/*").hasRole("USER")*/
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
