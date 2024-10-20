package br.com.softblue.bluefood.infrastructure.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationSuccessHandler loginSuccessHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorizer -> authorizer
                        .requestMatchers("/public/**", "/autenticacao/**", "/images/**", "/css/**", "/js/**")
                        .permitAll()
                        .requestMatchers("/cliente/**").hasAnyRole("CLIENTE")
                        .requestMatchers("/restaurante/**").hasAnyRole("RESTAURANTE")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/autenticacao/login")
                        .loginProcessingUrl("/autenticacao/login")
                        .failureUrl("/autenticacao/login/error")
                        .successHandler(authenticationSuccessHandler())
                        .permitAll()
                ).logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/autenticacao/logout"))
                        .logoutSuccessHandler(successHandler())
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionSecurityConfigurer -> sessionSecurityConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );

        return http.build();
    }

    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return loginSuccessHandler;
    }

    @Bean
    public LogoutSuccessHandler successHandler() {
        return logoutSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
