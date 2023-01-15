package tacos.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import tacos.dao.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true) //sirve para habilitar el spring security en methods
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    /*
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new User(
                "buzz", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        usersList.add(new User(
                "woody", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        return new InMemoryUserDetailsManager(usersList);
    }*/


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            tacos.data.User user = userRepo.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests().antMatchers("/design","orders").hasRole("USER")
                .antMatchers("/", "/**").permitAll().and().build();
    }
     */


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()


                .antMatchers("/design", "/orders").access("hasRole('USER')")
                .antMatchers("/", "/**","/h2-console/**").access("permitAll")

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/design")
                .failureUrl("/login-error")



                /*
                .and()
                  .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticate")
                    .usernameParameter("user")
                    .passwordParameter("pwd")
                 */

                .and()
                .oauth2Login()
                .loginPage("/login")

                .and()
                .logout()
                //.logoutSuccessUrl("/")


                .and()
                //.csrf().disable() //necesario apra consola h2  - deshabilitando para rest data (put genera forbiden)
                .csrf().ignoringAntMatchers("/h2-console/**").and()

                .headers().frameOptions().disable().and()



                //para data rest  TODO: Investigar seguridad para rest data
                .csrf()
                .ignoringAntMatchers("/data-api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/data-api/**").permitAll()
                .and()


                //.and()
                .build();
    }


    //TODO: Otro ejemplo

    /*
    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  return http
    .authorizeRequests()
      .antMatchers("/design", "/orders")
        .access("hasRole('USER') && " +
          "T(java.util.Calendar).getInstance().get("+
          "T(java.util.Calendar).DAY_OF_WEEK) == " +
          "T(java.util.Calendar).TUESDAY")
      .antMatchers("/", "/**").access("permitAll")

    .and()
    .build();
}
     */
}
