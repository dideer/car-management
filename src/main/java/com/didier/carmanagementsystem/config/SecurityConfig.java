package com.didier.carmanagementsystem.config;




import com.didier.carmanagementsystem.service.sec.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {


    @Autowired
    private  CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private  CustomSuccessHandler customSuccessHandler;



    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
        http.csrf(c  -> c.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/ceo/**").hasRole("CEO")
                        .requestMatchers("/manage/**").hasRole("MANAGER")
                        .requestMatchers("/salesperson/**").hasRole("SALESPERSON")
                        .requestMatchers("/employee/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/users/login").loginProcessingUrl("/users/login")
                        .successHandler(customSuccessHandler).permitAll())
                .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout").permitAll());

        return http.build();
    }



    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());
    }

}
