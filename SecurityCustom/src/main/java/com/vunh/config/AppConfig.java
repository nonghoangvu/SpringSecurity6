package com.vunh.config;

import com.vunh.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class AppConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasAuthority(RoleEnum.ADMIN.name())
                        .anyRequest().authenticated()
                ).formLogin(form -> form.loginProcessingUrl("/login")
                        .successHandler(this.customAuthenticationSuccessHandler())
//                        .failureHandler(this.customAuthenticationFailureHandler())
                )
                .userDetailsService(this.userDetailsService)
                .exceptionHandling(ex -> ex.accessDeniedPage("/error"));
        return http.build();
    }

    @Bean
    AuthenticationSuccessHandler customAuthenticationSuccessHandler() {//Handle login when role is ?
        return (request, response, authentication) -> {
            String redirectUrl = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(RoleEnum.ADMIN.name()))
                    ? "/admin"
                    : "/";
            response.sendRedirect(redirectUrl);
        };
    }

//    @Bean//Error repeat when login failed
//    AuthenticationFailureHandler customAuthenticationFailureHandler() {
//        return (request, response, exception) -> {
//            String username = request.getParameter("username");
//            request.setAttribute("username", username);
//            request.setAttribute("error", "Invalid username or password");
//            request.getRequestDispatcher("/login?error").forward(request, response);
//        };
//    }
}
