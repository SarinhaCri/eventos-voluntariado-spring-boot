package com.projetoextensao.plataforma_eventos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.projetoextensao.plataforma_eventos.model.User.Role;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

  
    @Autowired
    private UserDetailsService userDetailsService; 

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        
     
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService); 
        
    
        authProvider.setPasswordEncoder(passwordEncoder); 
        
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName(null);

        http
            .authorizeHttpRequests(requests -> requests
               
                .requestMatchers(
                    "/", 
                    "/login", 
                    "/signup", 
                    "/listaEventos", 
                    "/css/**",
                    "/js/**",
                    "/images/**"
                ).permitAll()
                
               
                .requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .requestMatchers("/app/**").authenticated()
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf
                .csrfTokenRequestHandler(requestHandler)
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
            )
            .formLogin(form -> form
                .loginPage("/login") 
                .successHandler(customSuccessHandler) 
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .exceptionHandling(exceptions -> exceptions
                .accessDeniedPage("/403") 
            );

        return http.build();
    }
}